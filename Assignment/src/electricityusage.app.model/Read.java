package electricityusage.app.model;
import java.util.*;

import electricityusage.app.model.CompositeCity;
import electricityusage.app.model.ICity;
import electricityusage.app.model.PartOfCity;

import java.io.*;

/*
* Strategy Class for Strategy Pattern. This class's strategy is to do the read option. Reads data from a given (valid) file.
*/

public class Read implements IOption
{
    private String[] args;
    private String readFilename;
    private CompositeCity readCity;

    public Read(String[] args)
    {
        this.args = args;
    }

    @Override
    public String doOption() throws IOException, IllegalArgumentException
    {
        //args[1] is the filename to read from
        try
        {
            //additional vaidation checks for command line arguments invalid formats. No point putting declarations before these if exceptions get thrown, it was a waste of time.
            //Hence, declaratinos performed after these final command line argument format validations
            if(args[1].equals(args[0]))
            {
                throw new IllegalArgumentException("Error: \"-r\" is not a file name! \"-r\" must be followed by a file name!");
            }
            if(args[1].equals("-g"))
            {
                throw new IllegalArgumentException("Error: \"-g\" is not a file name! \"-r\" must be followed by a file name!");
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(args[1]));//arg[0] is '-r'. arg[1] is the filename.
            String line = reader.readLine();
            CompositeCity city = null; 
            int k = 1;//depth

            while(line != null)
            {
                if(line.trim().length() > 0) //ignores blank lines
                {
                    String[] parts = line.split(",");
                    //System.out.println(parts[0]);

                    if(parts.length == 1)//First line exists. Root node was found. parts[0]-> name of root
                    {
                        city = new CompositeCity(parts[0]);//depth, k = 1 here as it is currently the root node.
                        city.setDepth(1);//root
                        //System.out.println(city.getName());
                    }
                    if(parts.length == 2)//found a composite node. parts[0]-> name of new composite. parts[1]-> name of parent composite
                    {
                        CompositeCity temp = new CompositeCity(parts[0]);
                        temp.setDepth(city.getCompositeDepth(parts[1])+1);//gets depth of parent composite, then adds 1 new composite child is one level deeper
                        //temp.setDepth(city.getComposite(parts[1]));
                        //System.out.println(temp.getName() + "2");
                        city.addComposite(temp, parts[1]);
                    }
                    if(parts.length > 2)//found a leaf node. parts[0]-> name of leaf. parts[1]-> name of parent (composite). not necessarily the root. part[2..parts.length-1] power categories
                    {
                        PartOfCity temp = new PartOfCity(parts[0]);
                        for(int i = 2; i < parts.length; i++)
                        {
                            String parts2[] = parts[i].split("=");//split into into power category and power consumption value, which still needs to be converted from string to double
                            String category = parts2[0];
                            double powerConsump = Double.parseDouble(parts2[1]);
                            //System.out.println(parts2[1]);
                            temp.addPowerCategory(category, powerConsump);
                        }
                        temp.setDepth(city.getCompositeDepth(parts[1])+1);
                        city.addComposite(temp, parts[1]);
                    }
                }
                line = reader.readLine();
            }
            this.readCity = city;
            reader.close();
            //System.out.println(city.getTotalPowerConsumption());
        }
        catch(IOException e)
        {
            throw new IOException("Error: Could not find file!");
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
        return "";
    }

    @Override
    public String getFilename(){return this.readFilename;}

    @Override
    public CompositeCity getCity(){return this.readCity;}

}