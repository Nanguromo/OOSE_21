public class SecuritySystem implements SensorObserver
{
    private MotionSensor motionSensor;
    private HeatSensor heatSensor;
    private Alarm alarm;
    private boolean armed;
    private EmailSystem emailSyst;

    //consustructor should be as simple as possible. "Should be trivial(ish)"
    public SecuritySystem(MotionSensor inMotionSens, HeatSensor inHeatSens, Alarm inAlarm, EmailSystem inEmailSyst)
    {
        //original constructor took Hardware and SensorBundle and then used them to get other object instances. Why not just inject those objects straight away?
        this.motionSensor = inMotionSens;
        this.heatSensor = inHeatSens;
        this.alarm = inAlarm;
        this.emailSyst = inEmailSyst; /*instead of having EmailSystem being injected as a reference in setAlarmed() and SensorDetection();
        set it as a class field here as they both use it to simply send an email to the system.*/
        this.armed = false;


        this.addSensorObsever(this);
    }

    public void setArmed(boolean newArmed)//, EmailSystem emailSyst)
    {
        this.armed = newArmed;
        emailSyst.sendMessage("Armed" + this.armed); //[1] original code had EmailSystem as a static class calling sendMessage(...),
        //Dependency injection principle states no static method calls to other classes. 
        //Furthermore, we are following another dependency injection principle of importing in all references for required objects
    }

    @Override
    public void SensorDetection(String str)//originally Sensor obj was passed in to have toString() called. Instead we pass in a String 
    {//to reduce Sensor from being directly coupled with this class. Sensor obj has toString() called by the injector and the string is passed in here.
        //no reason for Sensor to have been passed in.

        if(armed)
        {
            alarm.ring();
            emailSyst.sendMessage("Sensor detection for " + str); // same justification as [1]
        }
    }

    //use this method to keep constructor simple. It calls this method but this method can also be used to externally add more sensor observers.
    public void addSensorObsever(SensorObserver obs)
    {
        motionSensor.addSensorObsever(obs);
        heatSensor.addSensorObsever(obs);
    }
}