public abstract class MetaData implements ImageData
{
    protected ImageData next;
    protected String metaData;

    public MetaData(ImageData nextIn)
    {
        this.next = nextIn;
        nextIn.setMetaData();
    }

    @Override
    public String getMetadata()
    {
        return next.getMetadata(); //returns implementation class's getMetaData()
    }

    @Override
    public void setMetaData()
    {
        next.setMetaData();
    }

    @Override
    public String getFilename()
    {
        return next.getFilename();
    }

    public String getCaption()
    {
        return next.getCaption();
    }

    public String getGPS()
    {
        return next.getGPS();
    }

    public String getRating()
    {
        return next.getRating();
    }

    public String getDate()
    {
        return next.getDate();
    }
        
}