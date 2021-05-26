//package edu.curtin.comp2003;
import java.util.*;

import edu.curtin.comp2003.controller.EarthHQ;

/* Author: Wayne Nanguromo
Student ID: 19480060
Created: 26 May, 2021
*/
public class MarsRoverApp
{
    public static void main(String[] args)
    {
        EarthHQ hq = new EarthHQ(); // probably inject EarthHQ's fields here
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Commands for Rover:");
        String input;
        //...poll everything...
        while(true)
        {
            input = sc.nextLine();
            String[] split = input.split(" ");
            if(split.length == 1)
            {
                switch(input)
                {
                    case "P":
                        hq.takePhoto();
                        break;
                    case "E":
                        hq.getEnvironmentalStatus();
                        break;
                    case "S":
                        hq.startAnalysis();
                        break;
                    default:
                        System.out.println("Error: Invalid command!");
                }
            }
            else if(split.length == 2)
            {
                switch(split[0])
                {
                    case "D":
                        hq.driveFixedDistance(Double.parseDouble(split[1]));
                        break;
                    case "T":
                        hq.turn(Double.parseDouble(split[1]));
                        break;
                    default:
                        System.out.println("Error: Invalid command!");
                }
            }
            else
            {
                System.out.println("Error: Invalid command!");
            }
            try
            {
                Thread.sleep(500);//sleep for half a second
            }
            catch(InterruptedException e)
            {
                //do something
            }
        }
    }
}
