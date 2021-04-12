package addressbook.model;

import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author Wayne Nanguromo
 */

 //Address book is a map that is filled with Entry objects. It contains the methods for finding and displaying details to the user when they search up a name or email
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
    public Entry getEntry(String name)
    {
        return addressbook.get(name);
    }

    public boolean hasName(String name)
    {
        return addressbook.containsKey(name) ? true : false; //practicing using ternary operator. Quicker than if-else, yet does the same thing.
    }

    public boolean hasEmail(String email)
    {
        return addressbook.containsKey(email) ? true : false;
    }

    public Map<String, Entry> getAddressBook()
    {
        return this.addressbook;
    }

    //Mutators
    public void newEntry(String[] parts)
    {
        Entry entry = new Entry(parts);
        //System.out.println("test");
        addressbook.put(parts[0], entry); //parts[0] is always the name, which is our key
        
        
    }
}
