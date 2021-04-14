import java.util.*;

/**
 * Represents an album of images.
 */
public class Album 
{
    private List<ImageRecord> photos;
    private int currIdx;

    public Album()
    {
        photos = new ArrayList<>();
        currIdx = 0;
    }

    public void addPhoto(String name, String caption)
    {
        ImageRecord imageRecord = new ImageRecord(name, caption);
        photos.add(imageRecord);
    }

    public ImageRecord getFirstPhoto()
    {
        return photos.get(0);
    }

    public ImageRecord getNextPhoto()
    {
        if(currIdx == photos.size() - 1)//at the end of list; loop back to the front
        {
            currIdx = 0;
        }
        else
        {
            currIdx++;
        }

        return photos.get(currIdx);
    }

    public ImageRecord getPrevPhoto()
    {
        if(currIdx == 0) //at the start of list; loop backwards
        {
            currIdx = photos.size() - 1; //current idx is now the final element in the list
        }
        else
        {
            currIdx--;
        }

        return photos.get(currIdx);
    }

}
