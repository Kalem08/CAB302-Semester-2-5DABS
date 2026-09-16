package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.image.*;

import User.authManager;
import app.AuthenticationPanel.AuthMode;

//the entry page when the user first opens the app
public class EntryPage extends BorderPane
{
    Label signedInLabel;
    CustomButton logInButton;
    CustomButton registerButton;
    CustomTextfield searchTextfield;

    public EntryPage()
    {
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
        logInButton.setOnMouseClicked(event -> openLogInPage());

        registerButton = new CustomButton("Register", 100, 40);
        registerButton.setBackgroundColor(Design.superLightGrey);
        registerButton.setBackgroundHoverColor(Color.LIGHTGREY);
        registerButton.setOnMouseClicked(event -> openRegisterPage());

        //signed in label
        signedInLabel = new Label("Signed in");

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

        //modify the root container (BorderPane)
        setCenter(centreBox);
        setBottom(lowerBox);
        //change colour of root GridPane
        setStyle("-fx-background-color: #FFFFFF;");

        centreBox.setAlignment(Pos.CENTER);
        lowerBox.setAlignment(Pos.CENTER);

        updateAccountButtonsVisibility();
    }

    //MARK: Methods
    void search()
    {
        openResultsPage(searchTextfield.getText());
    }

    void openResultsPage(String query)
    {
        HelloApplication.scene.setRoot(new ResultsPage(query));
    }

    void openLogInPage()
    {
        HelloApplication.scene.setRoot(new LogInPage(AuthMode.Login));
    }

    void openRegisterPage()
    {
        HelloApplication.scene.setRoot(new LogInPage(AuthMode.Register));
    }

    //toggles visibility of buttons
    private void updateAccountButtonsVisibility()
    {
        Boolean isSignedIn = authManager.isSignedIn();
        signedInLabel.setVisible(isSignedIn);
        signedInLabel.setManaged(isSignedIn);
        logInButton.setVisible(!isSignedIn);
        logInButton.setManaged(!isSignedIn);
        registerButton.setVisible(!isSignedIn);
        registerButton.setManaged(!isSignedIn);
    }
}