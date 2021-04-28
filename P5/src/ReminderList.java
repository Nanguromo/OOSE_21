import java.util.*;

/**
 * Represents a collection of Reminders.
 */
public class ReminderList
{
    private List<Reminder> reminders;
    private List<ReminderObserver> obs;

    public ReminderList()
    {
        reminders = new ArrayList<Reminder>();
        obs = new ArrayList<ReminderObserver>();
    }
    
    /** Add a single reminder to the list. */
    public void addReminder(Reminder rem)
    {
        reminders.add(rem);
    }

    /** Add an observer to the observer list */
    public void addObserver(ReminderObserver ob)
    {
        obs.add(ob);
    }
    
    /** Add a complete list of reminders to the existing list. */
    public void addReminders(List<Reminder> newReminders)
    {
        reminders.addAll(newReminders);
    }
    
    /** Remove a reminder by index (i.e. 0 to #reminders-1) */
    public void removeReminder(int index)
    {
        reminders.remove(index);
    }
    
    /** Retrieve a copy of the reminder list. */
    public List<Reminder> getReminders()
    {
        return Collections.unmodifiableList(reminders);
    }

    /** Notify observers in observer list */
    public void notifyReminders()
    {
        for(ReminderObserver remObs: obs)
        {
            remObs.updateReminder();
        }
    }
}
