package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import User.authManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import app.components.CustomButton;
import app.components.CustomTextfield;
import app.components.CustomButton.HoverState;
import app.pages.EntryPage;

public class AuthenticationPanel extends VBox
{
    //MARK: Variables
    private Label titleLabel;
    private Label statusLabel;
    private Label bottomLabel;
    private CustomTextfield usernameTextfield;
    private CustomTextfield passwordTextfield;
    private CustomButton logInButton;
    private CustomButton registerButton;
    private CustomButton forgotPasswordButton;

    // private boolean registrationMode; //defines whether the page shows 'log in' or 'register'
    AuthMode registrationMode;
    int boxWidth = 300;
    int boxHeight = 400;
    int elementWidth = boxWidth - (Design.padding * 2);
    Color darkTextColour = Design.grey20;

    Boolean inRegisterMode()
    {
        return registrationMode == AuthMode.Register;
    }

    //empty constructor
    public AuthenticationPanel()
    {
        // this(false); //default to 'log in' mode
    }

    //MARK: Constructor
    public AuthenticationPanel(AuthMode mode)
    {
        this.registrationMode = mode;
        
        //rectangle
        Rectangle frame = new Rectangle(boxWidth, boxHeight);
        frame.setFill(Design.grey90);
        frame.setArcWidth(Design.largeCornerRadius);
        frame.setArcHeight(Design.largeCornerRadius);
        
        //MARK: Top
        //title
        titleLabel = new Label();
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        // titleLabel.setUnderline(true);
        titleLabel.setTextFill(darkTextColour);

        //MARK: Username
        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        usernameLabel.setMaxWidth(Double.MAX_VALUE); //align to the left
        usernameLabel.setTextFill(darkTextColour);

        usernameTextfield = new CustomTextfield("Username", elementWidth, 30);
        usernameTextfield.setBackgroundColor(Color.WHITE);
        usernameTextfield.background.setArcWidth(Design.cornerRadius);
        usernameTextfield.background.setArcHeight(Design.cornerRadius);

        VBox usernameContainer = new VBox(
            usernameLabel,
            usernameTextfield
        );
        usernameContainer.setSpacing(4);

        //MARK: Password
        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        passwordLabel.setMaxWidth(Double.MAX_VALUE);
        passwordLabel.setTextFill(darkTextColour);

        passwordTextfield = new CustomTextfield("Password", elementWidth, 30, true);
        passwordTextfield.setBackgroundColor(Color.WHITE);
        passwordTextfield.background.setArcWidth(Design.cornerRadius);
        passwordTextfield.background.setArcHeight(Design.cornerRadius);
        passwordTextfield.textfield.setOnAction(event -> submitCredentials());

        VBox passwordContainer = new VBox(
            passwordLabel,
            passwordTextfield
        );
        passwordContainer.setSpacing(4);

        //clear error message when user begins retyping
        usernameTextfield.textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            statusLabel.setText("");
        });

        passwordTextfield.textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            statusLabel.setText("");
        });

        //MARK: Middle
        bottomLabel = new Label("");

        //log in buttom
        logInButton = new CustomButton("", 120, 30);
        logInButton.setCornerRadius(30);
        logInButton.setBackgroundColor(Design.softGreen);
        logInButton.setBackgroundHoverColor(Design.darkSoftGreen);
        logInButton.setOnMouseClicked(event -> submitCredentials());
        // logInButton.setDefaultButton(true); 

        //forgot password button
        forgotPasswordButton = new CustomButton("Forgot password", 50, 20);
        forgotPasswordButton.setCornerRadius(0);
        forgotPasswordButton.label.setFont(Font.font(12));
        forgotPasswordButton.setBackgroundVisibility(false);
        forgotPasswordButton.setTextColour(darkTextColour);
        forgotPasswordButton.setTextHoverColour(Design.black);
        forgotPasswordButton.shouldUnderlineLabel = HoverState.onHover;

        statusLabel = new Label("");
        statusLabel.setTextFill(Color.RED);
        statusLabel.setMaxWidth(elementWidth);
        statusLabel.setWrapText(true);
        statusLabel.setAlignment(Pos.CENTER);
        statusLabel.setTextAlignment(TextAlignment.CENTER);

        //MARK: Bottom
        //register button
        registerButton = new CustomButton("", 100, 30);
        registerButton.setBackgroundColor(Color.LIMEGREEN);
        registerButton.setBackgroundHoverColor(Color.GREEN);
        registerButton.setBackgroundVisibility(false);
        registerButton.label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        registerButton.setTextColour(darkTextColour);
        registerButton.setTextHoverColour(darkTextColour);
        registerButton.setOnMouseClicked(event -> switchAuthMode());
        registerButton.shouldUnderlineLabel = HoverState.onHover;

        //guest button
        CustomButton guestButton = new CustomButton("Continue as guest", 150, 30);
        guestButton.setBackgroundVisibility(false);
        guestButton.label.setFont(Font.font(12));
        guestButton.label.setTextFill(Design.grey50);
        guestButton.setTextColour(Design.grey50);
        guestButton.setTextHoverColour(Design.grey50);
        guestButton.setOnMouseClicked(event -> openEntryPage());
        guestButton.shouldUnderlineLabel = HoverState.onHover;

        Region topSpacer2 = new Region();
        VBox.setVgrow(topSpacer2, Priority.ALWAYS);
        Region bottomSpacer2 = new Region();
        VBox.setVgrow(bottomSpacer2, Priority.ALWAYS);
        
        //MARK: VBox
        VBox content = new VBox(
            titleLabel,
            topSpacer2,
            usernameContainer,
            passwordContainer,
            statusLabel,
            logInButton,
            forgotPasswordButton,
            bottomSpacer2,
            bottomLabel,
            registerButton
        );
        content.setSpacing(Design.spacing);
        content.setPadding(new Insets(Design.padding));
        content.setAlignment(Pos.CENTER);
        content.setMaxWidth(boxWidth);
        content.setMaxHeight(boxHeight);

        StackPane stack = new StackPane(
            frame,
            content
        );
                
        getChildren().addAll(
            stack,
            guestButton
        );

        updateMode();
    }

    //MARK: Methods
    void openEntryPage()
    {
        HelloApplication.scene.setRoot(new EntryPage());
    }

    void switchAuthMode()
    {
        registrationMode = inRegisterMode() ? AuthMode.Login : AuthMode.Register; //toggle boolean
        statusLabel.setText(""); //reset text
        updateMode();
    }

    void submitCredentials()
    {
        String username = usernameTextfield.getText().trim();
        String password = passwordTextfield.getText();
        authManager manager = new authManager(username, password);

        if (inRegisterMode())
        {
            if (manager.Registration()) {
                statusLabel.setText("Account created. You can now log in.");
                registrationMode = AuthMode.Login;
                passwordTextfield.clear();
                updateMode();
            } else {
                statusLabel.setText("Username exists or password is invalid (8+ chars, upper, lower, special).");
            }
        } else if (manager.authenticate()) {
            statusLabel.setText("Login successful.");
            HelloApplication.scene.setRoot(new EntryPage());
        } else {
            statusLabel.setText("Username or password is incorrect.");
        }
    }

    void forgotPassword()
    {
        //not implemented yet
        System.out.println("forgotPassword not implemented yet");
    }

    //updates Nodes to correctly represent the current mode
    private void updateMode()
    {
        titleLabel.setText(inRegisterMode() ? "Register" : "Log In");
        logInButton.setText(inRegisterMode() ? "Create account" : "Log in");
        bottomLabel.setText(inRegisterMode() ? "Already have an account?" : "Don't have an account?");
        registerButton.setText(inRegisterMode() ? "Log in" : "Register");
        if (forgotPasswordButton != null)
        {
            forgotPasswordButton.setVisible(!inRegisterMode()); //show or hide
            forgotPasswordButton.setManaged(!inRegisterMode()); //when false, tells the parent layout container to ignore the button during its automatic layout and sizing calculations
        }
    }

    public enum AuthMode
    {
        Login,
        Register
    }
}
