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
    private int k; //depth
    private String display;

    public CompositeCity(String name)
    {
        this.children = new LinkedList<ICity>(); //iterating through a linked list and array list is both O(n). The list serves the purpose of allowing iteration through every child node.
        this.name = name;
        this.display = "";
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
        display += whitespace+this.name;
        //display += String.format("%1$" +indent+"s", this.name);
        
        //display += "\n";
        //display += this.getName();
        for(ICity currNode : children)
        {
            //display += String.format("%1$" +indent*(currNode.getDepth()-1), currNode.getName());
            display += "\n";
            display += currNode.toString();
        }
        //this.toStr = display;
        //come back to this later when I know what View is doing

        //when I do this method:
        //remember to use recursion. This means I don't kneed a class field String as the final recurse will return the resultant String
        //to the Controller who can that pass that to the View
        return display;
    }

    @Override
    public String getName(){return this.name;} 

    @Override
    public int getDepth(){return this.k;}

    public void addSubcity(ICity subCity)
    {
        children.add(subCity);
    }

    public void addComposite(ICity subCity, String key)
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
                    ((CompositeCity)currNode).addComposite(subCity, key);//recursively finds the matching key
                }
            }
        }
    }

    public int getCompositeDepth(String key)
    {
        int compDepth = 0;
        if(key.equals(this.name))//this is for the root node. If I dont put this here, the tree is never created and the rest of the compositive nodes can connect because the root has no child!
        {
            compDepth = this.k;
        }
        for(ICity currNode: children)
        {
            //System.out.println("and here");
            if(currNode instanceof CompositeCity)
            {
                //System.out.println("instance was reached");
                if(currNode.getName().equals(key))
                {
                    compDepth = currNode.getDepth();
                    //System.out.println("found->"+subCity.getName());
                }
                else
                {
                    ((CompositeCity)currNode).getCompositeDepth(key);//recursively finds the matching key
                }
            }
        }
        return compDepth;
    }
}