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
        //System.out.println(sa);
        
        EarthHQ hq = new EarthHQ(es, sa, sens, comms); // probably inject EarthHQ's fields here
       
        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter Commands for Rover:");
        //...poll everything...

        
        while(true)
        {
            String polledCmd = comms.pollCommand();
            String[] buffer = polledCmd.split("\n");

            //Thread one received polled commands.

            //Thread two executes polled commands.

            if(polledCmd == null)
            {
                comms.sendMessage("! Invalid command received");
            }
            else if(buffer.length == 1)//only one command polled
            {
                hq.performPolledCmd(polledCmd);

            }
            /*else if(polledCmd.equals("waiting"))// waiting for next command
            {

            }*/
            else//multiple commands polled
            {
                for(int i = 0; i < buffer.length; i++)
                {
                    hq.performPolledCmd(buffer[i]);
                }
            }

            try
            {
                Thread.sleep(500);//sleep for half a second
            }
            catch(InterruptedException e)
            {
                //do something
            }
            //polledCmd = "waiting";
            //System.out.println("poll"+polledCmd);
        }
    }
}
