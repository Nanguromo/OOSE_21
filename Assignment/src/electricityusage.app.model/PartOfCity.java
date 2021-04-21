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
    private int k;//depth;


    public PartOfCity(String name)
    {
        powerCategory = new HashMap<String, Double>();//hash map over tree map; Find key in O(1) verse O(logn), respectively. Also dont care about ordering; was never explicity stated in assignment
        this.name = name;
        //this.k = depth;
    }

    @Override
    public double getTotalPowerConsumption()
    {
        double totalPowerConsumption = 0.0;

        //Iterate through every (Key, Value) pair in the map
        for(Map.Entry<String, Double> entry : powerCategory.entrySet()) 
        {
            totalPowerConsumption += entry.getValue().doubleValue();//Convert Double Object to double primitive
            //System.out.println(name);
            //System.out.println("made it to here");
        }

        return totalPowerConsumption;
    }

    @Override
    public void setDepth(int depth)
    {
        this.k = depth;
    }

    @Override
    public String toString()
    {
        int indent = 3*(this.k-1); 
        char[] spaces;
        String whitespace;
        if(this.k != 1)
        {
            spaces = new char[indent];
            for(int i = 0; i < spaces.length; i++)
            {
                spaces[i] = ' ';
            }
            whitespace = new String(spaces);
        }
        else
        {
            whitespace = "";
        }
        String display = whitespace+this.name;

        return display;
    }

    @Override
    public String getWriteString()//{return this.name + ",";}
    {
        return "";
    }

    @Override
    public String getWritePowerString()
    {
        String powerStr = ",";
        int size = powerCategory.size();
        int i = 1;
        for(Map.Entry<String, Double> entry : powerCategory.entrySet()) 
        {
            if(i < size)
            {
                powerStr += entry.getKey() + "=" + entry.getValue().doubleValue() + ",";//Convert Double Object to double primitive
                i++;
            }
            else
            {
                powerStr += entry.getKey() + "=" + entry.getValue().doubleValue() + "%n";//Convert Double Object to double primitive
                i++;
            }
        }
        
        return powerStr;
    }

    @Override
    public String getName(){return this.name;}

    public void addPowerCategory(String category, double value)
    {
        powerCategory.put(category, value);
    }

    @Override
    public int getDepth(){return this.k;}
}