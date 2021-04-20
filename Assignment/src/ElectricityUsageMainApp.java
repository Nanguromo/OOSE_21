import electricityusage.app.model.*;
import electricityusage.app.view.*;
import electricityusage.app.controller.*;
import java.util.*;
import java.io.*;

public class ElectricityUsageMainApp
{
    /*Contains main method for city's electricity usage application. 
    *
    */
    /*Author: Wayne Nanguromo
    Student ID: 19480060
    Created: 18 April, 2021
    */
    
    public static void main(String [] args)
    {
        //Command line arguments should be taken and passed into View to allow it do to View things...
        //Model also takes args to do perform operations on the data, based on args received
        //Controller serves as a medium between the Model and View. Model and View never directly interact. All interactions occur through the Controller
        //View is very simply due to not requiring a GUI. All it does it print to terminal
        //Model utilises The Composite Pattern and The Strategy Pattern to perform operations on the data and to identify what operation needs to be performed based on the command line args

        //This method creates the MVC Objects and operates the program through the Controller's functions

        ElectricityModel model = new ElectricityModel(args);
        ElectricityView view = new ElectricityView(args);

        ElectricityController controller = new ElectricityController(model, view);

        controller.doFirstOption();
        controller.doSecondOption();
    }
}