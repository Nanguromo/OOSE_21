package electricityusage.app.model;
import java.util.*;
import java.io.*;
import electricityusage.app.model.CompositeCity;

/*
* Strategy Class for Strategy Pattern. This class's strategy is to do the display option. Outputs to terminal.
*/


public class Display implements IOption
{
    private String[] args;
    public CompositeCity city;

    public Display(String[] args, CompositeCity city)
    {
        this.args = args;
        this.city = city;
    }

    @Override
    public String doOption()
    {
        String out = city.toString();
        String temp = "\n\nThe total power consumption, P_total, for the city is the total power consumed across each category.\n\tP_total = " + String.format("%.2f",city.getTotalPowerConsumption()) + " kW";
        return out + temp;
    }
    
    @Override
    public String getFilename()
    {
        //irrelevant to this implementation
        return "";
    }

    @Override
    public CompositeCity getCity(){return this.city;}
}