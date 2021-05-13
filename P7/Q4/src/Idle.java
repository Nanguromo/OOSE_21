public class Idle implements IState
{
 
    
    private SpaceStation context;

    /**
     * 
     * Idle state object.
     */
    public Idle(SpaceStation context)
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
        // do not have to check whether the outer door is open, because in order to have passed through the inner door and enter the airlock; the outer door must have been closed
        // MANUALLY before, as specified in the problem description for Practical 7.
        // AT LEAST one door at all times must have been closed. Does not make sense to recheck it again
        else//good to breathe; Pressure = [90 kPa, 110kPa] and the outer door is closed
        {
            System.out.println("*You open the inner door from the Space Station HQ and enter the airlock.*");
            //ensures you close the door behind you once you've entered the next state.
            context.closeInnerDoor();
            context.setState(2);// get key 2, which corresponds to being inside the airlock. Then Airlock state is set as the current state in context.
        }

    }

    @Override
    public void openOuterDoor()
    {
        System.out.println("Error: You can only open the outer door, within the airlock!");
    }

}