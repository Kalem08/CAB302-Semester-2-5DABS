package app.components;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import User.authManager;
import app.Design;
import app.HelloApplication;
import app.AuthenticationPanel.AuthMode;
import app.pages.AccountPage;
import app.pages.EntryPage;
import app.pages.LogInPage;
import app.pages.ResultsPage;

//MARK: Class
//the header with logo, search bar and account button at the top of most pages
public class Header extends StackPane
{
    int searchElementHeight = 30;
    int buttonWidth = 100;

    CustomButton logInButton;
    CustomButton registerButton;
    Label usernameLabel;
    ImageButton accountButton;

    //MARK: Constructor
    /**
     * Creates a new Header element to sit at the top of each page.
     */
    public Header()
    {
        Rectangle background = new Rectangle();
        background.setFill(Color.LIGHTGRAY);
        background.widthProperty().bind(this.widthProperty()); //assume the width and height of the header
        background.heightProperty().bind(this.heightProperty());

        //logo
        int logoWidth = 200;
        ImageButton logo = new ImageButton("Logo.png", logoWidth, logoWidth + 20, 50);
        logo.setBackgroundColor(Color.WHITE);
        logo.setBackgroundHoverColor(Design.grey70);
        logo.setOnMouseClicked(event -> openEntryPage());
        
        //textfield
        CustomTextfield searchTextfield = new CustomTextfield("Search...", 400, searchElementHeight);
        searchTextfield.setBackgroundColor(Design.superLightGrey);
        searchTextfield.textfield.setOnAction(event -> search(searchTextfield.getText()));

        //search button
        CustomButton searchButton = new CustomButton("Search", 60, searchElementHeight);
        searchButton.setTextSize(12);
        searchButton.setBackgroundColor(Color.WHITESMOKE);
        searchButton.setBackgroundHoverColor(Design.grey70);
        searchButton.setTextColour(Design.grey20);
        searchButton.setTextHoverColour(Color.BLACK);
        searchButton.setOnMouseClicked(event -> search(searchTextfield.getText()));

        //login/register
        logInButton = new CustomButton("Log In", buttonWidth, logoWidth);
        logInButton.setBackgroundColor(Color.WHITE);
        logInButton.setBackgroundHoverColor(Design.grey70);
        logInButton.setOnMouseClicked(event -> openLoginPage());

        registerButton = new CustomButton("Register", buttonWidth, logoWidth);
        registerButton.setBackgroundColor(Color.WHITE);
        registerButton.setBackgroundHoverColor(Design.grey70);
        registerButton.setOnMouseClicked(event -> openRegisterPage());

        //account button
        int diameter = 40;
        accountButton = new ImageButton("Leaf.png", diameter / 2, diameter, diameter);
        accountButton.background.setArcWidth(diameter);
        accountButton.background.setArcHeight(diameter);
        accountButton.setBackgroundColor(Design.mediumGreen);
        accountButton.setBackgroundHoverColor(Design.darkGreen);
        accountButton.setOnMouseClicked(event -> openAccountPage());

        usernameLabel = new Label(authManager.getUsername());

        //create spacers
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox content = new HBox();
        content.getChildren().addAll(
            // titleLabel,
            logo,
            leftSpacer,
            searchTextfield,
            searchButton,
            rightSpacer,
            logInButton,
            registerButton,
            usernameLabel,
            accountButton
        );
        content.setAlignment(Pos.CENTER);
        content.setSpacing(Design.spacing);
        content.setPadding(new Insets(Design.padding));
        content.setFillHeight(false); //sub-elements should not stretch to fill the container
        
        //add everything to the header stack
        getChildren().addAll(background, content);
        setPrefHeight(60); //previously 50
        setMinHeight(60);
        setMinWidth(0); //need to set min width so header can shrink when window shrinks

        updateAccountButtonsVisibility();
    }

    //MARK: Methods
    void search(String query)
    {
        HelloApplication.setPage(new ResultsPage(query));
    }

    void openEntryPage()
    {
        HelloApplication.setPage(new EntryPage());
    }

    void openAccountPage()
    {
        HelloApplication.setPage(new AccountPage());
    }

    void openLoginPage()
    {
        HelloApplication.setPage(new LogInPage(AuthMode.Login));
    }

    void openRegisterPage()
    {
        HelloApplication.setPage(new LogInPage(AuthMode.Register));
    }

    //toggles visibility of buttons
    private void updateAccountButtonsVisibility()
    {
        Boolean isSignedIn = authManager.isSignedIn();
        logInButton.setVisible(!isSignedIn);
        logInButton.setManaged(!isSignedIn);
        registerButton.setVisible(!isSignedIn);
        registerButton.setManaged(!isSignedIn);
        accountButton.setVisible(isSignedIn);
        accountButton.setManaged(isSignedIn);
    }
}