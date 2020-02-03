import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class kMeanAlgorithm {


    BufferedImage testImage = null,outputImage=null;
    int width, height;
    Color imageColor = null;
    Color[] random = new Color[3];


    public kMeanAlgorithm() throws IOException {

        //File imageFile = new File("data\\image.jpg");
        //testImage= ImageIO.read(imageFile);
        testImage = ImageIO.read(new File("data\\image.jpg"));
        random[0] = new Color(testImage.getRGB(3, 3));
        random[1] = new Color(testImage.getRGB(4, 4));
        random[2] = new Color(testImage.getRGB(5, 5));

        for (int i = 0; i < testImage.getWidth(); i++)
        {

            for (int j = 0; j < testImage.getHeight(); j++)
            {
                if((i!=3||j!=3)  && (i!=4||j!=4) && (i!=5||j!=5))
                {
                    imageColor = new Color(testImage.getRGB(i, j));
                    float minDistance = getMin(distanceCalculator(random[0],imageColor),
                            distanceCalculator(random[1],imageColor), distanceCalculator(random[2],imageColor));
                    //System.out.println(minDistance);

                    if(minDistance==distanceCalculator(random[0],imageColor)){
                        testImage.setRGB(i,j,Color.GREEN.getRGB());
                    }

                    else if(minDistance==distanceCalculator(random[1],imageColor)){
                        testImage.setRGB(i,j,Color.RED.getRGB());
                    }

                    else{
                        testImage.setRGB(i,j,Color.BLUE.getRGB());
                    }
                }
            }
        }
    }

    public float getMin(float d1, float d2, float d3)
    {
        float min = d1;
        if (d2 < min) {
            min = d2;
        }

        if (d3 < min) {
            min = d3;
        }

        return min;
    }


    public float distanceCalculator(Color c1,Color c2)
    {
        float d=0;
        d= (float) Math.sqrt(  calc(c1.getRed(),c2.getRed()) + calc(c1.getGreen(),c2.getGreen())
                + calc(c1.getBlue(),c2.getBlue()));
        return  d;
    }

    public float calc( float x, float y)
    {
        float distance = 0;

        distance=(x-y)*(x-y);

        return  distance;
    }


    public void output() throws IOException {

        ImageIO.write(testImage,"jpg",new File("data\\output.jpg"));
    }

}
