import java.util.*;
import java.io.*;

public class SearchByName implements IOption
{
    private AddressBook ab;
    public SearchByName(AddressBook addresBook)
    {
        /*The Strategy Pattern implementation may sometimes result in the strategy classes not having any accessors or mutators,
        hence, a trivial constructor may be present.
        This is not always true, i.e our implementation, but it is, usually.*/
        this.ab = addresBook;
    }
    @Override
    public String doOption(String name)
    {
        String outputStr = name;

        if(ab.hasName(name)) // due to the addressbook being a map and the keys being the names, we can simply look up the name and get its associated value (Entry)
        {
            Entry entry = ab.getEntry(name);
            outputStr = "Name: " + entry.getName() + "\nEmail: ";

            //iterate through arraylist within the Entry object and print out its emails.
            List<String> emails = entry.getEmails();
            
            for(String email: emails)
            {
                outputStr += email + " ";
            }
            //System.out.println();
        }
        else
        {
            outputStr = (name + " is not in the address book!");
        }
        return outputStr;
    }

    @Override
    public boolean requiresText()
    {
        return true;
    }
}