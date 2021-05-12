public class Airlock
{
    // class fields
    private double currPressure;
    private boolean innerDoorStatus; //true -> door closed, false -> door open
    private boolean outerDoorStatus;// ""
    private int state;//0 -> idle, 1-> inside inner room (left idle through inner door), 2-> in space (outside outerdoor)
    private Door innerDoor;
    private Door outerDoor;
    private Pump pump;
    

    //constructor(s)
    public Airlock()
    {
        currPressure = 101.3;
        innerDoorStatus = true;
        outerDoorStatus = true;
        state = 0;
        innerDoor = new Door();
        outerDoor = new Door();
        pump = new Pump;
    }

    //Methods
    public void pressurise()
    {
        pump.beginExtraction();
    }

    public void depressurise() 
    {
        pump.beginReturn();
    }

    public void openInnerDoor()
    {
        switch(state)
        {
            case 0:
                if(currPressure < 90.0) // if current pressure is less than 90, pressure it until its acceptable to breathe
                {
                    pressurise();
                }
                break;
            case 1:
                if(outerDoorStatus && (currPressure >= 90 && currPressure <= 110)) // outer door is closed, and safe pressure. Proceed to next state
                {
                    innerDoor.open();
                    state = 1;
                }
                else if(!outerDoorStatus) //outer door is open. Cannot have two doors open at the same time
                {
                    outerDoor.close();
                }
            case 2:
                if(outerDoorStatus && (currPressure >= 90 && currPressure <= 110)) // safe for return from airlock to idle room
                {
                    innerDoor.open();
                    state = 0;
                }
                else if(!outerDoorStatus)
                {
                    outerDoor.close(); //can't return back to idle until outer door is closed
                }
                else if(currPressure < 90) // do not open the inner door until it is pressurised enough for the of the team within the idle room
                {
                    pressurise();
                }
                break;
            default:
                break;
        }
    }

    public void openOuterDoor()
    {
        switch(state)
        {
            case 1:
                if(currPressure >= 5) // wasting air. Depressurise more 
                {
                    depressurise();
                }
                else if(!innerDoorStatus)// can't have two doors open at once
                {
                    innerDoor.close();
                }
                else if(innerDoorStatus && currPressure < 5) // guard conditions met. Proceed to state 2 (in space)
                {
                    outerDoor.open();
                    state = 2;
                }
                break;
            case 2:
                if(pressure >= 5) // Wasting air. Depressurise more
                {
                    depressurise();
                }
                else if(!innerDoorStatus) // Can't have two doors open at once
                {
                    innerDoor.close();
                }
                else if(innerDoorStatus && currPressure < 5) // guard conditions met. Return to state 1 (back in airlock)
                {
                    outerDoor.open();
                }
                break;
            default:
                break;
        }

    }

    public void updatePressure(double inPressure)
    {
        currPressure = inPressure;
    }
}