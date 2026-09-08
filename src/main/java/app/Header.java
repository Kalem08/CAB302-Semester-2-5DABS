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
    int searchElementHeight = 30;

    public Header()
    {
        //MARK: Background
        Rectangle background = new Rectangle();
        background.setFill(Color.LIGHTGRAY);
        background.widthProperty().bind(this.widthProperty()); //assume the width and height of the header
        background.heightProperty().bind(this.heightProperty());
        
        //MARK: Elements

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
        
        //textfield
        CustomTextfield searchTextfield = new CustomTextfield("Search...", 400, searchElementHeight);
        searchTextfield.setBackgroundColor(Design.superLightGrey);

        //search button
        CustomButton searchButton = new CustomButton("Search", 60, searchElementHeight);
        searchButton.setTextSize(12);
        searchButton.setBackgroundColor(Color.WHITESMOKE);
        searchButton.setBackgroundHoverColor(Design.grey70);
        searchButton.setTextColour(Design.grey20);
        searchButton.setTextHoverColour(Color.BLACK);
        searchButton.setOnMouseClicked(event -> search());

        //login/register
        Button logInButton = new Button("Log in");
        logInButton.setOnAction(event -> openLoginPage());
        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> openLoginPage());

        //acount
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
        content.setSpacing(Design.spacing);
        content.setPadding(new Insets(Design.padding));
        content.setFillHeight(false); //sub-elements should not stretch to fill the container
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