import java.awt.*;

public class updateCentroidsValue
{
    int red=0,blue=0,green=0;
    public int[] addSum(Color image, int [] tSum)
    {
        tSum[0] += image.getRed();
        tSum[1] += image.getGreen();
        tSum[2] += image.getBlue();

        return tSum;
    }



    public Color averageCalculator(int[] tSum, int counter)
    {
        int []avg=new int[3];
        avg[0]=tSum[0]/counter;
        avg[1]=tSum[1]/counter;
        avg[2]=tSum[2]/counter;

        Color c=new Color(avg[0],avg[1],avg[2]);
        return  c;
    }
}
