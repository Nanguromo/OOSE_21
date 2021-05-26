package edu.curtin.comp2003.controller.observer;

/**
 * Observer Interface. Used to implement Section 4.2 - Return Messages
 */
public interface IReturnMessage
{
    public void commandComplete(String commandFlag, String message);
}