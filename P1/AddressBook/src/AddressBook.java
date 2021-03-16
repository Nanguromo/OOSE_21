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
    /*private Entry getEntry(String name)
    {
        return addressbook.get(name);
    }*/

    public void displayEntry(String name)
    {
        if(addressbook.containsKey(name)) // due to the addressbook being a map and the keys being the names, we can simply look up the name and get its associated value (Entry)
        {
            Entry entry = addressbook.get(name);
            System.out.println("Name: " + entry.getName());

            //iterate through arraylist within the Entry object and print out its emails.
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

    public void displayEmail(String email)
    {
        for(Map.Entry<String, Entry> ent: addressbook.entrySet()) // use entry set to allow iteration of hash map for finding specific email. We get a set of Entry objects
        {
            Entry temp = ent.getValue();//iterating through every Entry object in the set
            for(String emailStr: temp.getEmails()) //for every Entry, see if the email is found in the entry...
            {
                if(emailStr.equals(email))//...if the email is found in the entry we have a hit
                {
                    displayEntry(temp.getName());//re-use displayEntry. Just call it and pass the current Entry's name to have it display the details... the user doesn't need to know 
                }
            }
        }
        //This works, but surely there is a more efficient way to do this. If time permits, try refactor code w/ different containers
    }

    /*
    public void hashMap()
    {
        CLARIFY W/ TUTOR ABOUT THIS METHOD AND ALSO EQUALS()!!!
    }

    */

    //Mutators
    public void newEntry(String[] parts)
    {
        Entry entry = new Entry(parts);
        //System.out.println("test");
        addressbook.put(parts[0], entry); //parts[0] is always the name, which is our key
        
        
    }
}
