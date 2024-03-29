public class InsideAirlock implements IState
{
 
    
    private SpaceStation context;

    /**
     * 
     * Inside Airlock Object. This state ensures that at least one door will be closed at all times to prevent the crew from dying. This state
     * is an intermediate state between state 1 and state 3. In order to go to state 1 or 3, you must first go through state 2.
     */
    public InsideAirlock(SpaceStation context)
    {
        this.context = context;
    }

    @Override
    public void openInnerDoor()
    {
        //ensure pressure is suitable for humans to breathe
        if(context.getPressure() < 90.0)
        {
            System.out.println("Error: Inner door cannot be opened until the airlock is PRESSURISED to at least 90kPA!");
        }
        else if(context.getOuterDoorStatus()) //ensures outer door is closed before returning back inside. If outer door is open; manually close it!
        {
            context.closeOuterDoor();
        }
        else//good to breathe; Pressure = [90 kPa, 110kPa] AND the outer door is closed, as AT LEAST one door is closed at all times. Return to Idle
        {
            System.out.println("*You open the inner door from within the airlock and return back to the Space Station HQ*");
            context.closeOuterDoor();
            context.setState(1);// get key 2, which corresponds to being inside the airlock. Then Airlock state is set as the current state in context.
        }
    }

    @Override
    public void openOuterDoor()
    {
        if(context.getPressure() >= 5.0) // pressure is too high and the air will get wasted. Depressurise before opening the outer door
        {
            System.out.println("Error: Outer door cannot be opened until the airlock is DEPRESSURISED to below 5kPA");
        }
        else if(context.getInnerDoorStatus())// ensure inner door is closed before entering space.
        {
            context.closeInnerDoor();
        }
        else// pressure is below 5kPa AND inner door is closed. Safe to enter space
        {
            System.out.println("*You open the outer door from within the airlock and float into space.*");
            context.closeOuterDoor(); //ensures you close the door behind you once you've entered the next state.
            context.setState(3);// get key 3, which corresponds to being in Space.
        }
    }


}