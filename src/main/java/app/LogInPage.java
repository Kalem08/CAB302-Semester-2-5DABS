package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.scene.effect.*; //for image colouring
// import javafx.scene.text.TextAlignment;

import User.authManager;
import app.CustomButton.HoverState; //an enum for on-hover settings

//could also be called 'AccountPanel' or 'AuthenticationPanel', as this encompasses both log in AND registration
public class LogInPage extends StackPane
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

    private boolean registrationMode; //defines whether the page shows 'log in' or 'register'
    int boxWidth = 300;
    int boxHeight = 400;
    int elementWidth = boxWidth - (Design.padding * 2);
    Color darkTextColour = Design.grey20;

    //empty constructor
    public LogInPage()
    {
        this(false); //default to 'log in' mode
    }

    //MARK: Constructor
    public LogInPage(boolean registrationMode)
    {
        this.registrationMode = registrationMode;

        //background
        Rectangle background = new Rectangle();
        background.setFill(Color.GREEN);
        background.widthProperty().bind(this.widthProperty());
        background.heightProperty().bind(this.heightProperty());

        //logo
        ImageView logo = new ImageView(Design.getLogo());
        logo.setFitWidth(400);
        logo.setPreserveRatio(true);

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
        registerButton.setOnMouseClicked(event -> openRegisterPage());
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

        //the label at the very bottom of the screen
        Label footingLabel = new Label("Access millions of Australian native plants and make changes to pages with a free account.");
        footingLabel.setTextFill(Design.grey50);
        // footingLabel.setWrapText(true);
        footingLabel.maxWidth(boxWidth);
        footingLabel.setWrapText(true);
        // footingLabel.setAlignment(Pos.CENTER);
        footingLabel.setTextAlignment(TextAlignment.CENTER);
        
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
            // buttonsBox2
            registerButton
        );
        content.setSpacing(Design.spacing);
        content.setPadding(new Insets(Design.padding));
        content.setAlignment(Pos.CENTER);
        // content.setFillWidth(false); //set the fillWidth property to false so the children are not resized to fill the width of the VBox
        content.setMaxWidth(boxWidth);
        content.setMaxHeight(boxHeight);
        // VBox.setMargin(logInButton, new Insets(20, 0, 0, 0)); //give this element some extra padding

        //add elements to StackPane
        StackPane centrePanel = new StackPane(
            frame,
            content
        );

        Region topSpacer = new Region();
        VBox.setVgrow(topSpacer, Priority.ALWAYS);
        topSpacer.setMaxHeight(180);
        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        VBox middleColumn = new VBox(
            topSpacer,
            logo,
            centrePanel,
            guestButton,
            bottomSpacer,
            footingLabel
        );
        middleColumn.setAlignment(Pos.CENTER);
        // middleColumn.setSpacing(Design.spacing);
        middleColumn.setPadding(new Insets(Design.padding));
        VBox.setMargin(logo, new Insets(0, 0, 50, 0)); //bottom padding
        
        //leaves
        int imageHeight = 600;
        // int imageWidth = 400;
        Image leftLeafImage = Design.getImage("Leaf Right.png");
        Image rightLeafImage = Design.getImage("Leaf Left.png");
        // Image leafImage = Design.getImage("Leaf.png");

        ImageView leftLeaf = new ImageView(leftLeafImage);
        leftLeaf.setPreserveRatio(true);
        leftLeaf.setFitHeight(imageHeight);
        // leftLeaf.fitHeightProperty().bind(this.heightProperty());

        ImageView rightLeaf = new ImageView(rightLeafImage);
        rightLeaf.setPreserveRatio(true);
        rightLeaf.setFitHeight(imageHeight);
        // rightLeaf.fitHeightProperty().bind(this.heightProperty());

        ColorInput colour = new ColorInput(
            0, 0,
            // leftLeafImage.getWidth(),
            // leftLeafImage.getHeight(),
            imageHeight / 2,
            imageHeight,
            Design.superLightGrey
        );
        Blend blend = new Blend(
            BlendMode.SRC_ATOP,
            null,
            colour
        );
        leftLeaf.setEffect(blend);
        rightLeaf.setEffect(blend);

        Region leftSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox hBox = new HBox(
            leftLeaf,
            leftSpacer,
            middleColumn,
            rightSpacer,
            rightLeaf
        );
        hBox.setAlignment(Pos.CENTER);
        // leftLeaf.setTranslateX(-50);
        // rightLeaf.setTranslateX(50);
        
        getChildren().addAll(
            // background,
            hBox
        );

        //set background colour
        setStyle("-fx-background-color: #FFFFFF;");

        updateMode();
    }

    //MARK: Methods
    void openEntryPage()
    {
        HelloApplication.scene.setRoot(new EntryPage());
    }

    void openRegisterPage()
    {
        registrationMode = !registrationMode; //toggle boolean
        statusLabel.setText(""); //reset text
        updateMode();
    }

    void submitCredentials()
    {
        String username = usernameTextfield.getText().trim();
        String password = passwordTextfield.getText();
        authManager manager = new authManager(username, password);

        if (registrationMode)
        {
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
            statusLabel.setText("Username or password is incorrect.");
        }
    }

    void forgotPassword()
    {
        //not implemented yet
        System.out.println("User forgot password not implemented yet");
    }

    //updates Nodes to correctly represent the current mode
    private void updateMode()
    {
        titleLabel.setText(registrationMode ? "Register" : "Log In");
        logInButton.setText(registrationMode ? "Create account" : "Log in");
        bottomLabel.setText(registrationMode ? "Already have an account?" : "Don't have an account?");
        registerButton.setText(registrationMode ? "Log in" : "Register");
        if (forgotPasswordButton != null)
        {
            forgotPasswordButton.setVisible(!registrationMode); //show or hide
            forgotPasswordButton.setManaged(!registrationMode); //when false, tells the parent layout container to ignore the button during its automatic layout and sizing calculations
        }
    }
}
