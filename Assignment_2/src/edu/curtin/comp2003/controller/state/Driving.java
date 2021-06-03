package edu.curtin.comp2003.controller.state;

import java.util.*;

import edu.curtin.comp2003.controller.EarthHQ;
import edu.curtin.comp2003.rover.Sensors;
import edu.curtin.comp2003.rover.EngineSystem;
import edu.curtin.comp2003.rover.DistanceIncrement;


public class Driving implements ICommandsState
{
    private EarthHQ context;
    private EngineSystem es;
    private Sensors sensors;

    public Driving(EarthHQ context, EngineSystem es, Sensors sensors)
    {
        this.context = context;
        this.es = es;
        this.sensors = sensors;
    }


    @Override
    public void driveFixedDistance(double distance)
    {
        DistanceIncrement di = new DistanceIncrement(distance, es); //Encapsulating class used to change distance while rover travels
        //without this class, the anonymous new TimerTask() will have errors due to distance changing within it. Hence, to treat it as final, wrap distance
        //in its own encapsulating task
        
        //DO NOT have to set the state to "driving" state as this class is already the driving state.
        while(di.getFlag())
        {
            es.driveFixedDistance(distance);
            di.tick();
            try
            {
                Thread.sleep(50);
            }
            catch(InterruptedException e)
            {

            }
        }

        context.notifyObservers("D", "");//notify obsevers that rover has finished driving
        context.setState(3); //set the state back to 3 (Stopped) as the rover has finished driving and has reached its destination

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
    }

    @Override
    public void stopDriving()
    {
        es.stopDriving();
        context.setState(3);//3 corresponds to Stopped State, where rover is at a standstill
        context.notifyObservers("D", "");
    }
}