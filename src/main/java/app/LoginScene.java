package app;

//MARK: Imports
import javafx.application.Application;
import java.io.IOException;
// import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginScene //extends Application
{
    //MARK: Variables
    private Label titleLabel;
    private TextField usernameTextfield;
    private TextField passwordTextField;
    private Button submitButton;
    private Label statusLabel;

    // @Override
    public void create(Stage stage) // throws IOException
    {
        //create a scene from scatch
        VBox root = new VBox();
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setSpacing(15.0);
        root.setFillWidth(false); //set the fillWidth property to false so the children are not resized to fill the width of the VBox

        titleLabel = new Label("Login");
        statusLabel = new Label("* * *");
        //textfields
        usernameTextfield = new TextField();
        usernameTextfield.setText("Username");
        passwordTextField = new TextField();
        passwordTextField.setText("Password");
        //button
        submitButton = new Button("Login");
        submitButton.setOnAction(event -> submitCredentials());

        //add the children to the root vbox
        root.getChildren().addAll(titleLabel, usernameTextfield, passwordTextField, submitButton, statusLabel);

        //MARK: Scene
        //define the scene, add to the stage (window) and show the stage
        Scene scene = new Scene(root, 960, 540);
        stage.setTitle("Login Page"); //the window title
        stage.setScene(scene); //sets the scene
        stage.show();
    }

    void submitCredentials()
    {
        System.out.println("Attempt login");

        statusLabel.setText("Checking creditials...");
    }
}