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
import User.authManager;

//could also be called 'AccountPanel' or 'AuthenticationPanel', as this encompasses both log in AND registration
public class LogInPage extends StackPane
{
    //MARK: Variables
    private Label statusLabel;
    private Label titleLabel;
    private CustomTextfield usernameTextfield;
    private CustomTextfield passwordTextfield;
    private CustomButton submitButton;
    private CustomButton registerButton;
    private CustomButton forgotPasswordButton;
    private boolean registrationMode;

    int boxWidth = 300;
    int boxHeight = 400;
    int elementWidth = boxWidth - (Design.padding * 2);
    // int elementHeight = 350;

    public LogInPage()
    {
        this(false);
    }

    public LogInPage(boolean registrationMode)
    {
        this.registrationMode = registrationMode;
        //rectangle
        Rectangle background = new Rectangle(boxWidth, boxHeight);
        background.setFill(Design.grey80);
        background.setArcWidth(Design.cornerRadius);
        background.setArcHeight(Design.cornerRadius);
        // background.prefWidth(100);
        // background.prefHeight(200);
        // background.widthProperty().bind(this.widthProperty());
        // background.heightProperty().bind(this.heightProperty());
        
        titleLabel = new Label();
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setUnderline(true);

        //textfields
        usernameTextfield = new CustomTextfield("Username", elementWidth, 30);
        usernameTextfield.setBackgroundColor(Color.WHITE);
        passwordTextfield = new CustomTextfield("Password", elementWidth, 30, true);
        passwordTextfield.setBackgroundColor(Color.WHITE);

        //buttons
        // Button submitButton = new Button("Log in");
        submitButton = new CustomButton("", 120, 30);
        submitButton.setBackgroundColor(Color.LIMEGREEN);
        submitButton.setBackgroundHoverColor(Color.GREEN);
        submitButton.setOnMouseClicked(event -> submitCredentials());

        // Button forgotPasswordButton = new Button("Forgot password?");
        forgotPasswordButton = new CustomButton("Forgot password?", 150, 30);
        forgotPasswordButton.setBackgroundColor(Color.LIMEGREEN);
        forgotPasswordButton.setBackgroundHoverColor(Color.GREEN);
        forgotPasswordButton.setOnMouseClicked(event -> forgotPassword());

        // Button registerButton = new Button("Register");
        registerButton = new CustomButton("", 100, 30);
        registerButton.setBackgroundColor(Color.LIMEGREEN);
        registerButton.setBackgroundHoverColor(Color.GREEN);
        registerButton.setOnMouseClicked(event -> openRegisterPage());

        // Button guestButton = new Button("Continue as guest");
        CustomButton guestButton = new CustomButton("Continue as guest", 150, 30);
        guestButton.setBackgroundColor(Color.LIMEGREEN);
        guestButton.setBackgroundHoverColor(Color.GREEN);
        guestButton.setOnMouseClicked(event -> openEntryPage());

        statusLabel = new Label("");
        statusLabel.setMaxWidth(elementWidth);
        statusLabel.setWrapText(true);
        statusLabel.setAlignment(Pos.CENTER);
        statusLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

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

        updateMode();
    }

    void openEntryPage()
    {
        HelloApplication.scene.setRoot(new EntryPage());
    }

    void openRegisterPage()
    {
        registrationMode = !registrationMode;
        statusLabel.setText("");
        updateMode();
    }

    void submitCredentials()
    {
        String username = usernameTextfield.getText().trim();
        String password = passwordTextfield.getText();
        authManager manager = new authManager(username, password);

        if (registrationMode) {
            if (manager.Registration()) {
                statusLabel.setText("Account created. You can now log in.");
                registrationMode = false;
                passwordTextfield.clear();
                updateMode();
            } else {
                statusLabel.setText("Username exists or password is invalid (8+ chars, upper, lower, special).");
            }
        } else if (manager.authenticate()) {
            statusLabel.setText("Login successful.");
            HelloApplication.scene.setRoot(new EntryPage());
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    void forgotPassword()
    {
        System.out.println("User forgot password");
    }

    private void updateMode()
    {
        titleLabel.setText(registrationMode ? "Register" : "Log In");
        submitButton.setText(registrationMode ? "Create account" : "Log in");
        registerButton.setText(registrationMode ? "Back to login" : "Register");
        forgotPasswordButton.setVisible(!registrationMode);
        forgotPasswordButton.setManaged(!registrationMode);
    }
}
