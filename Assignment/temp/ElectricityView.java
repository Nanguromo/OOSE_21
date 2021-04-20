package electricityusage.app.view;
import java.util.*;
import java.io.*;

/*
* Deals with View portion of MVC. This class makes use of the IInput and IOutput Interfaces who both implement The Strategy Pattern
*/

public class ElectricityView
{
    private String[] input;
    private String filenameOut;
    private String filenameIn;
    public String specificErrorMsg;

    public ElectricityView(String[] args)
    {
        if(checkSize())
        {
            if(this.isValid())
            {
                this.input = args;
                //this.filename
            }
            else
            {

            }
        }
        else
        {
            System.out.println("Error: You must enter two arguments!\n"); 
            this.displayCommonErrorMessage();
            System.exit(0);//exit "gracefully"
        }
    }

    //checks if command line arguments are valid. If not program exits with an error telling the user what went wrong
    //this is too convoluted... surely there is some pattern that can check all the command line argument's validity?
    private boolean isValid()
    {
        boolean valid = false;

        if(!input[0].equals(input[1]))//ensures argument 1 and 2 are never the same. Furthermore, prevents reading from a while called "-r".
        {
            if(input[0].equals("-g") || input[0].equals("-r"))//Now checks to make sure argument one is either generate or read
            {
                if(input[0].equals("-r")) //If argument 1 was read, we now check
                {

                }
                else
                {
                    if(input[1].equals("-w") || input[1].equals("-d"))//ensures 
                    {
                        if( !(input[0].equals(input[1])) )
                        {
                        
                        }
                    }
                }
            }
        }
    }

    private boolean checkSize()
    {
        boolean valid = false;

        if()
    }
    
    private void displayCommonErrorMessage()
    {
        System.out.println("Argument (1) may either be '-g' to generate, or 'r' to read from a file");
        System.out.println("Argument (2)) may either be '-d' to display to the results to the terminal, or 'w' to write results to a file");
        System.out.println("If 'r' or 'w' are entered, a filename must be entered as well, directly after the two arguments respectively. Files must be unique!");
        System.out.println("For example entering: \"java -jar ElectricityUsage.jar -r file1.csv -w file2.csv\" is a valid.");
    }
}