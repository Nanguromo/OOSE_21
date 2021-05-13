import java.util.*;

public class Controls
{
    /**
     * 
     * This is the main file for my State Pattern implementation. It tests the functionality of the State Pattern.
     */
    public static void main(String[] args)
    {
        SpaceStation ss = new SpaceStation();
        //Initialisation. Default state is Idle, i.e. within SpaceStation HQ.

        Scanner sc = new Scanner(System.in);
        int choice = 0;
        System.out.println("You are an astronaut within the HQ. There are three buttons you can press:");

        while(choice != 5)
        {
            //System.out.println("Current pressure is: ")
            System.out.println("\t1. Open Inner Door");
            System.out.println("\t2. Open Outer Door");
            System.out.println("\t3. Pressurise Airlock");
            System.out.println("\t4. Depressurise Airlock");
            System.out.println("\t(5. Exit Program)");
            choice = sc.nextInt();
            System.out.println(choice + "fufihbdsiufbwduibfiusdbfisd");
            switch(choice)
            {
                case 1:
                    ss.openInnerDoor();
                    break;
                case 2:
                    ss.openOuterDoor();
                    break;
                case 3:
                    ss.pressurise();
                    break;
                case 4:
                    ss.depressurise();
                    break;
                default:
                    break;

            }

            //for some reason my program would not break the while-loop, even when choice was 5. Rudimentary solution, will fix if I have time
            if(choice == 5)
            {
                System.exit(0);
            }
        }
        


    }
}