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
            context.pressurise();
        }
        else//good to breathe; Pressure = [90 kPa, 110kPa] and the outer door is closed, as AT LEAST one door is closed at all times.
        {
            context.openInnerDoor();
            context.setState(context.get(2));// get key 2, which corresponds to being inside the airlock. Then Airlock state is set as the current state in context.
        }

    }

    @Override
    public void openOuterDoor()
    {
        System.out.println("Error: You can only open the outer door, within the airlock!");
    }

}