package edu.curtin.comp2003.controller;

import java.util.*;

import edu.curtin.comp2003.rover.Sensors;


public class EnvironmentalState implements ICommandsState
{

    private EarthHQ context;
    private Sensors sensors;

    public EnvironmentalState(EarthHQ context, Sensors sensors)
    {
        this.context = context;
        this.sensors = sensors;
    }

    public void startDriving()
    {
        // this method should never get called in the first place
    }

    public void turn(double angle)
    {
        // this method should never get called in the first place
    }

    public byte[] takePhoto()
    {
        // this method should never get called in the first place
        return null;
    }   

    public Environment getEnvironmentalStatus()
    {
        return new Environment(sensors.readTemperature(), sensors.readVisibility(), sensors.readLightLevel()); //returns new object Environment(double, double, double)
    }

    public void startAnalysis()
    {
        // this method should never get called in the first place
    }

}