package app.pages;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.image.*;

import app.Design;
import app.Navigation;
import app.components.CustomButton;
import app.components.CustomTextfield;
import app.components.AccountButton;
import app.components.AuthButton;
import User.authManager;

/**
 * The page present when the user first opens the app.
 */
public class EntryPage extends VBox
{
    CustomTextfield searchTextfield;
    AuthButton logInButton;
    AuthButton registerButton;
    AccountButton accountButton;

    /**
     * Creates an entry page which is visible when the app is first opened.
     */
    public EntryPage()
    {
        Region horizontalSpacer = new Region();
        HBox.setHgrow(horizontalSpacer, Priority.ALWAYS);

        //account buttons
        logInButton = new AuthButton("Log in", Design.defaultButtonWidth, Design.defaultButtonHeight, Design.grey90, Design.grey80, false);
        registerButton = new AuthButton("Register", Design.defaultButtonWidth, Design.defaultButtonHeight, Design.grey90, Design.grey80, true);

        //accountButton
        accountButton = new AccountButton(Design.grey90, Design.grey80);

        HBox topBar = new HBox(
            horizontalSpacer,
            logInButton,
            registerButton,
            accountButton
        );
        topBar.setSpacing(Design.spacing);
        topBar.setPadding(new Insets(Design.padding));
        topBar.setAlignment(Pos.CENTER);
        topBar.setPrefHeight(Design.headerHeight);

        //MARK: Logo
        ImageView logo = new ImageView(Design.getLogo());
        logo.setFitWidth(800);
        logo.setPreserveRatio(true);

        Label subtitleLabel = new Label("A digital encyclopedia for Australian native plants");

        searchTextfield = new CustomTextfield("Search...", 800, 50);
        searchTextfield.setBackgroundColor(Design.grey90);
        searchTextfield.textfield.setOnAction(event -> search());

        //search button
        CustomButton searchButton = new CustomButton("Search", 100, 40);
        searchButton.setBackgroundColor(Design.grey90);
        searchButton.setBackgroundHoverColor(Color.LIGHTGREY);
        searchButton.setTextColour(Design.grey20);
        searchButton.setTextHoverColour(Color.BLACK);
        searchButton.setOnMouseClicked(event -> search());

        VBox centreBox = new VBox();
        centreBox.setAlignment(Pos.CENTER);
        centreBox.setSpacing(15.0);
        centreBox.setFillWidth(false); //set the fillWidth property to false so the children are not resized to fill the width of the VBox
        centreBox.getChildren().addAll(
            logo,
            subtitleLabel,
            searchTextfield,
            searchButton
        );
        VBox.setVgrow(centreBox, Priority.ALWAYS);
        centreBox.setAlignment(Pos.CENTER);

        Region bottomSpacer = new Region();
        bottomSpacer.setMaxHeight(50);
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        //add Nodes to root container
        getChildren().addAll(
            topBar,
            centreBox,
            bottomSpacer
        );

        //change colour of root
        setStyle("-fx-background-color: #FFFFFF;");

        updateAccountButtonsVisibility();
    }

    //MARK: Search
    /**
     * Uses the value in the search textfield to load the results page.
     */
    void search()
    {
        Navigation.openResultsPage(searchTextfield.getText());
    }

     //MARK: Toggle button
    /**
     * Toggles visibility of buttons dependeing on if the user is signed in or not.
     */
    private void updateAccountButtonsVisibility()
    {
        Boolean isSignedIn = authManager.isSignedIn();

        //when logged in
        // signedInLabel.setVisible(isSignedIn);
        // signedInLabel.setManaged(isSignedIn);
        accountButton.setVisible(isSignedIn);
        accountButton.setManaged(isSignedIn);

        //when logged out
        logInButton.setVisible(!isSignedIn);
        logInButton.setManaged(!isSignedIn);
        registerButton.setVisible(!isSignedIn);
        registerButton.setManaged(!isSignedIn);
    }
}