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
import app.components.ImageButton;
import User.authManager;

//the entry page when the user first opens the app
public class EntryPage extends VBox
{
    Label signedInLabel;
    ImageButton accountButton;
    CustomButton logInButton;
    CustomButton registerButton;
    CustomTextfield searchTextfield;

    /**
     * Creates an entry page which is visible when the app is first opened.
     */
    public EntryPage()
    {
        //accountButton
        int diameter = 40;
        accountButton = new ImageButton("Leaf.png", diameter / 2, diameter, diameter);
        accountButton.background.setArcWidth(diameter);
        accountButton.background.setArcHeight(diameter);
        accountButton.setBackgroundColor(Design.mediumGreen);
        accountButton.setBackgroundHoverColor(Design.darkGreen);
        accountButton.setOnMouseClicked(event -> Navigation.openAccountPage());

        Region horizontalSpacer = new Region();
        HBox.setHgrow(horizontalSpacer, Priority.ALWAYS);

        HBox topBar = new HBox(
            horizontalSpacer,
            accountButton
        );
        topBar.setPadding(new Insets(Design.padding));

        //MARK: Logo
        ImageView logo = new ImageView(Design.getLogo());
        logo.setFitWidth(800);
        logo.setPreserveRatio(true);

        Label subtitleLabel = new Label("A digital encyclopedia for Australian native plants");

        searchTextfield = new CustomTextfield("Search...", 800, 50);
        searchTextfield.setBackgroundColor(Design.superLightGrey);
        searchTextfield.textfield.setOnAction(event -> search());

        //search button
        CustomButton searchButton = new CustomButton("Search", 100, 40);
        searchButton.setBackgroundColor(Design.superLightGrey);
        searchButton.setBackgroundHoverColor(Color.LIGHTGREY);
        searchButton.setTextColour(Design.grey20);
        searchButton.setTextHoverColour(Color.BLACK);
        searchButton.setOnMouseClicked(event -> search());

        //account buttons
        logInButton = new CustomButton("Log in", 100, 40);
        logInButton.setBackgroundColor(Design.superLightGrey);
        logInButton.setBackgroundHoverColor(Color.LIGHTGREY);
        logInButton.setOnMouseClicked(event -> Navigation.openLogInPage());

        registerButton = new CustomButton("Register", 100, 40);
        registerButton.setBackgroundColor(Design.superLightGrey);
        registerButton.setBackgroundHoverColor(Color.LIGHTGREY);
        registerButton.setOnMouseClicked(event -> Navigation.openRegisterPage());

        //signed in label
        signedInLabel = new Label("Signed in as '" + authManager.getUsername() + "'");

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
        // VBox.setV

        HBox lowerBox = new HBox();
        lowerBox.setAlignment(Pos.CENTER);
        lowerBox.setSpacing(20.0);
        lowerBox.setPadding(new Insets(0, 0, 20, 0)); //20 pixels of spacing from the bottom
        // lowerBox.setFillHeight(false);
        lowerBox.getChildren().addAll(
            signedInLabel,
            logInButton,
            registerButton
        );

        Region bottomSpacer = new Region();
        bottomSpacer.setMaxHeight(50);
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        //add Nodes to root container
        getChildren().addAll(
            topBar,
            centreBox,
            bottomSpacer,
            lowerBox
        );

        //change colour of root
        setStyle("-fx-background-color: #FFFFFF;");

        centreBox.setAlignment(Pos.CENTER);
        lowerBox.setAlignment(Pos.CENTER);

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
        signedInLabel.setVisible(isSignedIn);
        signedInLabel.setManaged(isSignedIn);
        accountButton.setVisible(isSignedIn);

        //when logged out
        logInButton.setVisible(!isSignedIn);
        logInButton.setManaged(!isSignedIn);
        registerButton.setVisible(!isSignedIn);
        registerButton.setManaged(!isSignedIn);
    }
}