package electricityusage.app.view;
import java.util.*;
import java.io.*;

/*
* Strategy Class for Strategy Pattern. This class's strategy is to do the write option. Writes to a given (valid) file. Creates a file if it doesn't exist.
*/

public class Write implements IOption
{
    private String[] args;
    private String writeFilename;

    public Write(String[] args)
    {
        this.args = args;

    }
    /*public ReadInput(IOption next, String[] arg) throws IllegalArgumentException, NullPointerException
    {
        this.next = next;

        if(this.next != null)
        {

            if(this.next instanceof Read) //need to check the instance. Even though checking instances is bad practice when working with polymorphism; it is necessary here.
            {
                if(arg[2].equals("-w"))
                {
                    if((Read)this.next.getFilename().equals(this.writeFilename))
                    {

                    }
                    else
                    {
                        throw new IllegalArgumentException("Error: Read and Write filenames cannot be the same!");
                    }

                }
                else
                {
                    throw new IllegalArgumentException("");
                }
            }
            else if(this.next instanceof Generate)
            {

            }

            if(arg[0].equals('w') || (arg[1] != arg[0]))
            {
            }
            else
            {
                throw new IllegalArugmentException("");
            }
        }
        else
        {
            throw new NullPointerException();
        }
    }*/

    @Override
    public void doOption() 
    {

    }

    @Override
    public String getFilename(){return this.writeFilename;}
    
}