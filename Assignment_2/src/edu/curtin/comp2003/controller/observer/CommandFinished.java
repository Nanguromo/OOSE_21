package edu.curtin.comp2003.controller.observer;

import java.util.*;
import edu.curtin.comp2003.rover.EarthComm;

public class CommandFinished
{
    private Set<IReturnMessage> observers;
    private EarthComm comm;

    public CommandFinished(EarthComm comm)
    {
        this.observers = new HashSet<>();
        this.comm = comm;

        //The set is filled with anonymous classes. It would be a waste of time and uncessary code (and files) required if I were to make a Concrete class for each Return Message
        //The trade-off for using anonymous classes is that this constructor is quite hefty in size, but it is not convoluted... In my opinion

        observers.add(new IReturnMessage() //anonymous class for invalid command return message. This was done because this observer is very simple
        {
            @Override public void commandComplete(String commandFlag, String message)
            {
                if(commandFlag.equals("!"))
                {
                    comm.sendMessage("! " + message);
                }
            }
        });

        //fairly simple instance, hence, an anonymous class was used again for the finished driving message Observer
        observers.add(new IReturnMessage(){
            @Override public void commandComplete(String commandFlag, String message)
            {  
                if(commandFlag.equals("D"))
                {
                    comm.sendMessage("D");
                }
            }
        });

        observers.add(new IReturnMessage(){
            @Override public void commandComplete(String commandFlag, String message)
            {  
                if(commandFlag.equals("P"))
                {
                    comm.sendMessage("P " + message);
                }
            }
        });

        observers.add(new IReturnMessage(){
            @Override public void commandComplete(String commandFlag, String message)
            {  
                if(commandFlag.equals("E"))
                {
                    comm.sendMessage("E " + message);
                }
            }
        });

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

    public void addObserver(IReturnMessage ob)
    {
        observers.add(ob);
    }

    public void removeObserver(IReturnMessage ob)
    {
        observers.remove(ob);
    }
    
    public void notifyObservers(String commandFlag, String message)
    {
        for(IReturnMessage curr: observers)
        {
            curr.commandComplete(commandFlag, message);
        }
    }
}
