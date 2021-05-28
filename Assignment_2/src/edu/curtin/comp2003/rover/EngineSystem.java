package edu.curtin.comp2003.rover;

public class EngineSystem
{
    public void startDriving(){}

    public void stopDriving(){}

    public void turn(double angle){}

    public double getDistanceDriven(){return 0.0;}

    public void driveFixedDistance(double distance){System.out.println("Rover is currently " + distance + " from target.");}; // my addition. startDriving() causes the rover to drive and keep going forever UNTIL told to stopDriving.
}