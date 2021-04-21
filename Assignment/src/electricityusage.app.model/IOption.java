package electricityusage.app.model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public interface IOption
{
    public String doOption() throws IOException, FileNotFoundException, IllegalArgumentException;

    public String getFilename();

    public CompositeCity getCity();

    //public void findComposite(IOption, String);
}