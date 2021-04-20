package electricityusage.app.view;
import java.util.*;
import java.io.*;

/*
* Strategy Class for Strategy Pattern. This class's strategy is to do generate option
*/

public class Generate implements IOption
{
    private IOption next;

    public Generate(IOption next, String[] arg) throws new IllegalArgumentException
    {
        if(arg[0].equals("-g"))
        {
            this.next = next;
        }
        else
        {
            throw new IllegalArgumentException("");
        }
    }

    public void doOption()
    {

    }
}