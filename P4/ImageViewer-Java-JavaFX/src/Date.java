public class Date extends MetaData
{
    private String date;

    public Date(ImageData nextIn, String inDate)
    {
        super(nextIn);
        this.date = inDate;
        nextIn.setMetaData();
    }

    @Override
    public void setMetaData()
    {
        metaData += getDate();
    }

    @Override
    public String getMetadata()
    {
        return next.getMetadata() +" "+ date;
    }

    @Override
    public String getDate()
    {
        return this.date;
    }
}