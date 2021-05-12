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
                airlock.updatePressure(airlock.getPressure()); //Print out the current pressure within the SpaceStation every second
                //if the Pump is extracting or returning air, it will print out the changing air pressure as it extracts/returns air
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