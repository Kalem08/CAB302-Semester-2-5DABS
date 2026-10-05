package app.pages;

//MARK: Imports
import javafx.geometry.Insets;
// import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import app.Design;
import app.Navigation;
import app.components.CustomButton;
import app.components.Header;
import app.components.PlantEditField;
import app.model.Plant;

//this page allows users to make a request to change details of a Plant entry
public class EditPage extends VBox
{
    int buttonWidth = 150;
    int buttonHeight = 40;

    int textfieldWidth = 400;
    int textAreaWidth = 800;

    //MARK: Constructor
    public EditPage(Plant plant)
    {
        Header header = new Header();

        //MARK: Body
        Label titleLabel = new Label("Edit Plant Page");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        String plantName = plant == null ? "Plant Name" : plant.getVernacularName();
        String scientificName = plant == null ? "Subtitle" : plant.getSciName();
        String description = plant == null ? "Description" : plant.getGeneralisedInfo();
        String growthInfo = (plant == null || plant.getGrowthInfo() == null || plant.getGrowthInfo().isBlank()) ? "" : plant.getGrowthInfo();
        String location = plant == null ? "" : plant.getLocation();

        //fields
        PlantEditField plantNameField = new PlantEditField("Plant name", plantName);
        PlantEditField scientificNameField = new PlantEditField("Scientific name", scientificName);
        PlantEditField descriptionField = new PlantEditField("Description", description, 150, true);
        PlantEditField growthInfoField = new PlantEditField("Growth info", growthInfo, 150, true);
        PlantEditField locationField = new PlantEditField("Location", location);
        PlantEditField familyField = new PlantEditField("Family", plant.getFamily());
        PlantEditField genusField = new PlantEditField("Genus", plant.getGenus());
        PlantEditField speciesField = new PlantEditField("Species", plant.getSpecies());

        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        //MARK: Bottom buttons
        CustomButton backButton = new CustomButton("Quit without saving", buttonWidth, buttonHeight);
        backButton.setTextColour(Design.white);
        backButton.setTextHoverColour(Design.grey95);
        backButton.setBackgroundColor(Design.red);
        backButton.setBackgroundHoverColor(Design.darkRed);
        backButton.setOnMouseClicked(event -> Navigation.openPlantPage(plant));

        CustomButton saveButton = new CustomButton("Submit changes", buttonWidth, buttonHeight);
        saveButton.setTextColour(Design.white);
        saveButton.setTextHoverColour(Design.grey95);
        saveButton.setBackgroundColor(Design.softGreen);
        saveButton.setBackgroundHoverColor(Design.darkSoftGreen);
        saveButton.setOnMouseClicked(event -> saveEdits());

        HBox bottomRow = new HBox(
            backButton,
            saveButton
        );
        bottomRow.setSpacing(Design.spacing);

        VBox content = new VBox(
            titleLabel,

            plantNameField,
            scientificNameField,
            descriptionField,
            growthInfoField,
            locationField,
            familyField,
            genusField,
            speciesField,

            bottomSpacer,
            bottomRow
        );
        content.setSpacing(Design.spacing);
        content.setPadding(new Insets(Design.padding));
        content.setFillWidth(false); //stop nodes in this container from stetching to their maximum horizontal size
        VBox.setVgrow(content, Priority.ALWAYS);

        getChildren().addAll(
            header,
            // testLabel
            content
        );
    }

    //MARK: Methods
    void saveEdits()
    {

    }
}