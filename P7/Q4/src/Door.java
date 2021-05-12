import java.util.*;

public class Door
{
    private boolean openFlag;

    public Door()
    {
        openFlag = false;
    }

    public void open()
    {
        openFlag = true;
    }

    public void close()
    {
        openFlag = false;
    }
}