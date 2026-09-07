package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.*;

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
        //title
        // Label titleLabel = new Label("Plantopedia");

        //logo
        int logoWidth = 200;
        Rectangle logoRectangle = new Rectangle(logoWidth + 20, 50, Color.WHITE);
        logoRectangle.setArcWidth(Design.cornerRadius);
        logoRectangle.setArcHeight(Design.cornerRadius);

        ImageView imageView = new ImageView(Design.getLogo());
        imageView.setFitWidth(logoWidth);
        imageView.setPreserveRatio(true);

        StackPane logo = new StackPane(logoRectangle, imageView);
        logo.setOnMouseClicked(event -> openEntryPage());

        CustomTextfield searchTextfield = new CustomTextfield("Search...", 400, 30);
        searchTextfield.setBackgroundColor(Design.superLightGrey);

        // Button searchButton = new Button("Search");
        // searchButton.setOnAction(event -> search());
        CustomButton searchButton = new CustomButton("Search", 60, 30);
        searchButton.setTextSize(12);
        searchButton.setBackgroundColor(Color.WHITESMOKE);
        searchButton.setBackgroundHoverColor(Color.LIGHTGREY);
        searchButton.setTextColour(Design.grey20);
        searchButton.setTextHoverColour(Color.BLACK);
        searchButton.setOnMouseClicked(event -> search());

        Button logInButton = new Button("Log in");
        logInButton.setOnAction(event -> openLoginPage());
        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> openLoginPage());

        AccountButton accountIcon = new AccountButton(40);
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
        HelloApplication.scene.setRoot(new ResultsPage());
    }

    //MARK: Methods
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
}