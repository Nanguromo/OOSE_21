package electricityusage.app.model;
import java.util.*;

/*
* Represents Composite Class of the Composite Pattern
*/

public class CompositeCity implements ICity
{
    //ComposisiteCity Objects are not leaf nodes. Hence, cannot hold power categories and power consumption values.

    private List<ICity> children; // List holds ICity objects. Hence, can be either more CompositeCity Objects or PartOfCity Objects (leaf nodes)
    private String name;
    //private int depth;

    public CompositeCity(String name)
    {
        children = new LinkedList<ICity>(); //iterating through a linked list and array list is both O(n). The list serves the purpose of allowing iteration through every child node.
        this.name = name;
        //this.depth;//used for finding depths for randomly generating a tree
    }

    @Override
    public double getTotalPowerConsumption()
    {
        double totalPowerConsumption = 0.0;

        //iterate through every ICity element in the list
        for(ICity currNode : children)
        {
            totalPowerConsumption += currNode.getTotalPowerConsumption(); //total power consumption is calculated recursively.
            //System.out.println(name);
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

    @Override
    public String getName()
    {
        return this.name;
    } 

    public void addSubcity(ICity subCity)
    {
        children.add(subCity);
    }

    public void findComposite(ICity subCity, String key)
    {
        //System.out.println("function reached");
        if(key.equals(this.name))//this is for the root node. If I dont put this here, the tree is never created and the rest of the compositive nodes can connect because the root has no child!
        {
            this.addSubcity(subCity);
        }
        for(ICity currNode: children)
        {
            //System.out.println("and here");
            if(currNode instanceof CompositeCity)
            {
                //System.out.println("instance was reached");
                if(currNode.getName().equals(key))
                {
                    ((CompositeCity)currNode).addSubcity(subCity);
                    //System.out.println("found->"+subCity.getName());
                }
                else
                {
                    ((CompositeCity)currNode).findComposite(subCity, key);//recursively finds the matching key
                }
            }
        }
    }
}