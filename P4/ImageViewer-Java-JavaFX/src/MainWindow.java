import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene; 
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import java.io.File;
import java.net.URL; //needed fot toURI()

/**
 * Represents the main user interface for the Image Viewer application.
 */
public class MainWindow
{
    private Album album;
    private Stage stage;
    
    private BorderPane mainBox = new BorderPane();
    private ImageView imageWidget = new ImageView();
    private Label captionWidget = new Label();
    
    public MainWindow(Album album, Stage stage)
    {
        this.album = album;
        this.stage = stage;
    }
    
    public File chooseAlbumFile()
    {
        FileChooser dialog = new FileChooser();
        dialog.setTitle("Select Album File");
        dialog.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        
        File currentDir = new File(System.getProperty("user.dir"));
        File resourcesDir = new File(currentDir, "resources");        
        if(resourcesDir.isDirectory())
        {
            dialog.setInitialDirectory(resourcesDir);
        }
        else
        {
            dialog.setInitialDirectory(currentDir);
        }
        return dialog.showOpenDialog(stage);
    }
    
    /**
     * Builds the main UI, based on an Album and a JavaFX 'Stage' (which is basically a 
     * pre-existing window).
     */
    public void show() //throws NullPointerException
    {
        System.out.println("second check value of album: " + album);
        Platform.setImplicitExit(true);
        stage.setTitle("JavaFX Image Viewer");
        System.out.println("Value of first photo: "+album.getFirstPhoto() );
        // *** Fix this code so that it loads the initial (first) image. ***
        ImageData currImage = album.getFirstPhoto();
        
        //debug
        try
        {
          System.out.println(currImage + "hi");   
        }catch(NullPointerException e)
        {
            System.out.println("faulty");
        }

        String url = new File(currImage.getFilename()).toURI().toString();
        imageWidget.setImage(new Image(url));
        
        // Add 'mainBox' to the window. This is a container for holding the other bits: the toolbar, 
        // scroller (containing the image), and caption.
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        
        Button prevBtn = new Button("Previous");
        Button nextBtn = new Button("Next");
        ToolBar toolBar = new ToolBar(prevBtn, nextBtn);
        mainBox.setTop(toolBar);
        
        // Set up nextBtnHandler() to be called when nextBtn is clicked, and similarly for prevBtn.
        // (This uses Java 8's 'method reference' feature.)
        prevBtn.setOnAction(this::prevBtnHandler);
        nextBtn.setOnAction(this::nextBtnHandler);
        
        ScrollPane scroller = new ScrollPane();
        scroller.setContent(imageWidget);
        mainBox.setCenter(scroller);
        
        // *** Fix this code so that it displays the caption for the first image. ***
        captionWidget.setText(currImage.getMetadata());
        mainBox.setBottom(captionWidget);
        
        stage.sizeToScene();
        stage.show();
    }
    
    /**
    * Retrieves the album.
    */
    public Album getAlbum()
    {
        return album;
    }

    /**
     * Called to handle "previous" button clicks.
     */
    private void prevBtnHandler(ActionEvent event)
    {
        // *** Fix this code so that it actually displays the previous image & caption. ***
        ImageData imageRecord = album.getPrevPhoto();
        String url = new File(imageRecord.getFilename()).toURI().toString();
        imageWidget.setImage(new Image(url));
        captionWidget.setText(imageRecord.getMetadata());
    }

    /**
     * Called to handle "next" button clicks.
     */
    private void nextBtnHandler(ActionEvent event)
    {
        // *** Fix this code so that it actually displays the next image & caption. ***
        ImageData imageRecord = album.getNextPhoto();

        String url = new File(imageRecord.getFilename()).toURI().toString();
        imageWidget.setImage(new Image(url));
        captionWidget.setText(imageRecord.getMetadata());
    }
}
