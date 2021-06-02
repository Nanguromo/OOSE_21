package edu.curtin.comp2003.rover;

public class Sensors
{
    public double readTemperature(){return 0.0;}

    public double readVisibility(){return 0.0;}

    public double readLightLevel(){return 0.0;}

    public byte[] takePhoto()
    {
        //STUB CODE
        //Random size of bytes to simulate different photos taken
        int res = (int)(Math.random()*10);
        while(res == 0)//ensure pseudo-random number is never 0
        {
            res = (int)(Math.random()*10);
            //System.out.println(res);
        }

        return new byte[res];
    }
}