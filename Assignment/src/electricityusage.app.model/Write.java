package electricityusage.app.model;
import java.util.*;
import java.io.*;
import electricityusage.app.model.CompositeCity;
/*
* Strategy Class for Strategy Pattern. This class's strategy is to do the write option. Writes to a given (valid) file. Creates a file if it doesn't exist.
*/

public class Write implements IOption
{
    private String[] args;
    private String writeFilename;
    private CompositeCity city;
    private IOption option1;

    public Write(String[] args, CompositeCity city, IOption option1)
    {
        this.args = args;
        this.city = city;
        this.option1 = option1;
    }

    @Override
    public String doOption() throws IOException, IllegalArgumentException
    {
        try
        {
                //Further validation of command line arguments
                if(option1 instanceof Read)
                {
                    //arg[0]p-> -r, arg [1]-> filename1, arg[2]-> -w, arg[3]-> filename2
                    
                    //check for duplicate names
                    if(this.foundDuplicateName(args[1], args[3]))
                    {
                        throw new IllegalArgumentException("Error: Cannot have same read and write filenames! They must be unique.\n");
                    }

                    writeFilename = args[3];
                }
                else//was Generate
                {
                    //arg[0]-> -g, arg[1]-> -w , arg[2]->filename
                    writeFilename = args[2];

                }

                FileWriter fileWriter = new FileWriter(writeFilename, true);//true tells file constructor to append to the writefile. If found file doesnt exist, it will be created
                BufferedWriter bw = new BufferedWriter(fileWriter);
                PrintWriter pw = new PrintWriter(bw);//decorative pattern?

                pw.printf(this.city.getWriteString()); // using printf, because the returned string is formatted with "%n" identifier. Printf reads this and knows when to write to new line.
                //the reason i have done this is because its simpler than writing to each line individually while maintaing track of where you are on the City tree.
                //Rather than do that, the tree is iterated over within the ICity implementation classes, and they track where they went to return a formatted String ready to be written to a file.

                pw.close();
        }
        catch(IOException e)
        {
            throw new IOException("Error: could not open file!\n");
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
        return "";
    }

    @Override
    public String getFilename(){return this.writeFilename;}
    
    @Override
    public CompositeCity getCity(){return this.city;}

       

    private boolean foundDuplicateName(String writeFile, String readFile)
    {
        boolean valid = false;

        if(writeFile.equals(readFile))
        {
            valid = true;
        }

        return valid;
    }
    
}