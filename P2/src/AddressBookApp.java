import java.io.*;
import java.util.*;


public class AddressBookApp
{

    //private static List<IOption> options;
    private static Map<Integer, IOption> options;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        AddressBookApp aba = new AddressBookApp();
        options = new HashMap<>();
        
        String fileName, entryName;
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = aba.readAddressBook(fileName);
            options.put(1, new SearchByName(addressBook));
            options.put(2, new SearchByEmail(addressBook));
            options.put(3, new SearchAll(addressBook));
            aba.showMenu(); //showMenu asks the user for what type of search option they choose and gets their input returned as a label.

        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }

    //Getter(s)
    private void showMenu() 
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

                        IOption nameOption = options.get(1);// Integer key 1 is assosciated with SearchByName Object which implements IOption
                        System.out.println(nameOption.doOption(name));
                        
                        //done is still false. User did not specify quitting
                        break;
                        
                    case 2:
                        System.out.print("Enter email: ");
                        String email = input.nextLine();

                        IOption emailOption = options.get(2);// Integer key 2 is assosciated with SearchByEmail Object which implements IOption
                        System.out.println(emailOption.doOption(email));

                        //done is still false. User did not specify quitting
                        break;
                        
    
                    case 3:
                        IOption all = options.get(3);
                        if(!all.requiresText())
                        {
                            System.out.println(all.doOption(""));
                        } 
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

    private AddressBook readAddressBook(String fileName) throws IOException
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

    //Mutator(s)
    public void addOption()
    {

    }
}



