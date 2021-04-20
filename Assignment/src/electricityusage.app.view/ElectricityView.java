package electricityusage.app.view;
import java.util.*;
import java.io.*;

/*
* Deals with View portion of MVC. This class makes use of the IInput and IOutput Interfaces who both implement The Strategy Pattern
*/
public class ElectricityView 
{
    private Map<String, IOption> options;
    private IOption option1;
    private IOption option2;
    private String[] args;

    public ElectricityView(String[] args)
    {
        options = new HashMap<String, IOption>();
        options.put("-r", new Read(args));
        options.put("-g", new Generate(args));
        options.put("-w", new Write(args));
        options.put("-d", new Display(args));

        this.args = args;
        option1 = null;
        option2 = null;
    }

    public IOption getFirstOption()
    {
        option1 = options.get(args[0]); //get first argument
        if(option1 == null)
        {
            System.out.println("Error: First argument must either be \"-r\" to read from a file or \"-g\" to generate data");
            System.exit(1);//exit gracefully
        }
        return option1;
    }

    public IOption getSecondOption()
    {
        //this method is called only if program has not exit yet. Does not make sense to get second option before the first!
        if(option1 instanceof Read)
        {
            option2 = options.get(args[2]);// get third argument. Second argument was filename from read

            //check to see if option 2 was found. If option 2 wasnt found, the user entered an invalid command line argument; display error and exit
            if(option2 == null)
            {
                System.out.println("Error: Third argument must either be \"-w\" to write a file or \"-d\" to display data");
                System.exit(1);
            }
        }

        if(option1 instanceof Generate)
        {
            option2 = options.get(args[1]); //get second argument

            //check to see if option 2 was found. If option 2 wasnt found, the user entered an invalid command line argument; display error and exit
            if(option2 == null)
            {
                System.out.println("Error: Second argument must either be \"-w\" to write a file or \"-d\" to display data");
                System.exit(1);
            }
        }

        return option2; //guaranteed to be non-null because of checks within the method. If option2 was null at any point within the nested if statements; 
        //option 2 was not found, hence, the program would have exit already!
    }

    public boolean checkReadWriteValid()//checks if read and write filenames are unique
    {
        boolean valid = true;
        if(option1.getFilename().equals(option2.getFilename()))
        {
            valid = false;
        }
        return valid;
    }

}