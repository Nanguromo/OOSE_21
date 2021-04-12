package addressbook.controller;

import java.util.*;
import java.io.*;
import addressbook.model.*;

public class SearchByEmail implements IOption
{
    private AddressBook ab;

    public SearchByEmail(AddressBook addressBook)
    {
        this.ab = addressBook;
    }

    @Override
    public String doOption(String email)
    {
        String outputStr = "";
        boolean found = false;

        
        for(Map.Entry<String, Entry> ent: ab.getAddressBook().entrySet()) // use entry set to allow iteration of hash map for finding specific email. We get a set of Entry objects
        {
            Entry currEntry = ent.getValue();//iterating through every Entry object in the set
            for(String emailStr: currEntry.getEmails()) //for every Entry, see if the email is found in the entry...
            {
                if(emailStr.equals(email))//...if the email is found in the entry; we have a hit
                {
                    //Entry entry = ab.getEntry(name);
                    outputStr = "Name: " + currEntry.getName() + "\nEmail: ";
        
                    //iterate through arraylist within the Entry object and print out its emails.
                    List<String> emails = currEntry.getEmails();
                    
                    for(String s: emails)
                    {
                        outputStr += s + " ";
                    }
                    found = true;
                
                    //IMPORTANT NOTE: The above code within the if statement is from SearchByName's doOption(...) method. I thought about creating a SearchByName Object and then
                    //calling its own doOption(...) method when a "hit" email was found, but ended up not to try to follow the UML outlined within practical 2 (no aggregation indicated)
                    //... Need to clarify with tutors/Dave if it is or isn't a good practice to have strategy classes aggregated if it improves code reusability.
                }
            }
        }

        outputStr += "\n";
        if(!found)
        {
            outputStr = email + " was not found!\n";
        }
        
        return outputStr;
    }

    @Override
    public boolean requiresText()
    {
        return true;
    }
}

