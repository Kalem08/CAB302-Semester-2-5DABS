package app;

//MARK: Imports
import javafx.application.Application;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
// import javafx.scene.control.*;
// import javafx.scene.layout.*;

//main class (an extension of 'Application')
public class HelloApplication extends Application
{
    Boolean useLoginPage = false;
    Boolean useFXMLLoader = false;

    //MARK: Start
    @Override
    public void start(Stage stage) throws IOException //'start()' method is defined in the 'Application' class that 'HelloApplication' inherits from (and overrides)
    {
        if (useFXMLLoader)
        {
            Loader loader = new Loader();
            loader.start(stage);
        }
        //use nodes from .java files
        else
        {
            Parent root; //= new EntryScene();

            if (useLoginPage)
            {
                root = new LoginPage();
            }
            //default to entry scene
            else
            {
                root = new EntryPage();
            }

            //MARK: Scene
            //define the scene, add to the stage/window, and display the stage
            Scene scene = new Scene(root, 960, 540);
            stage.setTitle("Window Title"); //the window title
            stage.setScene(scene); //sets the scene
            stage.show();
            
            //use .setRoot to change primary node/container that is present in the scene
            // scene.setRoot(header);
        }
    }
}