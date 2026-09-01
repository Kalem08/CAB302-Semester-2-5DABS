//the entry page when the user first opens the app

package app;

//MARK: Imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EntryScene
{
    //MARK: Variables
    private Label titleLabel;
    private Label subtitleLabel;
    private TextField searchTextfield;
    private Button searchButton;
    private Button loginButton;
    private Button registerButton;

    // @Override
    public void create(Stage stage) // throws IOException
    {
        //middle
        titleLabel = new Label("Plantopedia");
        subtitleLabel = new Label("A digital encyclopedia for Australian native plants");
        searchTextfield = new TextField();
        searchTextfield.setText("Search...");
        searchButton = new Button("Search");
        searchButton.setOnAction(event -> openSearchPage());

        //buttons
        loginButton = new Button("Login");
        loginButton.setOnAction(event -> openLoginPage());
        registerButton = new Button("Register");
        registerButton.setOnAction(event -> openRegisterPage());

        VBox centreBox = new VBox();
        centreBox.setAlignment(Pos.CENTER);
        centreBox.setSpacing(15.0);
        centreBox.setFillWidth(false); //set the fillWidth property to false so the children are not resized to fill the width of the VBox
        centreBox.getChildren().addAll(titleLabel, subtitleLabel, searchTextfield, searchButton);

        HBox lowerBox = new HBox();
        lowerBox.setAlignment(Pos.CENTER);
        lowerBox.setSpacing(20.0);
        lowerBox.setPadding(new Insets(0, 0, 20, 0)); //20 pixels of spacing from the bottom
        // lowerBox.setFillHeight(false);
        lowerBox.getChildren().addAll(loginButton, registerButton);
        
        BorderPane root = new BorderPane();
        root.setCenter(centreBox);
        root.setBottom(lowerBox);

        // centreBox.setAlignment(Pos.CENTER);
        // centreBox.setSpacing(10);

        // lowerBox.setAlignment(Pos.CENTER);
        // lowerBox.setSpacing(10);

        //MARK: Scene
        //define the scene, add to the stage (window) and show the stage
        Scene scene = new Scene(root, 960, 540);
        stage.setTitle("Entry Page"); //the window title
        stage.setScene(scene); //sets the scene
        stage.show();
    }

    void openSearchPage()
    {
        System.out.println("Open search page.");
    }

    void openLoginPage()
    {
        System.out.println("Open login page.");
    }

    void openRegisterPage()
    {
        System.out.println("Open register page.");
    }
}