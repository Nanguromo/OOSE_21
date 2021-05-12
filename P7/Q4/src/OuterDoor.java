public class OuterDoor extends Door
{
    public OuterDoor()
    {
        super();
    }

    @Override
    public void open()
    {
        openFlag = true;
        System.out.println("*Door opens. You enter through it and now are floating in space");
    }

    @Override
    public void close()
    {
        openFlag = false;
        System.out.println("*You close the outer door.");
    }
}

