package electricityusage.app.model;
import java.util.*;

/*
* Represents Composite Class of the Composite Pattern
*/

public class CompositeCity implements ICity
{
    //ComposisiteCity Objects are not leaf nodes. Hence, cannot hold power categories and power consumption values.

    private List<ICity> children; // List holds ICity objects. Hence, can be either more CompositeCity Objects or PartOfCity Objects (leaf nodes)

    public CompositeCity()
    {
        children = new LinkedList<ICity>(); //iterating through a linked list and array list is both O(n). The list serves the purpose of allowing iteration through every child node.
    }

    @Override
    public double getTotalPowerConsumption()
    {
        double totalPowerConsumption = 0.0;

        //iteratte through every ICity element in the list
        for(ICity currNode : children)
        {
            totalPowerConsumption = currNode.getTotalPowerConsumption(); //total power consumption is calculated recursively.
        }

        return totalPowerConsumption;
    }

    @Override
    public String toString()
    {
        //come back to this later when I know what View is doing

        //when I do this method:
        //remember to use recursion. This means I don't kneed a class field String as the final recurse will return the resultant String
        //to the Controller who can that pass that to the View
        return "";
    }

    public void addSubcity(ICity subCity)
    {
        children.add(subCity);
    }
}