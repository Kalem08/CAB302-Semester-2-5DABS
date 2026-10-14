package app.components;

//MARK: Imports
// import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
// import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import app.Design;
import app.Navigation;
import User.authManager;

//MARK: Class
//the header with logo, search bar and account button at the top of most pages
public class Header extends StackPane
{
    int searchElementHeight = 30;
    int buttonWidth = 100;

    CustomButton logInButton;
    CustomButton registerButton;
    CustomTextfield searchTextfield;
    // Label usernameLabel;
    // ImageButton accountButton;
    AccountButton accountButton;

    //MARK: Constructor
    /**
     * Creates a new Header element to sit at the top of each page.
     */
    public Header()
    {
        Rectangle background = new Rectangle();
        background.setFill(Design.grey90);
        background.widthProperty().bind(this.widthProperty()); //assume the width and height of the header
        background.heightProperty().bind(this.heightProperty());

        //logo
        int logoWidth = 200;
        ImageButton logo = new ImageButton("Logo.png", logoWidth, logoWidth + 20, 50);
        logo.setBackgroundColor(Design.white);
        logo.setBackgroundHoverColor(Design.grey70);
        logo.setOnMouseClicked(event -> Navigation.openEntryPage());
        
        //textfield
        searchTextfield = new CustomTextfield("Search...", 400, searchElementHeight);
        searchTextfield.setBackgroundColor(Design.white);
        searchTextfield.textfield.setOnAction(event -> search());

        //search button
        CustomButton searchButton = new CustomButton("Search", 60, searchElementHeight);
        searchButton.setTextSize(12);
        searchButton.setBackgroundColor(Design.white);
        searchButton.setBackgroundHoverColor(Design.grey70);
        searchButton.setTextColour(Design.grey20);
        searchButton.setTextHoverColour(Design.black);
        searchButton.setOnMouseClicked(event -> search());

        //login/register
        logInButton = new CustomButton("Log In", buttonWidth, logoWidth);
        logInButton.setBackgroundColor(Design.white);
        logInButton.setBackgroundHoverColor(Design.grey70);
        logInButton.setOnMouseClicked(event -> Navigation.openLogInPage());

        registerButton = new CustomButton("Register", buttonWidth, logoWidth);
        registerButton.setBackgroundColor(Design.white);
        registerButton.setBackgroundHoverColor(Design.grey70);
        registerButton.setOnMouseClicked(event -> Navigation.openRegisterPage());

        //account button
        // int diameter = 40;
        // accountButton = new ImageButton("Leaf.png", diameter / 2, diameter, diameter);
        // accountButton.background.setArcWidth(diameter);
        // accountButton.background.setArcHeight(diameter);
        // accountButton.setBackgroundColor(Design.mediumGreen);
        // accountButton.setBackgroundHoverColor(Design.darkGreen);
        // accountButton.setOnMouseClicked(event -> Navigation.openAccountPage());

        accountButton = new AccountButton(Design.white, Design.grey80);
        // accountButton.setOnMouseClicked(event -> Navigation.openAccountPage());

        // usernameLabel = new Label(authManager.getUsername());

        //create spacers
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox content = new HBox();
        content.getChildren().addAll(
            logo,
            leftSpacer,
            searchTextfield,
            searchButton,
            rightSpacer,
            logInButton,
            registerButton,
            // usernameLabel,
            accountButton
        );
        content.setAlignment(Pos.CENTER);
        content.setSpacing(Design.spacing);
        content.setPadding(new Insets(Design.padding));
        content.setFillHeight(false); //sub-elements should not stretch to fill the container
        
        //add everything to the header stack
        getChildren().addAll(
            background,
            content
        );
        // setPrefHeight(60);
        setMinHeight(60);
        setMinWidth(0); //need to set min width so header can shrink when window shrinks

        updateAccountButtonsVisibility();
    }

    //MARK: Methods
    void search()
    {
        Navigation.openResultsPage(searchTextfield.getText());
    }

    //toggles visibility of buttons
    private void updateAccountButtonsVisibility()
    {
        Boolean isSignedIn = authManager.isSignedIn();

        //when logged out
        logInButton.setVisible(!isSignedIn);
        logInButton.setManaged(!isSignedIn);
        registerButton.setVisible(!isSignedIn);
        registerButton.setManaged(!isSignedIn);

        //when signed in
        accountButton.setVisible(isSignedIn);
        accountButton.setManaged(isSignedIn);
    }
}