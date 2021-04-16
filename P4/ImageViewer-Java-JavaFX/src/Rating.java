public class Rating extends MetaData
{
    private String rating;

    public Rating(ImageData nextIn, String ratingIn)
    {
        super(nextIn);
        this.rating = ratingIn;
        nextIn.setMetaData();
    }

    @Override
    public void setMetaData()
    {
        metaData += getRating();
    }

    @Override
    public String getMetadata()
    {
        return next.getMetadata() + " " + rating;
    }

    @Override
    public String getRating()
    {
        return this.rating;
    }
}