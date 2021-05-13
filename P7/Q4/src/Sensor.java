import java.util.*;

public class Sensor 
{
    private double pressure;
    private SpaceStation ss;

    
    public Sensor(SpaceStation ss)
    {
        pressure = 101.3;
        this.ss = ss;
        TimerTask tt = new TimerTask(){
            @Override
            public void run()
            {
                ss.updatePressure(ss.getPressure()); //Print out the current pressure within the SpaceStation every second
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