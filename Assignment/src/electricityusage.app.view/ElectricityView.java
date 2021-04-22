package electricityusage.app.view;
import java.util.*;
import java.io.*;
/*
* Deals with View portion of MVC. This class makes use of the IInput and IOutput Interfaces who both implement The Strategy Pattern
*/
public class ElectricityView 
{
    private String[] args;

    public ElectricityView(String[] args)
    {
        this.args = args;
    }

    public void displayError(String str)
    {  
        System.out.println(str);
        System.exit(1);//exit gracefully

    }

    public void display(String str)
    {
        System.out.println(str);
    }
}