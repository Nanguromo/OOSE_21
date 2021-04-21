package electricityusage.app.model;
import java.util.*;
/*
* Interface for city's power consumption. Serves purpose of allowing the implementation of the composite pattern
*/

public interface ICity
{
    public double getTotalPowerConsumption();

    //displaying the tree should be done in View. 

    public String toString();//gets all the leaf nodes power consumptions, name, etc in a formatted String that is it to be passed to the Controller who then passed the String to the View

    public String getName();

    public void setDepth(int depth);

    public int getDepth();


}