public class GPS extends MetaData
{
    private String gps;

    public GPS(ImageData nextIn, String gpsIn)
    {
        super(nextIn);
        this.gps = gpsIn;
        nextIn.setMetaData();
    }

    @Override
    public void setMetaData()
    {
        metaData += getGPS();
    }

    @Override
    public String getMetadata()
    {
        return next.getMetadata() +" "+ gps;
    }

    @Override
    public String getGPS()
    {
        return this.gps;
    }
}