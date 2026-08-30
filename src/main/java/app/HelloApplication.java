//package com.example.cab302_jfx_project;
package app;

//MARK: Imports
import javafx.application.Application;
import java.io.IOException;
// import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
import javafx.scene.control.*; // load everything in the control package
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//main class (an extension of 'Application')
public class HelloApplication extends Application
{
    //MARK: Variables
    private Label label2;

    Boolean useLoginScene = true;

    //MARK: Start
    @Override
    public void start(Stage stage) throws IOException //'start()' method is defined in the 'Application' class that 'HelloApplication' inherits from (and overrides)
    {
        if (useLoginScene)
        {
            LoginScene logScn = new LoginScene();
            logScn.create(stage);
        }
        else
        {
            //load the .fxml file into a scene variable
            // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            // Scene scene = new Scene(fxmlLoader.load(), 960, 540); //create scene (with width and height)

            //create a scene from scatch
            VBox root = new VBox();
            root.setAlignment(javafx.geometry.Pos.CENTER);
            root.setSpacing(15.0);
            root.setFillWidth(false); //set the fillWidth property to false so the children are not resized to fill the width of the VBox

            //create a TextField, a Label, and an HBox with appropriate text
            TextField textField = new TextField();
            textField.setText("TextField");
            Label label = new Label("Label");
            //the HBox is used to hold the buttons
            HBox hbox = new HBox();
            hbox.setAlignment(javafx.geometry.Pos.CENTER);
            hbox.setSpacing(15.0);
            Button button1 = new Button("Button 1");
            Button button2 = new Button("Button 2");
            Button button3 = new Button("Button 3");

            Button button4 = new Button("Interactive button");
            button4.setOnAction(event -> buttonMethod());

            label2 = new Label("My Label");

            //add the buttons to the HBox
            hbox.getChildren().addAll(button1, button2, button3);
            //add the children to the root vbox
            root.getChildren().addAll(textField, label, hbox, button4, label2);

            //MARK: Scene
            //define the scene, add to the stage (window) and show the stage
            Scene scene = new Scene(root, 960, 540);
            stage.setTitle("Window Title"); //the window title
            stage.setScene(scene); //sets the scene
            stage.show();
        }
    }

    void buttonMethod()
    {
        System.out.println("Button clicked!");

        label2.setText("Button pressed!");
    }
}