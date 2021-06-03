package edu.curtin.comp2003.controller.observer;

import java.util.*;
import edu.curtin.comp2003.rover.EarthComm;

/**
 * This is the concrete implementation of the Observer interface. Only one concrete class was needed, but for scalability,
 * if more concrete implementations were needed; another concrete class could be easily created.
 */
public class CommandFinished
{
    private Set<IReturnMessage> observers;
    private EarthComm comm;

    public CommandFinished(EarthComm comm)
    {
        this.observers = new HashSet<>();
        this.comm = comm;

        //The set is filled with anonymous classes. It would be a waste of time and uncessary code (and files) required if I were to make a class for each Return Message
        //The trade-off for using anonymous classes is that this constructor is quite hefty in size, but it is not convoluted, i.e. it is still cohesive and does one thing

        observers.add(new IReturnMessage() //anonymous class for INVALID COMMAND RETURN MESSAGE. This was done because this observer is very simple
        {
            @Override public void commandComplete(String commandFlag, String message)
            {
                if(commandFlag.equals("!"))
                {
                    comm.sendMessage("! " + message);
                }
            }
        });

        //anonymous class added for successfull DRIVE completion
        observers.add(new IReturnMessage(){
            @Override public void commandComplete(String commandFlag, String message)
            {  
                if(commandFlag.equals("D"))
                {
                    comm.sendMessage("D");
                }
            }
        });

        //anonymous class added for successful PHOTO taken
        observers.add(new IReturnMessage(){
            @Override public void commandComplete(String commandFlag, String message)
            {  
                if(commandFlag.equals("P"))
                {
                    comm.sendMessage("P " + message);
                }
            }
        });

        //anonymous class added for successful ENVIRONMENTAL scan
        observers.add(new IReturnMessage(){
            @Override public void commandComplete(String commandFlag, String message)
            {  
                if(commandFlag.equals("E"))
                {
                    comm.sendMessage("E " + message);
                }
            }
        });

        //anonymous class added for successful SOIL ANALYSIS completion
        observers.add(new IReturnMessage(){
            @Override public void commandComplete(String commandFlag, String message)
            {  
                if(commandFlag.equals("S"))
                {
                    comm.sendMessage("S " + message);
                }
            }
        });
    }

    //The following are basic methods for Observer Pattern implemenation...
    public void addObserver(IReturnMessage ob)
    {
        observers.add(ob);
    }

    public void removeObserver(IReturnMessage ob)
    {
        observers.remove(ob);
    }
    
    
    /**
     * notifyObservers lets all the observers know when a command has been received and complete (or invalid command received). 
     * All the observers are iterated through and String commandFlag is sent to each observer; if the flag is a match with the corresponding
     * internal class field flag of the observer; that specific observer outs its completion message of the task (or whether the command received was invalid)
     */
    public void notifyObservers(String commandFlag, String message)
    {
        for(IReturnMessage curr: observers)
        {
            //System.out.println(curr);
            //System.out.println("here");

            curr.commandComplete(commandFlag, message);
        }
    }
}
