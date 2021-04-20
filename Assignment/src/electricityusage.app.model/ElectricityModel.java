package electricityusage.app.model;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom; //used for generating random numbers


/*
*   ElectricityModel is responsible for the Model of the MVC implementation of the Electricity Usage Application
*/


//Model should deal with data only and operations done to data. Can only communicate with the Controller directly; never the View
public class ElectricityModel
{
    private CompositeCity compCity;
    private int ctr; //for making unique leaf node names
    private int ctr2; // for making unique composite node names

    public ElectricityModel()
    {
        this.compCity = new CompositeCity("Perth");
        ctr = 1;
        ctr2 = 1;
    }

    public void addSubcity(ICity subCity) //wrapper method. CompositeCity is directly a part of the internal workings of this class. Hence, using a wrapper method
    {
        compCity.addSubcity(subCity); 
    }

    /*public CompositeCity generate()//generates random city and subcities. For each leaf node, all power categories are randomdly generated between (0.0 kW, 1000.0 kW) 
    {
        // 1. Randomly generate the tree depth, k, between 1 and 5 inclusive. k = 1 means the tree consists only of a root node.
        int k = ThreadLocalRandom.current().nextInt(1, 5+1); //nextInt(origin, bound). bount is exclusive, hence, make it 6 so that it takes 5 as its max!
        int numChildNodes;
        int randLeafOrComposite;
        
        for(int i=1; i<k; i++)
        {
            numChildNodes = ThreadLocalRandom.current().nextInt(2,5+1);
            randLeafOrComposite = ThreadLocalRandom.current().nextInt(1, 2+1);//if 1-> create a leaf. If 2-> create a composite.
            
            for(j=2; j < numChildNodes; j++)
            {
                if(i == k-1) //at this point, every child node should be a leaf, so they can have a power consumption category and values.
                {
                    compCity.addSubcity(new PartOfCity("building" + ctr++));
                }
                else
                {
                    if(randLeafOrComposite == 1)//create a leaf
                    {
                        
                    }
                    else//create a composite
                    {

                    }

                }
            }
        }

        return compCity;
    }*/
}