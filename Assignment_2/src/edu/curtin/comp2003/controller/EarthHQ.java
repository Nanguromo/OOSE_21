package edu.curtin.comp2003.controller;

import java.util.*;

import edu.curtin.comp2003.controller.state.ICommandsState;
import edu.curtin.comp2003.controller.observer.CommandFinished;
import edu.curtin.comp2003.rover.SoilAnalyser;
import edu.curtin.comp2003.rover.EngineSystem;
import edu.curtin.comp2003.rover.Sensors;
import edu.curtin.comp2003.controller.state.*;
import edu.curtin.comp2003.rover.EarthComm;

public class EarthHQ implements Runnable
{
    private ICommandsState state; //current state
    private Map<Integer, ICommandsState> states; // map of states. 
    private CommandFinished subject; //Subject for Observer pattern implementation. Used to notify the observers who perform return messages
    private EngineSystem engineSystem;
    private SoilAnalyser soilAnalyser;
    private Sensors sensors;
    private EarthComm comms;
    private Thread t;

    public EarthHQ(EngineSystem engineSystem, SoilAnalyser soilAnalyser, Sensors sensors, EarthComm comms, CommandFinished subject)
    {
        //A factory is not required for my design. Unecessary code required
        //Instead, objects are injected into each class by having each non-java API objects being created in the main (MarRoverApp) & injected
        this.engineSystem = engineSystem;
        this.soilAnalyser = soilAnalyser;
        this.sensors = sensors;
        this.comms = comms;
        this.subject = subject;

        state = new Stopped(this, engineSystem, sensors, soilAnalyser);// assume starting state is with the rover stopped and at a standstill
        states = new HashMap<>();
        states.put(Integer.valueOf(1), new Driving(this, engineSystem, sensors));
        states.put(Integer.valueOf(2), new SoilAnalysisState(this, sensors, soilAnalyser));
        states.put(Integer.valueOf(3), state);
    }

    @Override
    public void run()
    {
        while(true)//checks every 10 seconds
        {
            if(sensors.readVisibility() < 4 || sensors.readVisibility() > 5)//if visibility ever crosses (below or above) the thresholds of 4 and 5km, respecively
            {
                state.getEnvironmentalStatus();// rover can perform environmental status analysis in any state, hence, state does not matter
            }

            try
            {
                Thread.sleep(10000);
            }
            catch(InterruptedException e)
            {

            }
        }
    }

    //This class runs its own thread for run() which continuously checks the environment visiblity levels.
    public void start()
    {
        if(t == null)
        {
            t = new Thread(this);
        }
        try
        {
            t.start();
        }
        catch(IllegalThreadStateException e)
        {
            //state already started
        }
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

    public void performPolledCmd(String cmd)
    {
        String[] split = cmd.split(" ");

        if(split.length == 1) //delimiter couldn't find anything to split -> Length is 1
        {
            switch(cmd)
            {
                case "P":
                    this.takePhoto();
                    break;
                case "E":
                    this.getEnvironmentalStatus();
                    break;
                case "S":
                    this.startAnalysis();
                    break;
                default:
                    comms.sendMessage("! Invalid command received");

            }
        }
        else if(split.length == 2)//delimiter found a space. Expect two variables
        {
            switch(split[0])
            {
                case "D":
                    this.driveFixedDistance(Double.parseDouble(split[1]));
                    break;
                case "T":
                    this.turn(Double.parseDouble(split[1]));
                    break;
                default:
                    comms.sendMessage("! Invalid command received");
            }
        }
        else//error. This shouldn't happen
        {
            comms.sendMessage("! Invalid command received");
        }
    }
    
}