package edu.curtin.comp2003.controller.state;

import java.beans.Encoder;
import java.util.*;
import edu.curtin.comp2003.rover.EngineSystem;
import edu.curtin.comp2003.rover.Sensors;
import edu.curtin.comp2003.rover.SoilAnalyser;
import edu.curtin.comp2003.controller.EarthHQ;
import edu.curtin.comp2003.rover.DistanceIncrement;

public class Stopped implements ICommandsState
{
    private EarthHQ context;
    private Sensors sensors;
    private EngineSystem es;
    private SoilAnalyser soilAnalyser;
    private Timer timer;

    public Stopped(EarthHQ context, EngineSystem es, Sensors sensors, SoilAnalyser soilAnalyser, Timer timer)
    {
        this.context = context;
        this.es = es;
        this.sensors = sensors;
        this.soilAnalyser = soilAnalyser;
        this.timer = timer;// new Timer();
    }

    @Override
    public void driveFixedDistance(double distance)
    {
        //while(Math.abs(distance - (int)distance) < 0.001 )//while 
        DistanceIncrement di = new DistanceIncrement(distance, es); //Encapsulating class used to change distance while rover travels
        //without this class, the anonymous new TimerTask() will have errors due to distance changing within it. Hence, to treat it as final, wrap distance
        //in its own encapsulating task
        
        //while(!(di.getCurrDist() < 0.0) && di.getFlag())
        while(di.getFlag())
        {
            //INSERT TIMING CODE....\
           
            /*timer.schedule(new TimerTask(){
                @Override
                public void run()
                {
                    //distance = distance - 0.5; // rover travels at 0.5 metres per 0.5 seconds 
                    //es.driveFixedDistance(distance);

                    //...INSERT REFERENCE HERE... (STACK OVERFLOW)
                    di.tick();
                }
            }, 0, 1000);*/
            //System.out.println(di.getCurrDist());

            es.driveFixedDistance(distance);
            di.tick();
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                
            }
        }

        /*try
        {
            //timer.cancel();
            //timer.purge();
        }
        catch(IllegalStateException e)
        {

        }

        System.out.println("here reached");*/
        context.notifyObservers("D", "");

    }

    @Override
    public void turn(double angle)
    {
        es.turn(angle);
    }

    @Override
    public void takePhoto()
    {
        //INSERT JAVA DOCS REFERENCE HERE --
        String msg = Base64.getEncoder().encodeToString(sensors.takePhoto()); //encodes a Byte[] sent from sensors on the rover
        context.notifyObservers("P", msg);
    }

    @Override
    public void getEnvironmentalStatus()
    {
        String msg = sensors.readTemperature() + " " + sensors.readVisibility() + " " + sensors.readLightLevel();
        context.notifyObservers("E", msg);
    }

    @Override
    public void startAnalysis()
    {
        //System.out.println((soilAnalyser));   
        soilAnalyser.startAnalysis();
        context.setState(2);//the driver is stopped, but its current state is now SoilAnalysis state as it is performing a soil analysis

        //the rover now knows it cannot drive or perform any commands that are restricted by the soil analysis state

        context.setState(3); //3 corresponds to Stopped on Map <key, value> pair
        //set state back to stopped, when soil analysis is finished
        context.notifyObservers("S", "");
        //once soil analysis is complete, the rover is back in a stopped state.
    }

    //this does not need to be here
    @Override
    public void stopDriving()
    {
        context.notifyObservers("!", "Error: Invalid command. Rover cannot be stopped. It is already at a stand-still");
    }
}