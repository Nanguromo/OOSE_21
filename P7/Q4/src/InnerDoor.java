public class InnerDoor extends Door
{
    public InnerDoor()
    {
        super();
    }

    @Override
    public void open()
    {
        openFlag = true;
        System.out.println("*Door opens. You enter through it and enter the airlock*");
    }

    @Override
    public void close()
    {
        openFlag = false;
        System.out.println("*You close the inner door.*");
    }
}