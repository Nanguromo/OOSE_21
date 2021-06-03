package edu.curtin.comp2003.controller;

import java.util.*;

import edu.curtin.comp2003.controller.state.ICommandsState;
import edu.curtin.comp2003.controller.observer.CommandFinished;
import edu.curtin.comp2003.rover.SoilAnalyser;
import edu.curtin.comp2003.rover.EngineSystem;
import edu.curtin.comp2003.rover.Sensors;
import edu.curtin.comp2003.controller.state.*;
import edu.curtin.comp2003.rover.EarthComm;

/**
 * This is the main controller. It has the responsibility of being "Context" for The State Pattern, whilst simultaneously being the
 * "Subject" for The Observer Pattern. Hence, this class is essentially the bridge between these two Inheritance Hierarchies.
 */
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

    //Note how all the class objects are injected here (aside from standard Java API objects)
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

    /**
     * This class also runs its own thread parallel to the polling command thread. This thread was necessary to have
     * the rover continously check for when the visibility went below 4km or above 5km. This is checked every 10 seconds.
     */
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

    /**
     * Start method created a thread here if it has not already been created. It calls run() which is looped FOREVER
     *  until the Rover ceases to exist :(
     */
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

    /**
    * Used to set state by accepting an ICommandsState object
    */
    public void setState(ICommandsState newState)
    {
        this.state = newState;
    }

    /**
     * This method drives a fixed distance received from earth. The current rover API has a function for drive
     * but it does not accept a distance. This was odd, and I was not sure how to implement the drive distance functionality
     * otherwise, hence, I created this method and similarly, added it to the Rover's API.
     */
    public void driveFixedDistance(double distance)
    {
        state.driveFixedDistance(distance);
    }

    /**
     * Turns a specified angle, instantaneously.
     */
    public void turn(double angle)
    {
        state.turn(angle);
    }

    /** Takes a photo. A byte[] is received and the rover prosses this into a string which is displayed to the
     * scientists on Earth.
     */
    public void takePhoto()
    {
        state.takePhoto();
    }

    /** The rover sends the Environmental status of Mars to Earth for display. */
    public void getEnvironmentalStatus()
    {
        state.getEnvironmentalStatus();
    }

    /** Starts the soil analysis, and finishes after some specified time */
    public void startAnalysis()
    {
        state.startAnalysis();
    }

    /**Rover stops driving */
    public void stopDriving()
    {
        state.stopDriving();
    }

    /** Also sets the state of the Rover, but instead of receiving an ICommandState object,
     * it accepts an integer which corresonds to a key and its respective ICommandState object on a map.
     */
    public void setState(int key)
    {
        state = states.get(key);
    }
 
    /**Notifies observers that a command is finished */
    public void notifyObservers(String flag, String msg)
    {
        subject.notifyObservers(flag, msg);
    }

    /**MarsRoverApp calls this to perform a (or many) polled command(s) received from Earth (if it was valid) */
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