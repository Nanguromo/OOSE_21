import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author Wayne Nanguromo
 */

 //This a class simply for details. It stores the name scanned in and the emails. Simply used to hold values, but does not contain anything "complex"
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

    /*
    THIS METHOD IS NOT NECESSARY (for functionality). ALTHOUGH I NEED TO CLARIFY WHETHER HASHMAP() AND EQUALS() IN ADDRESSBOOK NEEDS TO BE WRITTEN AND WHY?
    public boolean equals(Entry e2)
    {
        boolean equals = false;
        if(this.name.equals(e2.getName()))
        {
            Iterator iter = emails.iterator();
            Iterator iter2 = e2.getEmails().iterator();
            equals = true;
            while(iter.hasNext() && iter2.hasNext())
            {
                if(!iter.next().equals(iter2.next()))
                {
                    return false;
                }
            }
        }
        return equals;
    }
    */
}
