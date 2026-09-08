package app;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
// import javafx.scene.image.*;

//this file creates a UI element for each search result
public class SearchResult extends StackPane
{
    int width = 800;
    int height = 100;

    public SearchResult()
    {
        // Rectangle background = new Rectangle(width, height, Color.LIGHTGREY);
        Rectangle background = new Rectangle();
        background.setFill(Color.LIGHTGREY);
        background.setArcWidth(Design.cornerRadius);
        background.setArcHeight(Design.cornerRadius);
        background.widthProperty().bind(this.widthProperty()); //completely fill this parent element
        background.heightProperty().bind(this.heightProperty());

        StackPane innerPane = new StackPane();

        int size = height - Design.spacing / 2;
        Rectangle imageRectangle = new Rectangle(size, size, Color.GREEN);
        imageRectangle.setArcWidth(Design.cornerRadius);
        imageRectangle.setArcHeight(Design.cornerRadius);
        // imageRectangle.widthProperty().bind(innerPane.widthProperty()); //completely fill this parent element
        // imageRectangle.heightProperty().bind(innerPane.heightProperty());

        //try a region instead of a rectangle
        Region innerBackground = new Region();
        innerBackground.setStyle(
            "-fx-background-color: green;" +
            "-fx-background-radius: 10;"
        );

        innerPane.getChildren().addAll(innerBackground);
        HBox.setHgrow(innerPane, Priority.ALWAYS);

        Label label = new Label("Plant Name\nPlant description.");

        HBox hStack = new HBox(
            // innerPane,
            imageRectangle,
            label
        );
        hStack.setSpacing(Design.spacing);
        hStack.setPadding(new Insets(Design.padding));
        hStack.setAlignment(Pos.TOP_LEFT); //all elements within the result should go to the top left

        getChildren().addAll(background, hStack);
        setPrefHeight(height);
        setMinWidth(0);
    }
}