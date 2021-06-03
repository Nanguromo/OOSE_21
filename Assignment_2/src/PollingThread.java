import java.util.*;

import edu.curtin.comp2003.rover.EarthComm;

/** 
 * This class is used to run a separate thread that runs parallel to the main program. This thread deals with polling commands, i.e. receiving
 * the commands from Earth
 */
public class PollingThread implements Runnable
{
    private Thread t;
    private String polledCmd;
    private String[] buffer;
    private EarthComm comms;

    public PollingThread(EarthComm c)
    {
        comms = c;
    }

    /**
     * when the thread is started, this function is called
     */
    @Override
    public void run()
    {
        polledCmd = comms.pollCommand();
        buffer = polledCmd.split("\n");

    }


    //@Override
    public void start()
    {
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

    // the following getters are necessary due to the MarsRoverApp needing access to this class's class fields for the software's functionality
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