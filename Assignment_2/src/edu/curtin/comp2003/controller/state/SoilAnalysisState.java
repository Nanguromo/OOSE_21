package edu.curtin.comp2003.controller.state;

import edu.curtin.comp2003.rover.SoilAnalyser;
import edu.curtin.comp2003.rover.Sensors;
import edu.curtin.comp2003.controller.EarthHQ;
import java.util.*;

/**
 * Rover is performing soil analysis. This was harder to determine if it should be a state, but it makes sense,
 * as the rover can only perform certain actions whilst doing soil analysis. Consequently, it was made a state.
 */
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

    @Override
    public void driveFixedDistance(double distance)
    {
        context.notifyObservers("!", "Error: Invalid command. Rover cannot drive whilst performing soil analysis");
    }

    @Override
    public void turn(double angle)
    {
        context.notifyObservers("!", "Error: Invalid command. Rover cannot turn whilst performing soil analysis");
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
        context.notifyObservers("!", "Error: Invalid command. Rover cannot start another soil analysis whilst still performing a soil analysis");
    }

    @Override
    public void stopDriving()
    {
        context.notifyObservers("!", "Error: Invalid command. Rover cannot be stopped. It is already at a stand-still, whilst performing a soil analysis");
    }
}