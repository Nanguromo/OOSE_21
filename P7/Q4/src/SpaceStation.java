import java.util.Map;

public class SpaceStation
{
    // class fields
    private double currPressure;

    private boolean innerDoorStatus; //true -> door closed, false -> door open
    private boolean outerDoorStatus;// ""
    private Door innerDoor;
    private Door outerDoor;
    
    private IState currState;
    private Map<Integer, IState> states;// Key correspondence: 1-> Idle, 2-> InsideAirlock, 3-> Space
    private Pump pump;


    // constructor(s)
    public SpaceStation()
    {
        currPressure = 101.3;

        innerDoorStatus = true;
        outerDoorStatus = true;
        innerDoor = new InsideDoor();
        outerDoor = new OutsideDoor();

        states = new HashMap<Integer, IState>();
        states.put(1, new Idle(this));
        states.put(2, new InsideAirlock(this));
        states.put(3, new Space(this));

        currState = states.get(1); // initial state is idle, i.e. within the Space Station HQ.

        pump = new Pump(this);
    }

    //Methods
    public void setState(int key)
    {
        currState = states.get(key);
    }
    public void pressurise()
    {
        pump.beginReturn(); // returning air will increase pressure in the room
    }

    public void depressurise() 
    {
        pump.beginExtraction(); // removing air will decrease the room's air pressure
    }

    public void openInnerDoor()
    {

    }

    public void openOuterDoor()
    {
      

    }

    //Used by Sensor to update and print in current pressure every second.
    public void updatePressure(double inPressure)
    {
        System.out.println("Current pressure is: " + inPressure);
    }

    //used for setting pressure within Airlock Object from Pump.
    public void setPressure(double inPressure)
    {
        currPressure = inPressure;
    }
    public double getPressure()
    {
        return currPressure;
    }

    public boolean getInnerDoorStatus()
    {
        return innerDoor.isOpen();
    }

    public boolean getOuterDoorStatus()
    {
        return outerDoor.isOpen();
    }

    public void closeOuterDoor()
    {
        outerDoor.close();
    }

    public void closeInnerDoor()
    {
        innerDoor.close();
    }

}