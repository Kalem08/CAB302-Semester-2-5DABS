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
import app.model.SQLitePlantDAO;
import app.page_logic.EditPageLogic;

public class EditPage extends VBox
{
    int buttonWidth = 150;
    int buttonHeight = 40;
    
    private final Plant plant;
    private final String searchQuery;
    private final PlantEditField plantNameField;
    private final PlantEditField scientificNameField;
    private final PlantEditField descriptionField;
    private final PlantEditField growthInfoField;
    private final PlantEditField locationField;
    private final PlantEditField familyField;
    private final PlantEditField genusField;
    private final PlantEditField speciesField;

    //MARK: Constructor
    public EditPage(Plant plant)
    {
        this(plant, "");
    }

    public EditPage(Plant plant, String searchQuery)
    {
        this.plant = plant;
        this.searchQuery = searchQuery == null ? "" : searchQuery;
        Header header = new Header();

        //MARK: Body
        Label titleLabel = new Label("Edit Plant");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        String plantName = plant == null ? "" : plant.getVernacularName();
        String scientificName = plant == null ? "" : plant.getSciName();
        String description = plant == null ? "" : plant.getGeneralisedInfo();
        String growthInfo = plant == null || plant.getGrowthInfo() == null ? "" : plant.getGrowthInfo();
        String location = plant == null ? "" : plant.getLocation();

        plantNameField = new PlantEditField("Plant name", plantName);
        scientificNameField = new PlantEditField("Scientific name", scientificName);
        descriptionField = new PlantEditField("Description", description, 150, true);
        growthInfoField = new PlantEditField("Growth info", growthInfo, 150, true);
        locationField = new PlantEditField("Location", location);
        familyField = new PlantEditField("Family", plant == null ? "" : plant.getFamily());
        genusField = new PlantEditField("Genus", plant == null ? "" : plant.getGenus());
        speciesField = new PlantEditField("Species", plant == null ? "" : plant.getSpecies());

        // ScrollPane scrollArea = createScrollNode();

        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        //MARK: Bottom buttons
        CustomButton backButton = new CustomButton("Quit without saving", buttonWidth, buttonHeight);
        backButton.setTextColour(Design.white);
        backButton.setTextHoverColour(Design.grey95);
        backButton.setBackgroundColor(Design.red);
        backButton.setBackgroundHoverColor(Design.darkRed);
        backButton.setOnMouseClicked(event -> Navigation.openPlantPage(plant, this.searchQuery));

        CustomButton saveButton = new CustomButton("Submit changes", buttonWidth, buttonHeight);
        saveButton.setTextColour(Design.white);
        saveButton.setTextHoverColour(Design.grey95);
        saveButton.setBackgroundColor(Design.softGreen);
        saveButton.setBackgroundHoverColor(Design.darkSoftGreen);
        // saveButton.setOnMouseClicked(event -> saveEdits());
        saveButton.setOnMouseClicked(event ->
            EditPageLogic.saveEdits(formPlantFromTextfields(), searchQuery)
        );

        HBox bottomRow = new HBox(
            backButton,
            saveButton
        );
        bottomRow.setSpacing(Design.spacing);

        VBox content = new VBox(
            titleLabel,

            // scrollArea,
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

        VBox page = new VBox(
            header,
            content
        );
        page.setFillWidth(true);

        ScrollPane scrollPane = new ScrollPane(
            page
        );
        scrollPane.setFitToWidth(true); //this allows the header to stretch to the maximum
        // scrollPane.setFitToHeight(false); //when false, allows the ScrollPane to shorten it's height when the window shrinks in height 
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        getChildren().addAll(
            // header,
            // content

            scrollPane
        );
    }

    //MARK: Methods
    Plant formPlantFromTextfields()
    {
        Plant updatedPlant = new Plant(
            descriptionField.getText(),
            growthInfoField.getText(),
            plant.getImage(),
            locationField.getText(),
            scientificNameField.getText(),
            plant.getKingdom(),
            plant.getPhylum(),
            plant.getPclass(),
            plant.getOrder(),
            familyField.getText(),
            genusField.getText(),
            plant.getTaxonRank(),
            plantNameField.getText(),
            speciesField.getText(),
            plant.getEdible(),
            plant.getHazardous(),
            plant.getSubspecies()
        );
        updatedPlant.setID(plant.getId());
        return updatedPlant;
    }
}