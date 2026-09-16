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
import app.pages.AccountPage;
import app.pages.EntryPage;
import app.pages.LogInPage;
import app.pages.ResultsPage;

//MARK: Class
//the header with logo, search bar and account button at the top of most pages
public class Header extends StackPane
{
    int searchElementHeight = 30;

    Button logInButton;
    Button registerButton;
    ImageButton accountIcon;

    public Header()
    {
        //MARK: Background
        Rectangle background = new Rectangle();
        background.setFill(Color.LIGHTGRAY);
        background.widthProperty().bind(this.widthProperty()); //assume the width and height of the header
        background.heightProperty().bind(this.heightProperty());
        
        //MARK: Elements

        //logo
        int logoWidth = 200;
        // Rectangle logoRectangle = new Rectangle(logoWidth + 20, 50, Color.WHITE);
        // logoRectangle.setArcWidth(Design.cornerRadius);
        // logoRectangle.setArcHeight(Design.cornerRadius);

        // ImageView imageView = new ImageView(Design.getLogo());
        // imageView.setFitWidth(logoWidth);
        // imageView.setPreserveRatio(true);

        // StackPane logo = new StackPane(logoRectangle, imageView);
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
        logInButton = new Button("Log in");
        logInButton.setOnAction(event -> openLoginPage());

        registerButton = new Button("Register");
        registerButton.setOnAction(event -> openLoginPage());

        //account button
        accountIcon = new ImageButton("Leaf.png", 20, 40, 40);
        accountIcon.background.setArcWidth(40);
        accountIcon.background.setArcHeight(40);
        accountIcon.setBackgroundColor(Design.mediumGreen);
        accountIcon.setBackgroundHoverColor(Design.darkGreen);
        accountIcon.setOnMouseClicked(event -> openAccountPage());

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
            accountIcon
        );
        content.setAlignment(Pos.CENTER);
        content.setSpacing(Design.spacing);
        content.setPadding(new Insets(Design.padding));
        content.setFillHeight(false); //sub-elements should not stretch to fill the container
        // content.setMinWidth(0);
        // content.prefWidthProperty().bind(this.widthProperty());
        
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
        HelloApplication.scene.setRoot(new ResultsPage(query));
    }

    void openEntryPage()
    {
        HelloApplication.scene.setRoot(new EntryPage());
    }

    void openAccountPage()
    {
        HelloApplication.scene.setRoot(new AccountPage());
    }

    void openLoginPage()
    {
        HelloApplication.scene.setRoot(new LogInPage());
    }

    //toggles visibility of buttons
    private void updateAccountButtonsVisibility()
    {
        Boolean isSignedIn = authManager.isSignedIn();
        logInButton.setVisible(!isSignedIn);
        logInButton.setManaged(!isSignedIn);
        registerButton.setVisible(!isSignedIn);
        registerButton.setManaged(!isSignedIn);
        accountIcon.setVisible(isSignedIn);
        accountIcon.setManaged(isSignedIn);
    }
}