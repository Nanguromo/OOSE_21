//package edu.curtin.comp2003;
import java.util.*;

import edu.curtin.comp2003.controller.EarthHQ;
import edu.curtin.comp2003.rover.*;
/* Author: Wayne Nanguromo
Student ID: 19480060
Created: 26 May, 2021
*/
public class MarsRoverApp 
{
    public static void main(String[] args)
    {
        EarthComm comms = new EarthComm();
        SoilAnalyser sa = new SoilAnalyser();
        EngineSystem es = new EngineSystem();
        Sensors sens = new Sensors();

        PollingThread t1 = new PollingThread(comms);
        EarthHQ hq = new EarthHQ(es, sa, sens, comms); // probably inject EarthHQ's fields here
        //Multithread t2;
       // Multithread t3;

        //...poll everything...

        
        while(true)
        {
            /*String polledCmd = comms.pollCommand();
            String[] buffer = polledCmd.split("\n");*/

            //Thread one received polled commands.
            t1.start();

            //Thread two executes polled commands.

            //if(polledCmd == null)
            if(t1.getPolledCmd() == null)
            {
                comms.sendMessage("! Invalid command received");
            }
            else if(t1.getBufferLength() == 1)
            //else if(buffer.length == 1)//only one command polled
            {
                hq.performPolledCmd(t1.getPolledCmd());
                //t2 = new Multithread(comms, hq, t1.getPolledCmd());
                //t3 = new Multithread(comms, hq);
                //t2.setCmd(t1.getPolledCmd());
                //t2.start();
                //t2.stop();
            }
            else//multiple commands polled
            {
                for(int i = 0; i < t1.getBufferLength(); i++)
                {
                    hq.performPolledCmd(t1.getBufferElement(i));

                    //t3.setCmd(t1.getBufferElement(i));
                    //t3 = new Multithread(comms, hq, t1.getBufferElement(i));
                    //t3.start();
                }
            }

            try
            {
                Thread.sleep(3000);//sleep for three seconds
            }
            catch(InterruptedException e)
            {
                //do something
            }
        }
    }
}
