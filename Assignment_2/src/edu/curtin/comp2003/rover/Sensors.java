package edu.curtin.comp2003.rover;

public class Sensors
{
    //to simulate different environment readings, these functions return pseudorandom values
    public double readTemperature()
    {
        int negOrPos = (int)(Math.random()*2);

        if(negOrPos == 0)//return a negative temperature
        {
            return -(int)(Math.random()*20);
        }
        else//return a positive temperature
        {
            return (int)(Math.random()*20);
        }
        //return (int)(Math.random()*20);
        //return 0.0;
    }

    public double readVisibility()
    {
        return (int)(Math.random()*10);
    }

    public double readLightLevel()
    {
        return (int)(Math.random()*10000);
    }

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