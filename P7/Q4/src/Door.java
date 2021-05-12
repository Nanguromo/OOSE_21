import java.util.*;

public abstract class Door
{
    protected boolean openFlag; //protected so children can directly use it, but anything else needs to call the getter, isOpen()

    public Door()
    {
        openFlag = false;
    }

    public boolean isOpen()
    {
        return openFlag;
    }

    public abstract void close();


    public abstract void open();

    
}