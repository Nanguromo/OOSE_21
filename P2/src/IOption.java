import java.io.*;
import java.util.*;

//Purpose: To facilitate implementing, "The Strategy Pattern" as a solution to practical 2, question 3.

public interface IOption
{
    public String doOption(String s);   

    public boolean requiresText();
}
