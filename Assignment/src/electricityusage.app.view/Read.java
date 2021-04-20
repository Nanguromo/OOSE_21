package electricityusage.app.view;
import java.util.*;
import java.io.*;

/*
* Strategy Class for Strategy Pattern. This class's strategy is to do the read option. Reads data from a given (valid) file.
*/

public class Read implements IOption
{
    private String[] args;
    private String readFilename;

    public Read(String[] args)
    {
        this.args = args;
    }

    @Override
    public void doOption() 
    {
        //this function is able to called if there was no errors when reading the command line argument, i.e. invalid format
        //args[2] is the filename to read from
        
        BufferedReader reader = new BufferedReader(new FileReader(args[2]));//arg[1] is '-r'
        String line = reader.readLine();

        while(line != null)
        {
            if(line.trim().length() > 0) //ignores blank lines
            {
                String[] parts = line.split(",");

                
            }
        }
    }

    @Override
    public String getFilename(){return this.readFilename;}


}