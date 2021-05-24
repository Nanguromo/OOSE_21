package edu.curtin.comp2003.controller;

//By creating a command state, this ensures that the command constraints specified in Section "4.1 Commands from Earth" are satisfied without using any if/else/cases.
//Each command function can can only called if-and-only-if the rover is in its corresponding state
public interface ICommandsState
{
    //public void startDriving(); // D
    public void driveFixedDistance(double distance);

    public void turn(double angle);// T
    
    public byte[] takePhoto();// P
    
    public Environment getEnvironmentalStatus(); // E. Environment is a simple data class holding temperature, visibility and light levels in its class fields

    public void startAnalysis(); // S

    public void stopDriving();
}
 