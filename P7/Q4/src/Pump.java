import java.util.*;

public class Pump
{
    private Airlock airlock;
    private double pressure;

    private final double PRESSURETHRESHOLD = 5.0;
    

    public Pump(Airlock a)
    {
        airlock = a;
        pressure = airlock.getPressure();
    }

    public void beginExtraction()
    {
        TimerTask tt = new TimerTask(){
            @Override
            public void run()
            {
                pressure = pressure - 1.0;
                airlock.updatePressure(pressure);       
            }
        };

        Timer timer = new Timer("Extraction");

        while(pressure > PRESSURETHRESHOLD)
        {
            timer.scheduleAtFixedRate(tt, 0, 100); //decrease pressure by 1 kPa every 100 ms
        }
        
    }

    public void beginReturn()
    {
        TimerTask tt = new TimerTask(){
            @Override
            public void run()
            {
                pressure = pressure + 1.0;
                airlock.updatePressure(pressure);       
            }
        };

        Timer timer = new Timer("Extraction");

        while(pressure < 90.0) // while pressure is less than 90kPa, keep pressurising it.
        {
            timer.scheduleAtFixedRate(tt, 0, 100); //decrease pressure by 1 kPa every 100 ms
        }
    }
    
    public void stop()
    {
        //make a GUI? Or some way to implement EDP.
    }


}