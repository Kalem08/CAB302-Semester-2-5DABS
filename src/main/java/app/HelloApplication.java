package app;

//MARK: Imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
// import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
import javafx.scene.control.*; // load everything in the control package
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//main class (an extension of 'Application')
public class HelloApplication extends Application
{
    //MARK: Variables
    private Label label2;

    // Boolean useEntryScene = false;
    Boolean useLoginScene = false;
    Boolean useFXMLLoader = false;

    //MARK: Start
    @Override
    public void start(Stage stage) throws IOException //'start()' method is defined in the 'Application' class that 'HelloApplication' inherits from (and overrides)
    {
        if (useLoginScene)
        {
            LoginScene logScn = new LoginScene();
            logScn.create(stage);
        }
        else if (useFXMLLoader)
        {
            Loader loaderScn = new Loader();
            loaderScn.start(stage);
        }
        //default to entry scene
        else
        {
            // StackPane root = new StackPane();
            EntryScene entryScn = new EntryScene();
            entryScn.create(stage);

            //MARK: Scene
            //define the scene, add to the stage (window) and show the stage
            // Scene scene = new Scene(root, 960, 540);
            // stage.setTitle("Entry Page"); //the window title
            // stage.setScene(scene); //sets the scene
            // stage.show();
        }
    }

    void buttonMethod()
    {
        System.out.println("Button clicked!");

        label2.setText("Button pressed!");
    }
}