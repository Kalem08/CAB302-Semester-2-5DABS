package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//MARK: Class
//the header with logo, search bar and account button at the top of most pages
public class Header extends StackPane
{
    public Header()
    {
        //MARK: Background
        Rectangle background = new Rectangle();
        background.setFill(Color.LIGHTGRAY);
        background.widthProperty().bind(this.widthProperty()); //assume the width and height of the header
        background.heightProperty().bind(this.heightProperty());
        
        //MARK: Elements
        Label titleLabel = new Label("Plantopedia");
        TextField searchTextfield = new TextField("Search...");

        Button accountButton = new Button("Account");
        accountButton.setOnAction(event -> openAccountPage());
        Button logInButton = new Button("Log in");
        logInButton.setOnAction(event -> openLoginPage());
        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> openLoginPage());

        searchTextfield.setPrefWidth(400);
        searchTextfield.setMinWidth(0);

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> search());

        //create spacers
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox content = new HBox();
        content.getChildren().addAll(
            titleLabel,
            leftSpacer,
            searchTextfield,
            searchButton,
            rightSpacer,
            logInButton,
            registerButton,
            accountButton
        );
        content.setAlignment(Pos.CENTER);
        content.setSpacing(10.0);
        content.setPadding(new Insets(10));
        // content.setMinWidth(0);
        // content.prefWidthProperty().bind(this.widthProperty());
        
        //add everything to the header stack
        getChildren().addAll(background, content);
        setPrefHeight(50);
        setMinWidth(0); //need to set min width so header can shrink when window shrinks
    }

    //MARK: Methods
    void search()
    {
        HelloApplication.scene.setRoot(new SearchPage());
    }

    //MARK: Methods
    void openAccountPage()
    {
        HelloApplication.scene.setRoot(new AccountPage());
    }

    void openLoginPage()
    {
        HelloApplication.scene.setRoot(new LogInPage());
    }
}