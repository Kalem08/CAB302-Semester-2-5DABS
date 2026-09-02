package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LoginPage extends VBox
{
    //MARK: Variables
    private Label titleLabel;
    private TextField usernameTextfield;
    private TextField passwordTextField;
    private Button submitButton;
    private Label statusLabel;

    public LoginPage()
    {
        titleLabel = new Label("Login");
        statusLabel = new Label("* * *");
        //textfields
        usernameTextfield = new TextField("Username");
        passwordTextField = new TextField("Password");
        //button
        submitButton = new Button("Login");
        submitButton.setOnAction(event -> submitCredentials());

        // VBox root = new VBox();
        setAlignment(javafx.geometry.Pos.CENTER);
        setSpacing(15.0);
        setFillWidth(false); //set the fillWidth property to false so the children are not resized to fill the width of the VBox

        //add the children to the root vbox
        getChildren().addAll(titleLabel, usernameTextfield, passwordTextField, submitButton, statusLabel);
    }

    void submitCredentials()
    {
        System.out.println("Attempt login");

        statusLabel.setText("Checking creditials...");
    }
}