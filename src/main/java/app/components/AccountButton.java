package app.components;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import app.Design;
import app.Navigation;
import User.authManager;
import app.HelloApplication;

/**
 * A button in the upper right-hand corner of the screen that shows the account icon and account name.
 */
public class AccountButton extends StackPane
{
    //MARK: Variables
    int profileDiameter = 40;
    int profileRadius = profileDiameter / 2;

    int width = 150;
    int height = profileDiameter + Design.padding * 2;

    Rectangle background;
    Label label;
    
    Color backgroundColour = Design.grey90;
    Color backgroundHoverColor = Design.grey80;

    //MARK: Constructor
    /**
     * Creates a clickable element for the account name and icon
     * @param defaultColour
     * @param hoverColour
     */
    public AccountButton(Color defaultColour, Color hoverColour)
    {
        backgroundColour = defaultColour;
        backgroundHoverColor = hoverColour;

        //background
        background = new Rectangle();
        background.setFill(backgroundColour);
        background.setArcWidth(height);
        background.setArcHeight(height);
        background.widthProperty().bind(this.widthProperty());
        background.heightProperty().bind(this.heightProperty());
        
        //MARK: Content
        //text
        label = new Label(authManager.getUsername());
        StackPane labelContainer = new StackPane(label);
        labelContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(labelContainer, Priority.ALWAYS);

        //MARK: Profile icon
        Circle greenCircle = new Circle(profileRadius, Design.mediumGreen);

        Image image = Design.getImage("Leaf.png");
        ImageView leafIcon = new ImageView(image);
        leafIcon.setFitWidth(profileRadius);
        leafIcon.setPreserveRatio(true);

        StackPane profileIcon = new StackPane(
            greenCircle,
            leafIcon
        );
        profileIcon.setPrefWidth(profileDiameter);
        
        //MARK: Content
        HBox content = new HBox(
            labelContainer,
            profileIcon
        );
        content.setPadding(new Insets(Design.padding / 2));
        content.setAlignment(Pos.CENTER_RIGHT);

        getChildren().addAll(
            background,
            content
        );
        setMinWidth(width);

        setHoverEffect();
        setOnMouseClicked(event -> Navigation.openAccountPage());
    }

    //MARK: Methods
    /**
     * Sets the hover effect for the button.
     */
    void setHoverEffect()
    {
        //mouse over
        setOnMouseEntered(event -> {
            background.setFill(backgroundHoverColor);
            label.setUnderline(true);
        });
        
        //mouse exit
        setOnMouseExited(event -> {
            background.setFill(backgroundColour);
            label.setUnderline(false);
        });
    }
}