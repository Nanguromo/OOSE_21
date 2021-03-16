import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author Wayne Nanguromo
 */
public class AddressBook
{
    // Insert your code here.
    private Map<String, Entry> addressbook;

    //Constructor
    public AddressBook()
    {
        addressbook = new HashMap<String, Entry>(); //using hash map because order does not matter. Just need to display details.

    }

    //Getters
    /*private Entry getEntry(String name)
    {
        return addressbook.get(name);
    }*/

    public void displayEntry(String name)
    {
        if(addressbook.containsKey(name))
        {
            Entry entry = addressbook.get(name);
            System.out.println("Name: " + entry.getName());

            List<String> emails = entry.getEmails();
            System.out.print("Email(s): ");
            for(String email: emails)
            {
                System.out.print(email + " ");
            }
            System.out.println();
        }
        else
        {
            System.out.println(name + " is not in the address book!");
        }
        
    }
    //Mutators
    public void newEntry(String[] parts)
    {
        Entry entry = new Entry(parts);
        //System.out.println("test");
        addressbook.put(parts[0], entry); //parts[0] is always the name, which is our key
        
        
    }
}
