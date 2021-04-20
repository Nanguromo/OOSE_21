package electricityusage.app.model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public interface IOption
{
    public void doOption() throws IOException, FileNotFoundException;

    public String getFilename();

    //public void findComposite(IOption, String);
}