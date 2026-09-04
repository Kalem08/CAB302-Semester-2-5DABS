package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
// import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LogInPage extends StackPane
{
    //MARK: Variables
    private Label statusLabel;

    int elementWidth = 300;
    int elementHeight = 350;

    public LogInPage()
    {
        //rectangle
        Rectangle background = new Rectangle(elementWidth, elementHeight);
        background.setFill(Color.LIGHTGRAY);
        // background.prefWidth(100);
        // background.prefHeight(200);
        // background.widthProperty().bind(this.widthProperty());
        // background.heightProperty().bind(this.heightProperty());
        
        Label titleLabel = new Label("Log In");
        statusLabel = new Label("* * *");

        //textfields
        TextField usernameTextfield = new TextField("Username");
        TextField passwordTextField = new TextField("Password");
        usernameTextfield.setPrefWidth(300);
        passwordTextField.setPrefWidth(300);

        //buttons
        Button submitButton = new Button("Log in");
        submitButton.setOnAction(event -> submitCredentials());
        Button forgotPasswordButton = new Button("Forgot password?");
        forgotPasswordButton.setOnAction(event -> forgotPassword());
        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> openRegisterPage());
        Button guestButton = new Button("Continue as guest");
        guestButton.setOnAction(event -> openEntryPage());

        Region hSpacer = new Region();
        HBox.setHgrow(hSpacer, Priority.ALWAYS);

        HBox buttonsBox1 = new HBox(
            submitButton,
            hSpacer,
            forgotPasswordButton
        );
        buttonsBox1.setSpacing(10);
        buttonsBox1.setAlignment(Pos.CENTER_LEFT);

        HBox buttonsBox2 = new HBox(
            registerButton,
            guestButton
        );
        buttonsBox2.setSpacing(10);
        buttonsBox2.setAlignment(Pos.CENTER);

        Region topSpacer = new Region();
        Region bottomSpacer = new Region();
        // topSpacer.prefHeight(10);
        // bottomSpacer.prefHeight(10);
        VBox.setVgrow(topSpacer, Priority.ALWAYS);
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
            titleLabel,
            topSpacer,
            usernameTextfield,
            passwordTextField,
            buttonsBox1,
            statusLabel,
            bottomSpacer,
            buttonsBox2
        );
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        // vBox.setFillWidth(false); //set the fillWidth property to false so the children are not resized to fill the width of the VBox
        vBox.setMaxWidth(elementWidth);
        vBox.setMaxHeight(elementHeight);
        
        //add elements to StackPane
        getChildren().addAll(
            background,
            vBox
        );
        //these values don't do anything if this StackPane is the root of a scene (it will always maximise to window size)
        // prefWidth(100);
        // prefHeight(200);
        // //min
        // setMinWidth(10);
        // setMinHeight(10);
        // //max
        // setMaxWidth(400);
        // setMaxHeight(400);
    }

    void openEntryPage()
    {
        HelloApplication.scene.setRoot(new EntryPage());
    }

    void openRegisterPage()
    {
        System.out.println("Register");
    }

    void submitCredentials()
    {
        System.out.println("Attempt login");

        statusLabel.setText("Checking creditials...");
    }

    void forgotPassword()
    {
        System.out.println("User forgot password");
    }
}