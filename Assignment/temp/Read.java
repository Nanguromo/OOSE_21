package electricityusage.app.view;
import java.util.*;
import java.io.*;

public class Read implements IOption
{
    private IOption next;
    private String readFilename;

    public ReadInput(IOption next, String[] arg) throws IllegalArgumentException
    {
        if(arg[0].equals('-r') || (arg[1] != arg[0]))
        {
            this.next = next;
            this.readFilename = arg[1];
        }
        else
        {
            throw new IllegalArugmentException("");
        }
    }

    @Override
    public void doOption() 
    {

    }

    public String getFilename(){return this.readFilename;}


}