package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.*;

//the entry page when the user first opens the app
public class EntryPage extends BorderPane
{
    public EntryPage()
    {
        //middle
        Label subtitleLabel = new Label("A digital encyclopedia for Australian native plants");
        CustomTextfield searchTextfield = new CustomTextfield("Search...", 800, 50);
        // searchTextfield.setPromptText("Search...");
        searchTextfield.setPrefWidth(400);

        //search button
        // Button searchButton = new Button("Search");
        CustomButton searchButton = new CustomButton("Search");
        searchButton.setBackgroundColor(Color.WHITESMOKE);
        searchButton.setBackgroundHoverColor(Color.LIGHTGREY);
        searchButton.setTextColour(Design.grey20);
        searchButton.setTextHoverColour(Color.BLACK);
        searchButton.setOnMouseClicked(event -> openResultsPage());

        //account buttons
        CustomButton logInButton = new CustomButton("Log in");
        logInButton.setOnMouseClicked(event -> openLogInPage());
        CustomButton registerButton = new CustomButton("Register");
        registerButton.setOnMouseClicked(event -> openLogInPage());

        //logo
        Image image = new Image(getClass().getResource("/Logo.png").toExternalForm()); //relative to "/resources/"
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800);
        imageView.setPreserveRatio(true);

        // CustomTextfield customTextfield = new CustomTextfield();

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
        
        // Header header = new Header();

        //modify the root container (BorderPane)
        // setTop(header);
        setCenter(centreBox);
        setBottom(lowerBox);

        centreBox.setAlignment(Pos.CENTER);
        // centreBox.setSpacing(10);
        lowerBox.setAlignment(Pos.CENTER);
        // lowerBox.setSpacing(10);

        //for root GridPane
        setStyle("-fx-background-color: #FFFFFF;");
    }

    void openResultsPage()
    {
        HelloApplication.scene.setRoot(new SearchPage());
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