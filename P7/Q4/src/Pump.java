import java.util.*;
public class Pump
{
    private SpaceStation ss;
    private double pressure;

    private final double PRESSURETHRESHOLD = 5.0;
    

    public Pump(SpaceStation ss)
    {
        this.ss = ss;
        pressure = ss.getPressure();
    }

    public void beginExtraction()
    {
        System.out.println("*The room is depressuring to prevent air wastage.*");

        /*TimerTask tt = new TimerTask(){
            @Override
            public void run()
            {
                pressure = pressure - 1.0;
                ss.setPressure(pressure);       
            }
        };

        Timer timer = new Timer("Extraction");

        while(pressure > PRESSURETHRESHOLD)
        {
            timer.scheduleAtFixedRate(tt, 0, 100); //decrease pressure by 1 kPa every 100 ms
        }*/
        while(pressure > PRESSURETHRESHOLD)
        {
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }            
            pressure = pressure - 1.0;
            ss.setPressure(pressure); 
        }
        
    }

    public void beginReturn()
    {
        System.out.println("The room is pressurising as it currently is not suitable for breathing.");

        /*TimerTask tt = new TimerTask(){
            @Override
            public void run()
            {
                pressure = pressure + 1.0;
                ss.setPressure(pressure);       
            }
        };

        Timer timer = new Timer("Extraction");

        while(pressure < 90.0) // while pressure is less than 90kPa, keep pressurising it.
        {
            timer.scheduleAtFixedRate(tt, 0, 100); //decrease pressure by 1 kPa every 100 ms
        }*/

        while(pressure < 90.0)
        {
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            pressure = pressure + 1.0;
            ss.setPressure(pressure); 
        }
    }
    
    public void stop()
    {
        //make a GUI? Or some way to implement EDP.
    }


}