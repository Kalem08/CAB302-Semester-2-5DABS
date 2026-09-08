package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

//could also be called 'AccountPanel' or 'AuthenticationPanel', as this encompasses both log in AND registration
public class LogInPage extends StackPane
{
    //MARK: Variables
    private Label statusLabel;

    int boxWidth = 300;
    int boxHeight = 400;
    int elementWidth = boxWidth - (Design.padding * 2);
    // int elementHeight = 350;

    public LogInPage()
    {
        //rectangle
        Rectangle background = new Rectangle(boxWidth, boxHeight);
        background.setFill(Design.grey80);
        background.setArcWidth(Design.cornerRadius);
        background.setArcHeight(Design.cornerRadius);
        // background.prefWidth(100);
        // background.prefHeight(200);
        // background.widthProperty().bind(this.widthProperty());
        // background.heightProperty().bind(this.heightProperty());
        
        Label titleLabel = new Label("Log In");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setUnderline(true);

        //textfields
        CustomTextfield usernameTextfield = new CustomTextfield("Username", elementWidth, 30);
        usernameTextfield.setBackgroundColor(Color.WHITE);
        CustomTextfield passwordTextfield = new CustomTextfield("Password", elementWidth, 30, true);
        passwordTextfield.setBackgroundColor(Color.WHITE);
        passwordTextfield.usePasswordField();

        //buttons
        // Button submitButton = new Button("Log in");
        CustomButton submitButton = new CustomButton("Log in", 60, 30);
        submitButton.setBackgroundColor(Color.LIMEGREEN);
        submitButton.setBackgroundHoverColor(Color.GREEN);
        submitButton.setOnMouseClicked(event -> submitCredentials());

        // Button forgotPasswordButton = new Button("Forgot password?");
        CustomButton forgotPasswordButton = new CustomButton("Forgot password?", 150, 30);
        forgotPasswordButton.setBackgroundColor(Color.LIMEGREEN);
        forgotPasswordButton.setBackgroundHoverColor(Color.GREEN);
        forgotPasswordButton.setOnMouseClicked(event -> forgotPassword());

        // Button registerButton = new Button("Register");
        CustomButton registerButton = new CustomButton("Register", 80, 30);
        registerButton.setBackgroundColor(Color.LIMEGREEN);
        registerButton.setBackgroundHoverColor(Color.GREEN);
        registerButton.setOnMouseClicked(event -> openRegisterPage());

        // Button guestButton = new Button("Continue as guest");
        CustomButton guestButton = new CustomButton("Continue as guest", 150, 30);
        guestButton.setBackgroundColor(Color.LIMEGREEN);
        guestButton.setBackgroundHoverColor(Color.GREEN);
        guestButton.setOnMouseClicked(event -> openEntryPage());

        statusLabel = new Label("");

        Region horizontalSpacer = new Region();
        HBox.setHgrow(horizontalSpacer, Priority.ALWAYS);

        HBox buttonsBox1 = new HBox(
            submitButton,
            horizontalSpacer,
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
            passwordTextfield,
            buttonsBox1,
            statusLabel,
            bottomSpacer,
            buttonsBox2
        );
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        // vBox.setFillWidth(false); //set the fillWidth property to false so the children are not resized to fill the width of the VBox
        vBox.setMaxWidth(boxWidth);
        vBox.setMaxHeight(boxHeight);
        
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

        //set background colour
        setStyle("-fx-background-color: #FFFFFF;");
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
