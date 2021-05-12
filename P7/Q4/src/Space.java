public class Space implements IState
{
 
    
    private SpaceStation context;

    /**
     * 
     * Idle state object.
     */
    public Space(SpaceStation context)
    {
        this.context = context;
    }

    @Override
    public void openInnerDoor()
    {

    }

    @Override
    public void openOuterDoor()
    {

    }

}


