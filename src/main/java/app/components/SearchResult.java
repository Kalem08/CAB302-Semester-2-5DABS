package app.components;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import app.model.Plant;

import app.Design;

//this file creates a UI element for each search result
public class SearchResult extends StackPane
{
    int height = 100;

    /**
     * Creates an empty result element in the search results page.
     */
    public SearchResult()
    {
        this(null);
    }

    /**
     * Creates a result element in the search results page.
     * @param plant The plant to display in this node.
     */
    public SearchResult(Plant plant)
    {
        Rectangle background = new Rectangle();
        background.setFill(Color.LIGHTGREY);
        background.setArcWidth(Design.cornerRadius);
        background.setArcHeight(Design.cornerRadius);
        background.widthProperty().bind(this.widthProperty()); //completely fill this parent element
        background.heightProperty().bind(this.heightProperty());
        setOnMouseEntered(event -> background.setFill(Design.grey70));
        setOnMouseExited(event -> background.setFill(Color.LIGHTGREY));

        int size = height - Design.spacing / 2;
        Rectangle imageBackground = new Rectangle(size, size, Color.GREEN);
        imageBackground.setArcWidth(Design.cornerRadius);
        imageBackground.setArcHeight(Design.cornerRadius);

        ImageView imageView = new ImageView();
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        if (plant != null && plant.getImage() != null && !plant.getImage().isBlank()) {
            imageView.setImage(new Image(plant.getImage(), true));
        }

        StackPane imagePane = new StackPane(imageBackground, imageView);
        imagePane.setMinSize(size, size);
        imagePane.setPrefSize(size, size);
        imagePane.setMaxSize(size, size);

        String scientificName = plant == null ? "Plant Name" : plant.getSciName();
        String vernacularName = plant == null ? "" : plant.getVernacularName();
        String location = plant == null ? "" : plant.getLocation();
        String description = plant == null ? "Plant description." : plant.getGeneralisedInfo();

        Label scientificNameLabel = new Label(scientificName);
        scientificNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        VBox text = new VBox(scientificNameLabel);
        if (vernacularName != null && !vernacularName.isBlank()) {
            text.getChildren().add(new Label(vernacularName));
        }
        if (location != null && !location.isBlank()) {
            text.getChildren().add(new Label(location));
        }
        text.getChildren().add(new Label(description));

        HBox hStack = new HBox(
            imagePane,
            text
        );
        hStack.setSpacing(Design.spacing);
        hStack.setPadding(new Insets(Design.padding));
        hStack.setAlignment(Pos.TOP_LEFT); //all elements within the result should go to the top left

        getChildren().addAll(
            background,
            hStack
        );
        setPrefHeight(height);
        setMinWidth(0);
    }
}