package electricityusage.app.model;
import java.util.*;
import java.io.*;
import electricityusage.app.model.CompositeCity;
import electricityusage.app.model.ICity;
import electricityusage.app.model.PartOfCity;

/*
* Strategy Class for Strategy Pattern. This class's strategy is to do the generate option. Generation is done randomly.
*/

public class Generate implements IOption
{
    private String[] args;
    private CompositeCity generatedCity;

    public Generate(String[] args) 
    {
        this.args = args;
    }

    @Override
    public String doOption() //this doOption randomly generates data. Should read from a txt file with a list of names to be selected from
    {
        //Generate's doOption() should perform this function using The Composite Pattern ??????
        return "";
    }

    @Override
    public String getFilename()
    {
        //irrelevant to this implementation
        return "";
    }

    @Override
    public CompositeCity getCity(){return this.generatedCity;}

}