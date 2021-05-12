import javax.swing.*;

/**
 * A reminder application.
 * @author Dave & Wayne
 */
public class ReminderApp 
{
    public static void main(String[] args)
    {
        final ReminderList reminders = new ReminderList();
        final Controller controller = new Controller(reminders);
            
        // Make sure all GUI-related stuff happens within the "Event Dispatch Thread". (Standard practice for the Java Swing GUI.)
        SwingUtilities.invokeLater(
            new Runnable() 
            {
                public void run() 
                {   
                    // Create a window (hidden to begin with)
                    //Controller.MainWindow window = new Controller.MainWindow();
                                        
                    // Exit when the exit button is pressed.
                    //window.setVisible(true);
                    controller.setVisible(true);
                    //controller.write();

                }
            }
        );
        /*controller.test();
        controller.write();
        controller.test();*/

    }
}
