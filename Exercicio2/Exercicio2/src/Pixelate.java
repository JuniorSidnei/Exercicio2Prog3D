import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sidnei on 02/04/2017.
 */

public class Pixelate
{
    private static String Path = "C:\\Users\\Sidnei\\Documents\\Prog3d\\img\\img\\cor";


    private float [][] contraste =
        {
                { 0.0f, -1.0f,  0.0f},
                {-1.0f,  5.0f, -1.0f},
                { 0.0f, -1.0f,  0.0f}
        };

    private int saturate(int value)
    {

        if(value > 255)

            return 255;

        if(value < 0)

            return 0;

        return value;

    }



    private BufferedImage applyKernel(BufferedImage img, float[][] kernel)
    {

        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);


        for (int y = 0; y < img.getHeight(); y++)
        {

            for (int x = 0; x < img.getWidth(); x++)
            {

                int r = 0;

                int g = 0;

                int b = 0;



                for(int kx=0; kx < 3; kx++){

                    for(int ky = 0; ky < 3; ky++) {



                        int px = x + (kx - 1);

                        int py = y + (ky - 1);



                        if(px < 0 || px >= img.getWidth() || py < 0 || py >= img.getHeight())

                            continue;



                        Color color = new Color(img.getRGB(px, py));

                        r += color.getRed() * kernel[kx][ky];

                        g += color.getGreen() * kernel[kx][ky];

                        b += color.getBlue() * kernel[kx][ky];

                    }

                }

                Color newColor = new Color(saturate(r), saturate(g), saturate(b));

                out.setRGB(x, y, newColor.getRGB());

            }

        }

        return out;

    }



    private void run() throws IOException
    {

        BufferedImage img = ImageIO.read(new File(Path, "turtle.jpg"));
        BufferedImage newImg = applyKernel(img, contraste);
        ImageIO.write(newImg, "jpg", new File(Path, "turtleSuavizacaoCruz.jpg"));

    }



    public static void main(String[] args) throws IOException {

        new Pixelate().run();

    }
}
