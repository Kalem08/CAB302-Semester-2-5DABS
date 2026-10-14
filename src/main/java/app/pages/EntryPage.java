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
// import app.components.ImageButton;
import app.components.AccountButton;
import User.authManager;

//the entry page when the user first opens the app
public class EntryPage extends VBox
{
    CustomTextfield searchTextfield;
    CustomButton logInButton;
    CustomButton registerButton;
    // Label signedInLabel;
    // ImageButton accountButton;
    AccountButton accountButton;

    /**
     * Creates an entry page which is visible when the app is first opened.
     */
    public EntryPage()
    {
        Region horizontalSpacer = new Region();
        HBox.setHgrow(horizontalSpacer, Priority.ALWAYS);

        //account buttons
        logInButton = new CustomButton("Log in", 100, 40);
        logInButton.setBackgroundColor(Design.grey90);
        logInButton.setBackgroundHoverColor(Color.LIGHTGREY);
        logInButton.setOnMouseClicked(event -> Navigation.openLogInPage());

        registerButton = new CustomButton("Register", 100, 40);
        registerButton.setBackgroundColor(Design.grey90);
        registerButton.setBackgroundHoverColor(Color.LIGHTGREY);
        registerButton.setOnMouseClicked(event -> Navigation.openRegisterPage());

        //signed in label
        // signedInLabel = new Label("Signed in as '" + authManager.getUsername() + "'");

        //accountButton
        // int diameter = 40;
        // accountButton = new ImageButton("Leaf.png", diameter / 2, diameter, diameter);
        // accountButton.background.setArcWidth(diameter);
        // accountButton.background.setArcHeight(diameter);
        // accountButton.setBackgroundColor(Design.mediumGreen);
        // accountButton.setBackgroundHoverColor(Design.darkGreen);
        // accountButton.setOnMouseClicked(event -> Navigation.openAccountPage());

        accountButton = new AccountButton(Design.grey90, Design.grey80);
        // accountButton.setOnMouseClicked(event -> Navigation.openAccountPage());

        HBox topBar = new HBox(
            horizontalSpacer,
            logInButton,
            registerButton,
            // signedInLabel,
            accountButton
        );
        topBar.setSpacing(Design.spacing);
        topBar.setPadding(new Insets(Design.padding));
        topBar.setAlignment(Pos.CENTER);

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

        // HBox lowerBox = new HBox();
        // lowerBox.setAlignment(Pos.CENTER);
        // lowerBox.setSpacing(20.0);
        // lowerBox.setPadding(new Insets(0, 0, 20, 0)); //20 pixels of spacing from the bottom
        // // lowerBox.setFillHeight(false);
        // lowerBox.getChildren().addAll(
        //     signedInLabel,
        //     logInButton,
        //     registerButton
        // );

        Region bottomSpacer = new Region();
        bottomSpacer.setMaxHeight(50);
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        //add Nodes to root container
        getChildren().addAll(
            topBar,
            centreBox,
            bottomSpacer
            // lowerBox
        );

        //change colour of root
        setStyle("-fx-background-color: #FFFFFF;");
        // lowerBox.setAlignment(Pos.CENTER);

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