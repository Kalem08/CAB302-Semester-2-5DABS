package app;

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
        Header header = new Header();

        HBox hStack = new HBox();
        VBox rightColumn = new VBox();
        VBox leftColumn = new VBox();

        Region horizontalSpacer = new Region();
        HBox.setHgrow(horizontalSpacer, Priority.ALWAYS);
        
        Label title = new Label("Plant Name");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label subtitle = new Label("Subtitle");

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

        Label description = new Label("Description");
        Label locationText = new Label("Location");

        ImageView imageView = new ImageView(Design.getImage("Hibiscus.jpg"));
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        Label imageSubtitle = new Label("A photo of the Hibiscus flower.");

        leftColumn.getChildren().addAll(
            title,
            subtitle,
            editPrompt,
            verticalSpacer,
            description,
            locationText
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
            hStack
        );
        hStack.setSpacing(Design.spacing);
        hStack.setPadding(new Insets(Design.padding));
    }
}