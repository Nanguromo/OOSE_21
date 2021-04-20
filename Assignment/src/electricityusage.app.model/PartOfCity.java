package electricityusage.app.model;
import java.util.*;

/*
*  Represents Leaf Nodes of the Composite Pattern
*/
public class PartOfCity implements ICity
{
    //only leaf nodes hold power consumption categories
    private Map<String, Double> powerCategory; /*abbreviations are the key (String). Double object holds the power consumption (double) for a specific category.
                                                this is done only categories with values can be set, i.e. they actually exist*/
    private String name;

    public PartOfCity(String name)
    {
        powerCategory = new HashMap<String, Double>();//hash map over tree map; Find key in O(1) verse O(logn), respectively. Also dont care about ordering; was never explicity stated in assignment
        this.name = name;
    }

    @Override
    public double getTotalPowerConsumption()
    {
        double totalPowerConsumption = 0.0;

        //Iterate through every (Key, Value) pair in the map
        for(Map.Entry<String, Double> entry : powerCategory.entrySet()) 
        {
            totalPowerConsumption += entry.getValue().doubleValue();//Convert Double Object to double primitive
        }

        return totalPowerConsumption;
    }

    @Override
    public String toString()
    {
        //come back to this later when I know what View is doing
        return "";

    }

    @Override
    public String getName(){return this.name;}

    

}