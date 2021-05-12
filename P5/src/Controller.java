import java.util.*;
import java.io.IOException;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

/**
 * The controller of the reminders application. The responsibilities of this 
 * class are light -- it's a thin layer between the View and the Model.
 */
public class Controller
{
    private static final String REMINDER_FILE = "reminders.txt";

    private ReminderList list;
    
    private MainWindow window;

    //private inner class MainWindow which is an implementation of ReminderObserver.
    //No other class directly uses MainWindow. ReminderList is not coupled to this class due to it only using ReminderObserver objects in its list
    private class MainWindow extends JFrame implements ReminderObserver
    {
        //another nested private inner class. Only MainWindow uses this so it would be simplest to nest it in here. Not necessary though
        private class AddReminderWindow extends JFrame
        {
            private static final int PADDING = 10;

            private Controller controller;
            private JTextField taskWidget;
            private JButton addButton;
            private JButton closeButton;

            /** 
             * Set everything up. We need to import a Controller reference, because 
             * this is where we tell the controller to add a reminder. 
             */
            public AddReminderWindow(Controller inController)
            {
                super("Add Reminder"); // Window title.

                controller = inController;

                // These are the important widgets.
                taskWidget = new JTextField(50);    // Space to enter some reminder text. 
                addButton = new JButton("Add");     // A button to add the reminder.
                closeButton = new JButton("Close"); // A button to close the window.


                // Now we do the boring window layout stuff.
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
                buttonPanel.add(Box.createHorizontalGlue());
                buttonPanel.add(addButton);
                buttonPanel.add(Box.createRigidArea(new Dimension(PADDING, 0)));
                buttonPanel.add(closeButton);

                JPanel contentPane = new JPanel();
                contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
                contentPane.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
                contentPane.add(taskWidget);
                contentPane.add(Box.createRigidArea(new Dimension(0, PADDING)));
                contentPane.add(buttonPanel);
                getRootPane().setContentPane(contentPane);


                // The add button tells the controller to add a new reminder.
                addButton.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            // Retrieve the text entered by the user.
                            String task = taskWidget.getText();
                            if(task.length() > 0)
                            {
                                // *If* there is some text present, add a new reminder.
                                controller.addReminder(task, new Date(0l));
                            }
                        }
                    }
                );
                
                // The close button simply hides this window; i.e. making it invisible 
                // to the user (but still present in memory).
                closeButton.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            setVisible(false);
                        }
                    }
                );
                
                // Trigger the window layout algorithm.
                pack();
            }
        }

        /**
        * The main reminder application window. This contains a list of the reminders,
        * along with an "add" button that opens an "add reminder" window, and a 
        * "remove" button that removes the currently-selected reminder.
        */
        private JList<String> remindersWidget;
        private JButton addButton;
        private JButton removeButton;
        
        private AddReminderWindow addReminderWindow;
        private Controller controller;
        private JPanel contentPane;
        //private List<JLabel> labels;
        //public MainWindow(Controller inController)
        public MainWindow()
        {
            super("Reminder App");                          // Window title.
            setPreferredSize(new Dimension(400, 150));      // Window size

            // Make the whole program close when this window is closed.
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //controller = inController;
            controller = Controller.this;
            addReminderWindow = new AddReminderWindow(controller);//get this Controller instance

            // Our important widgets:
            remindersWidget = new JList<String>();
            remindersWidget.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            addButton = new JButton("Add");
            removeButton = new JButton("Remove");



            // Boring window layout stuff.
            //JPanel 
            contentPane = new JPanel(new BorderLayout());
            JScrollPane scrollPane = new JScrollPane(remindersWidget);
            JToolBar toolbar = new JToolBar();
            contentPane.add(toolbar, BorderLayout.NORTH);
            contentPane.add(scrollPane, BorderLayout.CENTER);
            toolbar.add(addButton);
            toolbar.add(removeButton);
            getRootPane().setContentPane(contentPane);
            
            
            
            
            // When the "add" button is clicked, make the "add reminder" window visible.
            addButton.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        addReminderWindow.setVisible(true);
                    }
                }
            );
            
            // When the "remove" button is clicked, tell the controller to remove
            // the currently selected reminder.
            removeButton.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        // Figure out which reminder is currently selected.
                        int index = remindersWidget.getSelectedIndex();
                        if(index != -1)
                        {
                            // If there *is* something currently selected, tell the
                            // controller to remove it.
                            controller.removeReminder(index);
                        }
                    }
                }
            );

            //When main window close is clicked, write to output file upon exit
            this.addWindowListener( 
                new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        //Controller.write();
                        //System.out.println("got heeere!!");
                        try 
                        {
                            FileManager.write(REMINDER_FILE, list.getReminders());
                        } catch (IOException er) 
                        {
                            System.err.println("Error whilst saving reminders!");
                        }
                        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        System.exit(0);
                    }
                }
            );

            showReminders(controller.getReminders());
            
            //showReminders(controller.getReminders());
            
            // Trigger the layout algorithm.
            pack();

            
        }

        /**
         * Takes a list of Reminder objects and displays them in the window.
         */
        public void showReminders(List<Reminder> reminders)
        {
            Vector<String> tasks = new Vector<String>();
            for(Reminder rem : reminders)
            {
                tasks.add(rem.getTask());
            }
            remindersWidget.setListData(tasks);
            contentPane.add(remindersWidget);
            
        }

        @Override
        public void updateReminder()
        {
            //showReminders(list.getReminders());
            /*Vector<String> tasks = new Vector<String>();
            List<Reminder> reminders = list.getReminders();
            for(Reminder rem: reminders)
            {
                tasks.add(rem.getTask());
            }
            for(String task: tasks)
            {
                contentPane.add(new JLabel(task, JLabel.LEFT));
            }*/
            showReminders(list.getReminders());

        }

        /*@Override
        public void windowClosing(WindowEvent e)
        {
            //Controller.write();
            try 
            {
                FileManager.write(REMINDER_FILE, list.getReminders());
            } catch (IOException er) 
            {
                System.err.println("Error whilst saving reminders!");
            }
            System.exit(0);
        }*/

    }

    /**
     * Takes in an existing ReminderList -- the Model -- and populates it with
     * data read from the reminders file.
     */
    public Controller(ReminderList inReminderList)
    {
        list = inReminderList;
        window = new MainWindow();
        list.addObserver(window);
        //list.addObserver(new MainWindow());
        try
        {
            list.addReminders(FileManager.read(REMINDER_FILE));
            list.notifyReminders();
        }
        catch(IOException e)
        {
            System.err.println("Warning: unable to open the reminders file! Continuing without it.");
        }
    }
    
    /** Used by the UI when a reminder needs adding. */
    public void addReminder(String task, Date dateObj)
    {
        list.addReminder(new Reminder(task, dateObj));
        list.notifyReminders();
    }

    public void addObserver()
    {
        list.addObserver(new MainWindow());
    }
    
    /** Used by the UI when a reminder needs removing. */
    public void removeReminder(int index)
    {
        list.removeReminder(index);
        list.notifyReminders();
    }
    
    /** Used by the UI to obtain reminders to display. */
    public List<Reminder> getReminders()
    {
        return list.getReminders();
    }

    public void setVisible(boolean bool)
    {
        window.setVisible(bool);
    }

    /*public void write()
    {
        try 
        {
            FileManager.write(REMINDER_FILE, list.getReminders());
        } catch (IOException e) 
        {
            System.err.println("Error whilst saving reminders!");
        }
    }*/
    
    public void test()
    {
        System.out.println("works");
    }
}