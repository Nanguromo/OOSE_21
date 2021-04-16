/**
 * Represents an image in an album.
 */
public class ImageRecord implements ImageData
{
    private String filename;
    
    public ImageRecord(String newFilename)
    {
        filename = newFilename; 
        //caption = newCaption;
    }

    @Override
    public void setMetaData()
    {
        //fileName = this.fileName;
    }
    
    @Override
    public String getFilename()
    {
        return filename;
    }
    
    @Override
    public String getMetadata(){return "";}

    @Override
    public String getCaption(){return "";}

    @Override
    public String getGPS(){return "";}

    @Override
    public String getRating(){return "";}

    @Override
    public String getDate(){return "";}
}

