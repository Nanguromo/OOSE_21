package edu.curtin.comp2003.controller.state;

import java.beans.Encoder;
import java.util.*;
import edu.curtin.comp2003.rover.EngineSystem;
import edu.curtin.comp2003.rover.Sensors;
import edu.curtin.comp2003.rover.SoilAnalyser;
import edu.curtin.comp2003.controller.EarthHQ;

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
        this.timer = timer;
    }

    @Override
    public void driveFixedDistance(double distance)
    {
        es.driveFixedDistance(distance);
        context.setState(1);//1 corresponds to Driving state on Map <key, value> pair
        while(distance >= 0.001)
        {
            //distance = distance - 0.5; // rover travels at 0.5 metres per 0.1 seconds, i.e. 5 metres per second
            //INSERT TIMING CODE....
            timer.schedule(new TimerTask(){
                @Override
                public void run()
                {
                    es.driveFixedDistance(distance);
                }
            }, 0, 100);
        }
        stopDriving();
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
        soilAnalyser.startAnalysis();
        context.setState(2); //2 corresponds to SoilAnalysisState on Map <key, value> pair
    }

    @Override
    public void stopDriving()
    {
        context.notifyObservers("!", "Error: Invalid command. Rover cannot be stopped. It is already at a stand-still");
    }
}