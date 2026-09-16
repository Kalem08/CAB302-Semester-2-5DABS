package app;

//MARK: Imports
import app.pages.EntryPage;
import javafx.application.Application;
import java.io.IOException;

import app.pages.LogInPage;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
// import javafx.scene.control.*;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;

//main class (an extension of 'Application')
public class HelloApplication extends Application
{
    //because this variable is static, any other class can access this without an object reference
    public static Scene scene;

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
        //use nodes/pages from .java files
        else
        {
            //define the inital page here
            // Parent root = new EntryPage();
            Parent root = new LogInPage(); //a temporary swap

            //MARK: Scene
            //define the scene, add to the stage/window, and display the stage
            scene = new Scene(root, 960, 540); //small window
            stage.setTitle("Plantopedia"); //the window title
            stage.setScene(scene);
            stage.setMaximized(true); //start as a maximised window
            stage.show();

            //change background colour
            // root.setStyle("-fx-background-color: #202020;"); //works for GridPane
            // scene.setFill(Color.DARKBLUE); //works for all other elements
            
            //use .setRoot to change primary node/container that is present in the scene
            // scene.setRoot(header);
        }
    }

    //shorthand method for 'System.out.println()'
    public static void print(Object value)
    {
        System.out.println(value);
    }
}