package addressbook.controller;

import java.io.*;
import java.util.*;
import addressbook.model.*;
import addressbook.view.*;

public class AddressBookApp
{

    //private static List<IOption> options;
    private static Map<Integer, IOption> options;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        MenuIO menu = new MenuIO();
        options = new HashMap<>();
        
        String fileName, entryName;
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = menu.readAddressBook(fileName);
            options.put(1, new SearchByName(addressBook));
            options.put(2, new SearchByEmail(addressBook));
            options.put(3, new SearchAll(addressBook));
            menu.doMenuOption(options); //showMenu asks the user for what type of search option they choose and gets their input returned as a label.

        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }

}



