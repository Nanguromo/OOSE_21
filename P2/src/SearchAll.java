import java.util.*;
import java.io.*;

public class SearchAll implements IOption
{
    private AddressBook ab;
    public SearchAll(AddressBook addressBook)
    {
        this.ab = addressBook;
    }

    @Override
    public boolean requiresText()
    {
        return false;
    }

    @Override 
    public String doOption(String in)
    {
        String outputStr = "";
        
        for(Map.Entry<String, Entry> ent: ab.getAddressBook().entrySet()) // use entry set to allow iteration of hash map for finding specific email. We get a set of Entry objects
        {
            Entry currEntry = ent.getValue();//iterating through every Entry object in the set
            outputStr += "Name: " + currEntry.getName() + "\nEmail: ";

            for(String emailStr: currEntry.getEmails()) //for every Entry, see if the email is found in the entry...
            {
                //iterate through arraylist within the Entry object and print out its emails.
                List<String> emails = currEntry.getEmails();
                
                for(String s: emails)
                {
                    outputStr = outputStr + s + " ";
                }
                outputStr+= "\n";           
            }
        }
        return outputStr;
    }

}