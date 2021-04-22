package electricityusage.app.model;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
    private String writeString;

    public CompositeCity(String name)
    {
        this.children = new LinkedList<ICity>(); //iterating through a linked list and array list is both O(n). The list serves the purpose of allowing iteration through every child node.
        this.name = name;
        this.display = "";
        this.writeString = "";
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
        
        return display;
    }

    @Override
    public String getName(){return this.name;} 

    @Override
    public int getDepth(){return this.k;}

    @Override
    public String getWriteString()
    {
        //writeString = this.name + "%n";

        if(this.k == 1)//at root. First line of write file
        {
            writeString = this.name + "%n";
        }/*
        else
        {
            writeString = this.name + ",";
        }*/

        for(ICity currNode: children)
        {
            writeString +=  currNode.getName() + "," + this.name + currNode.getWritePowerString();//+ "%n";
        }
        for(ICity currNode: children)
        {
            writeString += currNode.getWriteString();
        }
        //writeString += "%n";

        return writeString;
    }

    @Override 
    public String getWritePowerString()
    {
        return "%n";
    }

    public void addSubcity(ICity subCity)
    {
        children.add(subCity);
    }


    @Override
    public void generateCity(int maxDepth, int ctr)
    {
        //this method is called on the root node from Generate Class
        //int k = ThreadLocalRandom.current().nextInt(1, 5+1);//5+1 is the upper bound limit for the random generator. It is not inclusive, hence 5+1 means the limit is 5 which is <6.

        if(maxDepth == 1)//tree consists of only root node.
        {
            return;
        }

        if(this.k < maxDepth - 1)
        {
            int numChildred = ThreadLocalRandom.current().nextInt(2,5+1);//upper bound is 5. Random num of children = [2,5]
            for(int i = 0; i < numChildred; i++)
            {
                int randChild = ThreadLocalRandom.current().nextInt(1,2+1);// upper bound is 1. This one is 50-50 change. If 1-> Create a leaf node child. If 2-> Create a composite node child
                if(randChild == 1)//create leaf
                {
                    PartOfCity temp = new PartOfCity("Building" + ctr);
                    temp.setDepth(this.k + 1);
                    temp.addPowerCategory("dm", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                    temp.addPowerCategory("da", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                    temp.addPowerCategory("de", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                    temp.addPowerCategory("em", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                    temp.addPowerCategory("ea", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                    temp.addPowerCategory("ee", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                    temp.addPowerCategory("h", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                    temp.addPowerCategory("s", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));

                    this.addSubcity(temp);
                    ctr++;

                }
                else//create composite
                {
                    CompositeCity temp = new CompositeCity("Subcity" + ctr);
                    temp.setDepth(this.k + 1);
                    this.addSubcity(temp);

                    ctr++;
                }
            }

            for(ICity currNode: children)//recursively goes through created children and adds new children until max depth reached
            {
                currNode.generateCity(maxDepth, ctr);
            }
        }  
        
        if(this.k == maxDepth - 1)//when depth of k-1 is reached, all composite nodes at depth k-1, must only have leaf node children (PartOfCity Objects)    
        {
            int numChildred = ThreadLocalRandom.current().nextInt(2,5+1);//upper bound is 5. Random num of children = [2,5]
            for(int i = 0; i < numChildred; i++)
            {
                int randChild = ThreadLocalRandom.current().nextInt(1,2+1);
                PartOfCity temp = new PartOfCity("Building" + ctr);
                
                temp.setDepth(this.k + 1);

                temp.addPowerCategory("dm", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                temp.addPowerCategory("da", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                temp.addPowerCategory("de", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                temp.addPowerCategory("em", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                temp.addPowerCategory("ea", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                temp.addPowerCategory("ee", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                temp.addPowerCategory("h", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));
                temp.addPowerCategory("s", ThreadLocalRandom.current().nextDouble(0.0, 1000.1));

                this.addSubcity(temp);
                ctr++;
            }
        }

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