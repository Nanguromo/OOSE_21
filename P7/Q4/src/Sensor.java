import java.util.*;

public class Sensor 
{
    private double pressure;
    private Airlock airlock;

    
    public Sensor(Airlock al)
    {
        pressure = 101.3;
        airlock = al;
        TimerTask tt = new TimerTask(){
            @Override
            public void run()
            {
                airlock.updatePressure(airlock.getPressure());
            }
        };

        Timer time  = new Timer("Pressure Sensor");
        time.scheduleAtFixedRate(tt, 0 , 1000);  //update pressure every second
    }

    public double getPressure()
    {
        return pressure;
    }

    
}