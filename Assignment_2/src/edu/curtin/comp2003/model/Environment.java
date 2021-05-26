package edu.curtin.comp2003.model;

import java.util.*;

public class Environment
{
    private double temperature;
    private double lightLevel;
    private double visibility;

    public Environment(double temp, double light, double vis)
    {
        temperature = temp;
        lightLevel = light;
        visibility = vis;
    }

    public double getTemperature()
    {
        return temperature;
    }

    public double getLightLevel()
    {
        return lightLevel;  
    }

    public double getVisibility()
    {
        return visibility;
    }
}