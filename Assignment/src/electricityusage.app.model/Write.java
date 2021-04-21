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

    public Write(String[] args, CompositeCity city)
    {
        this.args = args;
        this.city = city;
    }

    @Override
    public String doOption() throws IOException, IllegalArgumentException
    {
        return "";
    }

    @Override
    public String getFilename(){return this.writeFilename;}
    
    @Override
    public CompositeCity getCity(){return this.city;}
    
}