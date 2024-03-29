package edu.curtin.comp2003.rover;

import java.util.Timer;

public class SoilAnalyser
{
    public void startAnalysis()
    {
        //STUB CODE...
        //Demonstrates functionality (i.e. timing delay) until real implementation is complete
        System.out.println("Soil analysis commencing");

        //let us assume the soil analysis takes an arbitrary time of 5 seconds.
        for(int i = 0; i < 5; i++)
        {
            try
            {
                if(i == 0)
                {                
                    System.out.println("...Performing Soil Analysis...");
                }
                else
                {
                    System.out.print(("."));
                    Thread.sleep(100);
                    System.out.print((".."));
                    Thread.sleep(100);
                    System.out.print(("..."));
                    Thread.sleep(100);



                }
                Thread.sleep(970);
            }
            catch(InterruptedException e)
            {

            }
        }
        System.out.println("");
        //System.out.println("\nSoil Analysis Complete.");
    }

    public byte[] pollAnalysis()
    {
        //STUB CODE...
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