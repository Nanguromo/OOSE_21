package edu.curtin.comp2003.controller;

import java.util.*;

import edu.curtin.comp2003.controller.state.ICommandsState;
import edu.curtin.comp2003.controller.observer.CommandFinished;
import edu.curtin.comp2003.rover.SoilAnalyser;
import edu.curtin.comp2003.rover.EngineSystem;
import edu.curtin.comp2003.rover.Sensors;
import edu.curtin.comp2003.controller.state.*;
import edu.curtin.comp2003.rover.EarthComm;


import edu.curtin.comp2003.model.Environment;

public class EarthHQ
{
    private ICommandsState state; //current state
    private Map<Integer, ICommandsState> states; // map of states. 
    //private Map<String,
    private CommandFinished subject; //Subject for Observer pattern implementation. Used to notify the observers who perform return messages

    public EarthHQ()
    {
        // Use factory to inject EngineSystem, Sensors, EarthComm and SoilAnalyser

        //...DEPENDENCY INJECTION TBD...
        EngineSystem engineSystem = new EngineSystem();
        SoilAnalyser soilAnalyser = new SoilAnalyser();
        Sensors sensors = new Sensors();
        EarthComm comms = new EarthComm();
        CommandFinished subject = new CommandFinished(comms);
        Timer timer = new Timer();


        state = new Stopped(this, engineSystem, sensors, soilAnalyser, timer); // assume starting state is with the rover stopped and at a standstill
        states = new HashMap<>();
        states.put(Integer.valueOf(1), new Driving(this, engineSystem, sensors, timer));
        states.put(Integer.valueOf(2), new SoilAnalysisState(this, sensors, soilAnalyser, timer));
        states.put(Integer.valueOf(3), state);


    }

    public void setState(ICommandsState newState)
    {
        this.state = newState;
    }

    public void driveFixedDistance(double distance)
    {
        state.driveFixedDistance(distance);
    }

    public void turn(double angle)
    {
        state.turn(angle);
    }

    public void takePhoto()
    {
        state.takePhoto();
    }

    public void getEnvironmentalStatus()
    {
        state.getEnvironmentalStatus();
    }

    public void startAnalysis()
    {
        state.startAnalysis();
    }

    public void stopDriving()
    {
        state.stopDriving();
    }

    public void setState(int key)
    {
        state = states.get(key);
    }
    /*public ICommandState getState()
    {
        return state;
    }*/
 
    public void notifyObservers(String flag, String msg)
    {
        subject.notifyObservers(flag, msg);
    }

}