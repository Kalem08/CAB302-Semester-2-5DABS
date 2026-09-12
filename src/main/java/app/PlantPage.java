package app;

import app.model.Plant;
//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.*;

//this is the page that contains all of the details/content about a plant
public class PlantPage extends VBox
{
    public PlantPage()
    {
        this(null);
    }

    public PlantPage(Plant plant)
    {
        Header header = new Header();

        HBox hStack = new HBox();
        VBox rightColumn = new VBox();
        VBox leftColumn = new VBox();

        Region horizontalSpacer = new Region();
        HBox.setHgrow(horizontalSpacer, Priority.ALWAYS);
        
        String titleText = plant == null ? "Plant Name" : plant.getVernacularName();
        String scientificName = plant == null ? "Subtitle" : plant.getSciName();
        String descriptionText = plant == null ? "Description" : plant.getGeneralisedInfo();
        String location = plant == null ? "Location" : "Location: " + plant.getLocation();
        String taxonomy = plant == null
            ? ""
            : "Family: " + plant.getFamily() + " | Genus: " + plant.getGenus()
                + " | Species: " + plant.getSpecies();

        Label title = new Label(titleText);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label subtitle = new Label(scientificName);

        Label editPrompt = new Label("Want to suggest a change? Click here.");
        editPrompt.setTextFill(Design.hyperlinkColour);
        editPrompt.setUnderline(true);

        editPrompt.setOnMouseEntered(event -> {
            editPrompt.setTextFill(Color.BLUE);
        });
        editPrompt.setOnMouseExited(event -> {
            editPrompt.setTextFill(Design.hyperlinkColour);
        });

        Region verticalSpacer = new Region();
        verticalSpacer.setPrefHeight(25);

        Label description = new Label(descriptionText);
        Label locationText = new Label(location);
        Label taxonomyText = new Label(taxonomy);

        ImageView imageView = new ImageView(Design.getImage("Hibiscus.jpg"));
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        Label imageSubtitle = new Label("A photo of the Hibiscus flower.");

        leftColumn.getChildren().addAll(
            title,
            subtitle,
            verticalSpacer,
            description,
            locationText,
            taxonomyText
        );

        rightColumn.getChildren().addAll(
            imageView,
            imageSubtitle
        );

        hStack.getChildren().addAll(
            leftColumn,
            horizontalSpacer,
            rightColumn
        );

        getChildren().addAll(
            header,
            hStack,
            editPrompt
        );
        hStack.setSpacing(Design.spacing);
        hStack.setPadding(new Insets(Design.padding));
        editPrompt.setPadding(new Insets(0, Design.padding, Design.padding, Design.padding));
    }
}