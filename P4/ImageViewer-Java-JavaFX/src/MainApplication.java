import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;
import java.util.*;

/**
 * Main class representing the entry point (and controller) of the application.
 */
public class MainApplication extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args); // Run JavaFX
        // This will effectively do 'new MainApplication()' and then call 'start(...)'.
    }
    
    /**
     * Loads an image album and then creates a window to display it.
     */
    @Override
    public void start(Stage stage)
    {
        // Construct an album object.
        Album album = new Album();
        
        // Make a new window.
        MainWindow window = new MainWindow(album, stage);
        /*if(window != null)
        {
            throw new IllegalArgumentException("Window null");
        }    */    
        
        // Choose which album to load.
        File albumFile = window.chooseAlbumFile();
        
        if(albumFile == null)
        {
            Platform.exit(); // Otherwise JavaFX keeps the program open, doing nothing.
        }
        else
        {
            try
            {
                // Attempt to read an album file.
                readAlbumFile(albumFile, album);
                System.out.println("value of album: " +album);
                //window = new MainWindow(album, stage);
                // Display the window.
                window.show();

            }
            catch(IOException e)
            {
                System.err.println("Error while reading " + albumFile);
                Platform.exit();
            }
            catch(NullPointerException e)
            {
                System.err.println("Window is  null");
                Platform.exit();
            }
        }
    }
    
    /**
     * Reads an album file, given a filename and an Album object. Returns true if
     * successful, or false if the file could not be read.
     *
     * @param albumFile The file storing the list of image filenames and their captions.
     * @param album An Album object to populate.
     * 
     * @throws IOException If the file could not be read.
     */
    private static void readAlbumFile(File albumFile, Album album) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(albumFile));
        String line = reader.readLine();
        //int ctr=2;
        ImageData decor1 = null;
        ImageData decor2 = null;
        ImageData noDecor = null;

        while(line != null)
        {
            if(line.trim().length() > 0) // Ignore blank lines
            {
                //String[] parts = line.split(":", 2);
                String[] parts  = line.split(":");

                String imageFilename = albumFile.getParent() + File.separatorChar + parts[0];
                String imageCaption = "";
                String copy = "";
                if(parts.length >= 2)
                {
                    imageCaption = parts[1];
                    //ctr++;
                    int i = 0;

                    if(parts.length == 2)//only filename and caption present; no decors
                    {
                        noDecor = new Caption(new ImageRecord(imageFilename), imageCaption);
                    }
                    else //at least one decor present
                    {

                        for(i = 2; i < parts.length; i++)
                        {
                            copy = new String(parts[i]);
                            String part2[] = parts[i].split("=");
                            System.out.println("here is L: "+copy );

                            switch(i)
                            {
                                case 2:
                                    if(part2[0].equals("gps"))
                                    {
                                        decor1 = new Caption(new GPS(new ImageRecord(imageFilename), copy),imageCaption); 
                                        //System.out.println(decor1.getGPS());
                                    }
                                    else if(part2[0].equals("rating"))
                                    {
                                        decor1 = new Caption(new Rating(new ImageRecord(imageFilename), copy),imageCaption); 
                                        //System.out.println(decor1.getRating());
                                    }
                                    else if(part2[0].equals("date"))
                                    {
                                        decor1 = new Caption(new Date(new ImageRecord(imageFilename), copy),imageCaption); 
                                        //System.out.println(decor1.getCaption());
                                    }
                                    break;
                                case 3: // case 3 can only happen if case 2 was done, hence, decor 1 will be initiated
                                    if(part2[0].equals("gps"))
                                    {
                                        decor2 = new GPS(decor1, copy);
                                        System.out.println(decor2.getGPS());
                                    }
                                    else if(part2[0].equals("rating"))
                                    {
                                        decor2 = new Rating(decor1, copy);
                                        System.out.println("case 3.1 reached!!!!!!!!->" +decor1);
                                    }
                                    else if(part2[0].equals("date"))
                                    {
                                        decor2 = new Date(decor1, copy);
                                        System.out.println("case 3.2 reached!!!!!!!!->"+decor2);
                                    }
                                    break;
                                default:
                                //nothing
                            }
                        }
                    }
                }
                System.out.println("DIFFERENT SCOPE DECOR1->"+decor1);
                // Insert your code here to add a new image to the album.
                switch(parts.length)
                {
                    case 2:
                        System.out.println("reached case 1");
                        album.addPhoto(noDecor);
                        break;
                    case 3:
                        System.out.println("reached case 2");
                        System.out.println("2DIFFERENT SCOPE DECOR1->"+decor1);
                        System.out.println("value of decor1 added: " +decor1);
                        album.addPhoto(decor1);
                        System.out.println(decor1 + "->case2  ");
                        break;
                    case 4:
                        System.out.println("reached case 3");
                        System.out.println("2DIFFERENT SCOPE DECOR1->"+decor2);
                        System.out.println("value of decor2 added: " +decor2);
                        album.addPhoto(decor2);
                        System.out.println(decor2 + "->case 3");

                        break;
                    default:
                    //nothing
                }                
            }
                        
            line = reader.readLine();
        }
        reader.close();
    }

}
