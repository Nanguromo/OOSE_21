package edu.curtin.comp2003.rover;

import edu.curtin.comp2003.rover.EngineSystem;


/**
 * Encapsulating Class. Used to wrap distance value and decrement within this class. This class was necessary due to the new TimerClass() needing a
    a final value for distance.
 */
public class DistanceIncrement
{
    private double currDist;
    private EngineSystem es; 

    public DistanceIncrement(double currDist, EngineSystem es)
    {
        this.currDist = currDist;
        this.es = es;
    }
    public void tick()
    {
        if(currDist >= 0.5)
        {
            currDist = currDist - 0.5;
            es.driveFixedDistance(currDist);
    
        }
    }

    public double getCurrDist()
    {
        return currDist;
    }
}