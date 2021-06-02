
import java.util.*;
import edu.curtin.comp2003.rover.EarthComm;
import edu.curtin.comp2003.controller.EarthHQ;

public class Multithread implements Runnable    
{
    private Thread t;
    private EarthComm comms;
    private EarthHQ hq;
    private String cmd;

    public Multithread(EarthComm c, EarthHQ hq, String cmd)
    {
        //System.out.println("Multithread created");
        comms = c;
        this.hq = hq;
        this.cmd = cmd;
    }

    @Override
    public void run()
    {
        hq.performPolledCmd(cmd);
    }

    public void start()
    {
        //System.out.println("Starting multithread");
        if(t == null)
        {
            t = new Thread(this);
        }

        try
        {
            t.start();
        }
        catch(IllegalThreadStateException e)
        {
            //state already started
        }
    }

    /*public void setCmd(String cmd)
    {
        this.cmd = cmd;
    }*/
}