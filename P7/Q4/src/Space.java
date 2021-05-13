public class Space implements IState
{
 
    
    private SpaceStation context;

    /**
     * 
     * Space state object. This state is for when the astronaut has left the outerdoor and is now floating in space doing his job.
     */
    public Space(SpaceStation context)
    {
        this.context = context;
    }

    @Override
    public void openInnerDoor()
    {
        System.out.println("Error: You cannot open the inner door from outside (in space)!");
    }

    @Override
    public void openOuterDoor()
    {
        // do not have to check whether the inner door is open, because in order to have exited through the outer door and enter space; the inner door must have been closed
        // AT LEAST one door at all times must have been closed. Does not make sense to recheck it again

        if(context.getPressure() >= 5.0) // to much pressure; depressurise
        {
            System.out.println("Error: Outer door cannot be opened until the airlock is DEPRESSURISED to below 5kPA");
        }
        else// pressure is fine. Inner door is already closed
        {
            System.out.println("*You open the outer door from space and return back into the airlock*");
            context.setState(2); //returning from space to airlock; state 3 -> state 2
        }
    }

}


