package app.components;

//MARK: Imports
import app.Design;
import app.model.Plant;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//this file creates a UI element for each search result
public class SearchResult extends StackPane
{
    int width = 800;
    int height = 100;

    public SearchResult()
    {
        this(null);
    }

    public SearchResult(Plant plant)
    {
        // Rectangle background = new Rectangle(width, height, Color.LIGHTGREY);
        Rectangle background = new Rectangle();
        background.setFill(Color.LIGHTGREY);
        background.setArcWidth(Design.cornerRadius);
        background.setArcHeight(Design.cornerRadius);
        background.widthProperty().bind(this.widthProperty()); //completely fill this parent element
        background.heightProperty().bind(this.heightProperty());

        int size = height - Design.spacing / 2;
        Rectangle imageBackground = new Rectangle(size, size, Color.GREEN);
        imageBackground.setArcWidth(Design.cornerRadius);
        imageBackground.setArcHeight(Design.cornerRadius);

        ImageView imageView = new ImageView();
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        if (plant != null && plant.getImageUrl() != null) {
            imageView.setImage(new Image(plant.getImageUrl(), true));
        }

        StackPane imagePane = new StackPane(imageBackground, imageView);
        imagePane.setMinSize(size, size);
        imagePane.setPrefSize(size, size);
        imagePane.setMaxSize(size, size);

        String plantName = plant == null ? "Plant Name" : plant.getVernacularName();
        String scientificName = plant == null ? "" : plant.getSciName();
        String location = plant == null ? "" : plant.getLocation();
        String description = plant == null ? "Plant description." : plant.getGeneralisedInfo();
        Label label = new Label(plantName + "\n" + scientificName + " - " + location + "\n" + description);

        HBox hStack = new HBox(
            imagePane,
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