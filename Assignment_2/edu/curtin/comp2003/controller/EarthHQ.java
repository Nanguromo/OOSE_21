package edu.curtin.comp2003.controller;

import java.util.*;

import edu.curtin.comp2003.controller.ICommandsState;
import edu.curtin.comp2003.rover.SoilAnalyser;

public class EarthHQ
{
    private ICommandsState state; //current state
    private Map<Integer, ICommandsState> states; // map of states. 
    //private Map<String,

    public EarthHQ()
    {
        // Use factory to inject EngineSystem, Sensors, EarthComm and SoilAnalyser
        state = null;
        states = new HashMap<>();
        states.put(Integer.valueof(1), new Driving(this, engineSystem);
        states.put(Integer.valueof(2), new SoilAnalysisState(this, soilAnalyser));
        states.put(Integer.valueof(3), new Stopped(this, sensors));


    }

    public void setState(ICommandsState newState)
    {
        this.state = newState;
    }

    public void startDriving()
    {
        state.startDriving();
    }

    public void turn(double angle)
    {
        state.turn(angle);
    }

    public byte[] takePhoto()
    {
        return state.takePhoto();
    }

    public Environment getEnvironmentalStatus()
    {
        return state.getEnvironmentalStatus();
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
 
}