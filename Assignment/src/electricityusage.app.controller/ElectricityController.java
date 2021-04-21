package electricityusage.app.controller;
import java.util.*;
import java.io.*;
import electricityusage.app.model.*;
import electricityusage.app.model.IOption;
import electricityusage.app.view.*;
/*mport electricityusage.app.model.ElectricityModel;
import electricityusage.app.view.ElectricityView;
import electricityusage.app.view.IOption;*/

//import electricityusage.app.model.ElectricityModel;
//import electricityusage.app.view.ElectricityView;


public class ElectricityController
{
     private ElectricityModel model;
     private ElectricityView view;
    
     public ElectricityController(ElectricityModel m, ElectricityView v)
     {
         this.model = m;
         this.view = v;
     }

     /*public void run()
     {
        try
        {

        }
        catch(IllegalArgumentException e)
        {
            view.displayError(e.getMessage());
        }
     }*/

     public void doFirstOption()
     {
         try
         {
             IOption op = model.getFirstOption();
             String temp = op.doOption();
         }
         catch(IllegalArgumentException e)
         {
             view.displayError(e.getMessage());
             System.exit(0);
         }
        catch(IOException e)
        {
            view.displayError(e.getMessage());
            System.exit(0);
        }
        /*catch(FileNotFoundException e)
        {
            view.displayError(e.getMessage());
        }*/
     }

     public void doSecondOption()
     {
        try
        {
            IOption op = model.getSecondOption();
            String writeordisplay = op.doOption();
            view.display(writeordisplay);
        }
        catch(IllegalArgumentException e)
        {
            view.displayError(e.getMessage());
            System.exit(0);
        }
        catch(IOException e)
        {
            view.displayError(e.getMessage());
            System.exit(0);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            view.displayError(e.getMessage());
            System.exit(0);
        }
     }

}