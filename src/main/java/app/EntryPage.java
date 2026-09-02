package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//the entry page when the user first opens the app
public class EntryPage extends BorderPane
{
    public EntryPage()
    {
        //middle
        Label titleLabel = new Label("Plantopedia");
        Label subtitleLabel = new Label("A digital encyclopedia for Australian native plants");
        TextField searchTextfield = new TextField("Search...");
        searchTextfield.setPrefWidth(400);
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> openSearchPage());

        //buttons
        Button logInButton = new Button("Log in");
        logInButton.setOnAction(event -> openLogInPage());
        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> openRegisterPage());

        VBox centreBox = new VBox();
        centreBox.setAlignment(Pos.CENTER);
        centreBox.setSpacing(15.0);
        centreBox.setFillWidth(false); //set the fillWidth property to false so the children are not resized to fill the width of the VBox
        centreBox.getChildren().addAll(
            titleLabel,
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
    }

    void openSearchPage()
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