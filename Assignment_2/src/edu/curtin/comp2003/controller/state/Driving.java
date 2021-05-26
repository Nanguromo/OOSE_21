package edu.curtin.comp2003.controller.state;

import java.util.*;

import edu.curtin.comp2003.controller.EarthHQ;
import edu.curtin.comp2003.rover.Sensors;
import edu.curtin.comp2003.rover.EngineSystem;

public class Driving implements ICommandsState
{
    private EarthHQ context;
    private EngineSystem es;
    private Sensors sensors;
    private Timer timer;

    public Driving(EarthHQ context, EngineSystem es, Sensors sensors, Timer timer)
    {
        this.context = context;
        this.es = es;
        this.sensors = sensors;
        this.timer = timer;
    }

    @Override
    public void driveFixedDistance(double distance)
    {
        //while(Math.abs(distance - (int)distance) < 0.001 )//while d
        while(distance >= 0.001)
        {
            //INSERT TIMING CODE....\
           
            timer.schedule(new TimerTask(){
                @Override
                public void run()
                {
                    //distance = distance - 0.5; // rover travels at 0.5 metres per 0.1 seconds, i.e. 5 metres per second   
                    es.driveFixedDistance(distance);
                }
            }, 0, 100);
            
        }
        
        /*context.setState(3);//3 corresponds to Stopped State, where rover is at a stand-still
        context.notifyObservers("D", "");*/
        stopDriving();
        //timer.cancel();
    }

    @Override
    public void turn(double angle)
    {
        es.turn(angle);
    }

    @Override
    public void takePhoto()
    {
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
        context.notifyObservers("!", "Error: Invalid command. Rover cannot start soil analysis whilst driving");
        //throw new IllegalArgumentException("Error: Invalid command. Rover cannot start soil analysis whilst driving");
    }

    @Override
    public void stopDriving()
    {
        es.stopDriving();
        context.setState(3);//3 corresponds to Stopped State, where rover is at a standstill
        context.notifyObservers("D", "");
    }
}