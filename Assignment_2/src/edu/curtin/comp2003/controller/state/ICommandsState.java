package edu.curtin.comp2003.controller.state;

//By creating a command state, this ensures that the command constraints specified in Section "4.1 Commands from Earth" are satisfied without using any if/else/cases.
//Each command function can can only called if-and-only-if the rover is in its corresponding state
//Using The State Pattern to address this criteria provides an elegant solution as different commands can easily be implemented and only implemented IFF
// they are in their corresponding state, otherwise, "! ..." error message is sent, as speciified by the Problem sheet.

/**
 * Interface for the states of the rover. This interface is used to implement The State Pattern.
 * The three states are: Driving, Stopped, and SoilAnalysisState.
 */
public interface ICommandsState
{
    //public void startDriving(); // D

    /** All these functions wrap around the Rover API's functions found within their respective classes. The wrappers are needed because these functions when implemented 
     * do additional things; not only just call the Rover API functions.
     */
    public void driveFixedDistance(double distance);

    public void turn(double angle);// T
    
    /**
     * 
     * Encodes a Byte[] to a String using the Base64 encoding scheme
     */
    public void takePhoto();// P
    
    public void getEnvironmentalStatus(); // E. Environment is a simple data class holding temperature, visibility and light levels in its class fields

    public void startAnalysis(); // S

    public void stopDriving();
}
 