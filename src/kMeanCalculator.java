import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class kMeanCalculator {

    arrayInitializer a= new arrayInitializer();
    updateCentroidsValue u = new updateCentroidsValue();
    BufferedImage testImage = null,outputImage=null;
    int width, height;
    Color imageColor = null;
    Color[] random = new Color[3];
    int c1=0,c2=0,c3=0;
    int[] tSum = new int[3];
    int[] tSum2 = new int[3];
    int[] tSum3 = new int[3];

    int checker=0;



    public  kMeanCalculator() throws IOException {

        //File imageFile = new File("data\\image.jpg");
        //testImage= ImageIO.read(imageFile);
        testImage = ImageIO.read(new File("data\\image.jpg"));
        random[0] = new Color(testImage.getRGB(1, 1));
        random[1] = new Color(testImage.getRGB(2, 2));
        random[2] = new Color(testImage.getRGB(3, 3));

        tSum=a.initializeTheArray(tSum);
        tSum2=a.initializeTheArray(tSum2);
        tSum3=a.initializeTheArray(tSum3);

        while (checker<=3)
        {
            System.out.println("The first red value : "+random[1].getRed());
            System.out.println("The first red value : "+random[1].getGreen());
            System.out.println("The first red value : "+random[1].getBlue());

            for (int i = 0; i < testImage.getWidth(); i++)
            {

                for (int j = 0; j < testImage.getHeight(); j++)
                {
                    if((i!=1||j!=1)  && (i!=2||j!=2) && (i!=3||j!=3))
                    {
                        imageColor = new Color(testImage.getRGB(i, j));
                        float minDistance = getMin(distanceCalculator(random[0],imageColor),
                                distanceCalculator(random[1],imageColor), distanceCalculator(random[2],imageColor));
                        //System.out.println(minDistance);

                        if(minDistance==distanceCalculator(random[0],imageColor))
                        {
                            tSum =u.addSum(imageColor,tSum);
                            c1++;
                            testImage.setRGB(i,j,Color.GREEN.getRGB());
                        }

                        else if(minDistance==distanceCalculator(random[1],imageColor)){
                            tSum2 =u.addSum(imageColor,tSum2);
                            c2++;
                            testImage.setRGB(i,j,Color.RED.getRGB());
                        }

                        else{
                            tSum3 =u.addSum(imageColor,tSum3);
                            c3++;
                            testImage.setRGB(i,j,Color.BLUE.getRGB());
                        }
                    }
                }
            }
            random[0]=u.averageCalculator(tSum,c1);
            random[1]=u.averageCalculator(tSum2,c2);
            random[2]=u.averageCalculator(tSum3,c3);

            System.out.println("The updated red value : "+random[1].getRed());
            System.out.println("The updated red value : "+random[1].getGreen());
            System.out.println("The updated red value : "+random[1].getBlue());

            checker++;
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
