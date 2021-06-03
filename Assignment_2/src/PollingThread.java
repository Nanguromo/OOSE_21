//PUT INTO PROPER PACKAGES  
import java.util.*;

import edu.curtin.comp2003.rover.EarthComm;

public class PollingThread implements Runnable
{
    private Thread t;
    private String polledCmd;
    private String[] buffer;
    private EarthComm comms;

    public PollingThread(EarthComm c)//Thread t)
    {
        //System.out.println("Polling Thread created");
        comms = c;
        //this.t = t;
    }

    @Override
    public void run()
    {
        polledCmd = comms.pollCommand();
        buffer = polledCmd.split("\n");

    }


    //@Override
    public void start()
    {
        //System.out.println("Starting polling thread");
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

    public String getPolledCmd()
    {
        return polledCmd;
    }

    public String[] getBuffer()
    {
        return buffer;
    }

    public int getBufferLength()
    {
        if(buffer == null)
        {
            System.out.println(); 
            //why do i keep getting null point exception
            //I was trying to track buffer's value but I can't seem to find where it goes to null
            //putting this sysout here fixes it though, albeit, not an ideal solution   
        }
        return buffer.length;
    }

    public String getBufferElement(int i)
    {
        return buffer[i];
    }

}