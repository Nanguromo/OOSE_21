public class Main
{
    public static void main(String[] args)
    {
        //create objects to be injected
        EmailSystem es = new EmailSystem();
        Alarm alarm = new Alarm();
        Hardware hw = new Hardware();
        SensorBundle sense = hw.getSensors();

        MotionSensor ms = sense.getMotionSensor();
        HeatSensor hs = sens.getHeatSensor();

        //inject dependencies and create SecuritySystem object
        SecuritySystem ss = new SecuritySystem(ms, hs, alarm, es);      
    }
}