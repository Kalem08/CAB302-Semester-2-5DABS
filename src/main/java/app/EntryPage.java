package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.image.*;
import java.net.URL;

//the entry page when the user first opens the app
public class EntryPage extends BorderPane
{
    public EntryPage()
    {
        //middle
        Label subtitleLabel = new Label("A digital encyclopedia for Australian native plants");
        CustomTextfield searchTextfield = new CustomTextfield("Search...", 800, 50);
        searchTextfield.setBackgroundColor(Design.superLightGrey);

        //search button
        CustomButton searchButton = new CustomButton("Search", 100, 40);
        searchButton.setBackgroundColor(Design.superLightGrey);
        searchButton.setBackgroundHoverColor(Color.LIGHTGREY);
        searchButton.setTextColour(Design.grey20);
        searchButton.setTextHoverColour(Color.BLACK);
        searchButton.setOnMouseClicked(event -> openResultsPage());

        //account buttons
        CustomButton logInButton = new CustomButton("Log in", 100, 40);
        logInButton.setOnMouseClicked(event -> openLogInPage());
        CustomButton registerButton = new CustomButton("Register", 100, 40);
        registerButton.setOnMouseClicked(event -> openLogInPage());

        //MARK: Logo
        ImageView imageView = new ImageView(Design.getLogo());
        imageView.setFitWidth(800);
        imageView.setPreserveRatio(true);

        VBox centreBox = new VBox();
        centreBox.setAlignment(Pos.CENTER);
        centreBox.setSpacing(15.0);
        centreBox.setFillWidth(false); //set the fillWidth property to false so the children are not resized to fill the width of the VBox
        centreBox.getChildren().addAll(
            imageView,
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

        // URL resource = Design.class.getResource("/Logo.png");
        // System.out.println("Image: ");
        // System.out.println(resource);
        // Image img = new Image(resource.toExternalForm());
    }

    void openResultsPage()
    {
        HelloApplication.scene.setRoot(new ResultsPage());
    }

    void openLogInPage()
    {
        HelloApplication.scene.setRoot(new LogInPage());
    }

    void openRegisterPage()
    {
        HelloApplication.scene.setRoot(new LogInPage());
    }
}