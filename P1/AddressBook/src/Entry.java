import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author Wayne Nanguromo
 */
public class Entry 
{
    // Insert your code here.
    private String name;
    private List<String> emails;

    //Constructor
    public Entry(String[] parts)
    {
        name = parts[0];
        int i = 1;
        emails = new ArrayList<String>();
        while(i < parts.length)
        {
            emails.add(parts[i]);
            i++;
        }
        
    }

    //Getters
    public String getName()
    {
        return this.name;
    }

    public List<String> getEmails()
    {
        return this.emails;
    }
}
