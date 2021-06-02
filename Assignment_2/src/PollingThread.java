
import java.util.*;

public class PollingThread implements Runnable
{
    private Thread t;

    public PollingThread()//Thread t)
    {
        System.out.println("Polling Thread created");
        //this.t = t;
    }

    @Override
    public void run()
    {
        String polledCmd = comms.pollCommand();
    }


    @Override
    public void start()
    {
        System.out.println("Starting polling thread");
        if(t == null)
        {
            t = new Thread(this);
        }
        t.start();
    }
}