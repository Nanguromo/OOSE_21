package edu.curtin.comp2003.controller.state;

import edu.curtin.comp2003.rover.EngineSystem;
import edu.curtin.comp2003.rover.Sensors;
import edu.curtin.comp2003.rover.SoilAnalyser;

public class Stopped implements ICommandsState
{
    private EarthHQ context;
    private Sensors sensors;
    private EngineSystem es;
    private SoilAnalyser soilAnalyser;

    private Stopped(EarthHQ context, EngineSystem es, Sensors sensors, SoilAnalyser soilAnalyser)
    {
        this.context = context;
        this.es = es;
        this.sensors = sensors;
        this.soilAnalyser = soilAnalyser;
    }

    public void driveFixedDistance(double distance)
    {
        es.driveFixedDistance(distance);
        context.setState(1);//1 corresponds to Driving state on Map <key, value> pair
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
        soilAnalyser.startAnalysis();
        context.setState(2); //2 corresponds to SoilAnalysisState on Map <key, value> pair
    }
}