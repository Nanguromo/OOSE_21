package addressbook.view; 

import java.io.*;
import java.util.*;
import addressbook.controller.*;
import addressbook.model.*;

public class MenuIO
{
    private IOption menuOption;
    private static Scanner input = new Scanner(System.in);
    
    public MenuIO()
    {
        this.menuOption = null;
    }

    public void doMenuOption(Map<Integer, IOption> options) 
    {
        boolean done = false;
        
        while(!done)
        {
            System.out.println("(1) Search by name, (2) Search by email, (3) Display all entries, (4) Quit");
            
            try
            {
                switch(Integer.parseInt(input.nextLine()))
                {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = input.nextLine();

                        menuOption = options.get(1);// Integer key 1 is assosciated with SearchByName Object which implements IOption
                        
                        System.out.println(menuOption.doOption(name));
                        
                        //done is still false. User did not specify quitting
                        break;
                        
                    case 2:
                        System.out.print("Enter email: ");
                        String email = input.nextLine();

                        menuOption = options.get(2);// Integer key 2 is assosciated with SearchByEmail Object which implements IOption
                        System.out.println(menuOption.doOption(email));

                        //done is still false. User did not specify quitting
                        break;
                        
    
                    case 3:
                        menuOption = options.get(3);
                        if(!menuOption.requiresText())
                        {
                            System.out.println(menuOption.doOption(""));
                        } 

                        break;
                    case 4:
                        done = true;
                        System.out.println("Buh-bye!");
                        
                        break;

                    default:
                        System.out.println("That number is not an option!!!!!\nChoose again.");
                        //done is still false
                        break;
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number!!!!!");
            }
        }
    }

    public AddressBook readAddressBook(String fileName) throws IOException
    {
        AddressBook addressBook = new AddressBook();
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        
        while(line != null)
        {

            String[] parts = line.split(":");

            addressBook.newEntry(parts);

            //a person can only have one name...right? Hence, part[0] will be their name.
            //Subsequent part(s) will be emails
            
            line = reader.readLine();

        }
        reader.close();

        
        return addressBook;
    }
}