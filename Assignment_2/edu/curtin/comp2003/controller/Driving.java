package edu.curtin.comp2003.controller;

import java.util.*;

import edu.curtin.comp2003.controller.EarthHQ;
import edu.curtin.comp2003.rover.Sensors;
import edu.curtin.comp2003.rover.EngineSystem;

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

    public void driveFixedDistance(double distance)
    {
        es.driveFixedDistance(distance);
    }

    public void turn(double angle)
    {
        es.turn(angle);
    }

    public byte[] takePhoto()
    {
        return sensors.takePhoto();
    }

    public Environment getEnvironmentalStatus()
    {
        return new Environment(sensors.readTemperature(),sensors.readVisibility(), sensors.readLightLevel());
    }

    public void startAnalysis()
    {
        throw new IllegalArgumentException("Error: Invalid command. Rover cannot start soil analysis whilst driving");
    }

    public void stopDriving()
    {
        es.stopDriving();
        context.setState(3);//3 corresponds to Stopped State, where rover is at a standstill
    }
}