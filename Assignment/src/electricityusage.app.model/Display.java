package electricityusage.app.model;
import java.util.*;
import java.io.*;

/*
* Strategy Class for Strategy Pattern. This class's strategy is to do the display option. Outputs to terminal.
*/


public class Display implements IOption
{
    private String[] args;

    public Display(String[] args)
    {
        this.args = args;
    }

    @Override
    public void doOption()
    {

    }
    
    @Override
    public String getFilename()
    {
        //irrelevant to this implementation
        return "";
    }
}