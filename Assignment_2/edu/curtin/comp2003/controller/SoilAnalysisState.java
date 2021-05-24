package edu.curtin.comp2003.controller;

import edu.curtin.comp2003.rover.SoilAnalyser;
import edu.curtin.comp2003.rover.Sensors;

public class SoilAnalysisState implements ICommandsState
{ 
    private EarthHQ context;
    private SoilAnalyser soilAnalyser;
    private Sensors sensors;

    public SoilAnalysisState(EarthHQ context, Sensors sensors, SoilAnalyser soilAnalyser)
    {
        this.context = context;
        this.sensors = sensors;
        this.soilAnalyser = soilAnalyser;
    }


    public void driveFixedDistance(double distance)
    {
        throw new IllegalArgumentException("Error: Invalid command. Rover cannot drive whilst performing soil analysis");
    }

    public void turn(double angle)
    {
        throw new IllegalArgumentException("Error: Invalid command. Rover cannot turn whilst performing soil analysis");
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
        throw new IllegalArgumentException("Error: Invalid command. Rover cannot start another soil analysis whilst still performing a soil analysis");
    }
}