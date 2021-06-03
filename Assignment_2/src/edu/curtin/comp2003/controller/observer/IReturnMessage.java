package edu.curtin.comp2003.controller.observer;

/**
 * Interface used to implement The Observer Pattern to solve Section 4.2 - Return Messages
 */
public interface IReturnMessage
{
    public void commandComplete(String commandFlag, String message);
}