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

    //private int ctr; for making unique leaf node names
    //private int ctr2;  for making unique composite node names

    public ElectricityModel(String[] args)
    {
        this.args = args;
        this.op1 = null;
        this.op2 = null;
        operations = new HashMap<String, IOption>();

        //Strategy Pattern
        //Don't know what option user wants, just have keys to locate IOption objects within a map that implement a specific operation
        operations.put("-r", new Read(args));
        operations.put("-g", new Generate(args));
        
        
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

    //this method is called only if program has not exit yet. Does not make sense to get second option before the first!
    public IOption getSecondOption()throws IllegalArgumentException,ArrayIndexOutOfBoundsException
    {
        operations.put("-w", new Write(args, op1.getCity(), op1)); //only add these options to the map after option has been performed. They rely on having a Composite city existing!
        operations.put("-d", new Display(args, op1.getCity(), op1));

        try
        {
            if(op1 instanceof Read) //depending if argument 1 was Read or Generate, the order number of args will change. Need to check to prevent ArrayIndexOutOfBoundsException
            {
                if(args[2].equals("-g"))
                {
                        throw new IllegalArgumentException("Error: Cannot read from a file \"-r\" AND generate \"-g\" data!");
                }
                else if(args[2].equals(args[0]))//prevent entering the same command line argument twice
                {
                    throw new IllegalArgumentException("Error: Cannot have same arguments \"-r\" twice!. Arguments must be unique.");
                }
                else
                {
                    op2 = operations.get(args[2]);// get third argument. Second argument was filename from read
                    //System.out.println("got to read");

                    //check to see if option 2 was found. If option 2 wasnt found, the user entered an invalid command line argument; display error and exit
                    if(op2 == null)
                    {
                        throw new IllegalArgumentException("Error: Third argument must either be \"-w\" to write a file or \"-d\" to display data");
                    }
                }
            }

            if(op1 instanceof Generate)
            {
                if(args[1].equals("-r"))
                {
                        throw new IllegalArgumentException("Error: Cannot read from a file \"-r\" AND generate \"-g\" data!");
                }
                else if(args[0].equals(args[1])) //prevent entering the same command line argument twice
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
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
        
        return op2; //guaranteed to be non-null because of checks within the method. If option2 was null at any point within the nested if statements; 
        //option 2 was not found, hence, the program would have exit already!
    }
}