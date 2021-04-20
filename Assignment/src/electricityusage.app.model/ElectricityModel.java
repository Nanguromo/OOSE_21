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
    private Map<String, IOption> operations;
    private String[] args;
    private IOption op1;
    private IOption op2;

    private int ctr; //for making unique leaf node names
    private int ctr2; // for making unique composite node names

    public ElectricityModel(String[] args)
    {
        //this.compCity = new CompositeCity("Perth");
        //ctr = 1;
        //ctr2 = 1;
        this.args = args;
        this.op1 = null;
        this.op2 = null;
        operations = new HashMap<String, IOption>();

        //Strategy Pattern
        //Don't know what option user wants, just have keys to locate IOption objects within a map that implement a specific operation
        operations.put("-r", new Read(args));
        operations.put("-g", new Generate(args));
        operations.put("-w", new Write(args));
        operations.put("-d", new Display(args));
    }

    public IOption getFirstOption()throws IllegalArgumentException
    {
        if(args.length == 0)
        {
            throw new IllegalArgumentException("Error: arguments required!");
        }

        op1 = operations.get(args[0]);
        if(op1 == null) //only null if the argument could not be found in the map. That means the user did not follow the proper format!
        {
            throw new IllegalArgumentException("Error: First argument must either be \"-r\" to read from a file or \"-g\" to generate data");
        }
        return op1;
    }

    public IOption getSecondOption()throws IllegalArgumentException,ArrayIndexOutOfBoundsException
    {
        //this method is called only if program has not exit yet. Does not make sense to get second option before the first!
        try
        {
            if(op1 instanceof Read)
            {
                if(args[2].equals(args[0]))//prevent entering the same command line argument twice
                {
                    throw new IllegalArgumentException("Error: Cannot have same arguments \"-r\" twice!. Arguments must be unique.");
                }
                else
                {
                    op2 = operations.get(args[2]);// get third argument. Second argument was filename from read
                    System.out.println("got to read");

                    //check to see if option 2 was found. If option 2 wasnt found, the user entered an invalid command line argument; display error and exit
                    if(op2 == null)
                    {
                        throw new IllegalArgumentException("Error: Third argument must either be \"-w\" to write a file or \"-d\" to display data");
                    }
                }
            }

            if(op1 instanceof Generate)
            {
                if(args[0].equals(args[1])) //prevent entering the same command line argument twice
                {
                    throw new IllegalArgumentException("Error: Cannot have same arguments \"-g\" twice!. Arguments must be unique.");
                }
                else
                {
                    op2 = operations.get(args[1]); //get second argument

                    //check to see if option 2 was found. If option 2 wasnt found, the user entered an invalid command line argument; display error and exit
                    if(op2 == null)
                    {
                        throw new IllegalArgumentException("Error: Second argument must either be \"-w\" to write a file or \"-d\" to display data");
                    }
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {   
            throw new ArrayIndexOutOfBoundsException("Error: You must enter the second argument which may either be \"-w\" to write to a file or \"-d\" to display data");
        }
        
        return op2; //guaranteed to be non-null because of checks within the method. If option2 was null at any point within the nested if statements; 
        //option 2 was not found, hence, the program would have exit already!
    }


    /*public void addSubcity(ICity subCity) //wrapper method. CompositeCity is directly a part of the internal workings of this class. Hence, using a wrapper method
    {
        compCity.addSubcity(subCity); 
    }*/

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