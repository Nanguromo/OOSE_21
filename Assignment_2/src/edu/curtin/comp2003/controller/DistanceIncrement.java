package edu.curtin.comp2003.controller;

import edu.curtin.comp2003.rover.EngineSystem;


/**
 * Encapsulating Class. Used to wrap distance value and decrement within this class. This class was necessary due to the new TimerClass() needing a
    a final value for distance.
 */
public class DistanceIncrement
{
    private double currDist;
    private EngineSystem es; 
    private boolean flag;

    public DistanceIncrement(double currDist, EngineSystem es)
    {
        this.currDist = currDist;
        this.es = es;
        this.flag = true;
    }
    public void tick()
    {
        if(currDist >= 0.5)
        {
            es.driveFixedDistance(currDist);
            currDist = currDist - 0.5;
        }
        else
        {
            es.driveFixedDistance(0.0);
            flag = false;
        }
        //System.out.println(currDist);

    }

    public double getCurrDist()
    {
        return currDist;
    }

    public boolean getFlag()
    {
        return flag;
    }
}