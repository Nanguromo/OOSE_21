public class Caption extends MetaData
{
    private String caption;

    public Caption(ImageData nextIn, String inCap)
    {
        super(nextIn); //super calls creates MetaData's constructor
        this.caption = inCap;
        nextIn.setMetaData();//super.setMetaData()??????
    }

    /*@Override
    public String getMetaData()
    {
        return getCaption(); //next is accessible as Caption inherits it from MetaData (protected)
    }*/

    @Override 
    public void setMetaData()
    {
        metaData += getCaption();
    }

    @Override
    public String getMetadata()
    {
        return next.getMetadata() +" "+ caption;
    }

    @Override
    public String getCaption()
    {
        return this.caption;
    }

}