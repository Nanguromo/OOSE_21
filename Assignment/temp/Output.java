package electricityusage.app.view;
import java.util.*;
import java.io.*;

/*
* Abstract decorator class or Decorator Pattern implementation
*/

public abstract class Output implements IOption
{
    protected IOption next;

    public Output(IOption next)
    {
        this.next = next;
    }

    @Override
    public void doOption()
    {
        next.doOption();
    }
}