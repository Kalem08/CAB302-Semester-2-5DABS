package app.pages;

//MARK: Imports
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import app.Design;
import app.Navigation;
import app.components.CustomButton;
import app.components.Header;
import app.components.PlantEditField;
import app.model.Plant;
// import app.model.SQLitePlantDAO;
import app.page_logic.EditPageLogic;

public class EditPage extends VBox
{
    int buttonWidth = 150;
    int buttonHeight = 40;
    
    private final Plant plant;
    private final String searchQuery;

    //fields
    private final PlantEditField plantNameField;
    private final PlantEditField scientificNameField;
    private final PlantEditField descriptionField;
    private final PlantEditField growthInfoField;
    private final PlantEditField locationField;

    private final PlantEditField kingdomField;
    private final PlantEditField phylumField;
    private final PlantEditField classField;
    private final PlantEditField orderField;
    private final PlantEditField familyField;
    private final PlantEditField genusField;
    private final PlantEditField speciesField;
    private final PlantEditField taxonRankField;

    private final PlantEditField edibleField;
    private final PlantEditField hazardousField;
    private final PlantEditField subspeciesField;

    //MARK: Constructors
    // public EditPage(Plant plant)
    // {
    //     this(plant, "", false);
    // }

    public EditPage(Plant plant, boolean isAddPage)
    {
        this(plant, "", isAddPage);
    }

    public EditPage(Plant plant, String searchQuery, boolean isAddPage)
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

        kingdomField = new PlantEditField("Kingdom", plant == null ? "" : plant.getKingdom());
        phylumField = new PlantEditField("Phylum", plant == null ? "" : plant.getPhylum());
        classField = new PlantEditField("Class", plant == null ? "" : plant.getPclass());
        orderField = new PlantEditField("Order", plant == null ? "" : plant.getOrder());
        familyField = new PlantEditField("Family", plant == null ? "" : plant.getFamily());
        genusField = new PlantEditField("Genus", plant == null ? "" : plant.getGenus());
        speciesField = new PlantEditField("Species", plant == null ? "" : plant.getSpecies());
        taxonRankField = new PlantEditField("Taxon Rank", plant == null ? "" : plant.getTaxonRank());

        edibleField = new PlantEditField("Edible", plant == null ? "" : plant.getEdible());
        hazardousField = new PlantEditField("Hazardous", plant == null ? "" : plant.getHazardous());
        subspeciesField = new PlantEditField("Subspecies", plant == null ? "" : plant.getSubspecies());

        VBox leftColumn = new VBox(
            plantNameField,
            scientificNameField,
            descriptionField,
            growthInfoField,
            locationField,

            edibleField,
            hazardousField,
            subspeciesField
        );
        leftColumn.setSpacing(Design.spacing);
        leftColumn.setFillWidth(false); //stop smaller fields from stretching their width to match the wider fields in the same column

        VBox rightColumn = new VBox(
            kingdomField,
            phylumField,
            classField,
            orderField,
            familyField,
            genusField,
            speciesField,
            taxonRankField
        );
        rightColumn.setSpacing(Design.spacing);
        rightColumn.setFillWidth(false);

        Region columnSpacer = new Region();
        HBox.setHgrow(columnSpacer, Priority.ALWAYS);

        HBox columns = new HBox(
            leftColumn,
            columnSpacer,
            rightColumn
        );

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
        saveButton.setOnMouseClicked(event -> submit(isAddPage));

        HBox bottomRow = new HBox(
            backButton,
            saveButton
        );
        bottomRow.setSpacing(Design.spacing);

        //MARK: Body
        VBox content = new VBox(
            titleLabel,
            columns,
            bottomSpacer,
            bottomRow
        );
        content.setSpacing(Design.spacing);
        content.setPadding(new Insets(Design.padding));
        content.setFillWidth(true); //tell nodes in this container to stetch to their maximum horizontal size (stetches horizontal spacer between the columns)
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
        // scrollPane.setFitToHeight(false); //when false, allows the ScrollPane to shorten it's height when the window shrinks in height  (false by default, hence commented out)
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        getChildren().addAll(
            scrollPane
        );
    }

    /**
     * Tell the EditPage logic to either create and save a new plant, or save the edits to an existing plant.
     */
    void submit(boolean isAdding)
    {
        if (isAdding)
            EditPageLogic.addPlant(formPlantFromTextfields(), searchQuery);
        else
            EditPageLogic.saveEdits(formPlantFromTextfields(), searchQuery);
    }

    //MARK: Form plant
    /**
     * Uses the textfields from the EditPage to form a plant class instance.
     * @return A new plant object
     */
    Plant formPlantFromTextfields()
    {
        Plant updatedPlant = new Plant(
            descriptionField.getText(),
            growthInfoField.getText(),
            plant.getImage(),
            locationField.getText(),
            scientificNameField.getText(),
            kingdomField.getText(),
            phylumField.getText(),
            classField.getText(),
            orderField.getText(),
            familyField.getText(),
            genusField.getText(),
            taxonRankField.getText(),
            plantNameField.getText(),
            speciesField.getText(),
            edibleField.getText(),
            hazardousField.getText(),
            subspeciesField.getText()
        );
        updatedPlant.setID(plant.getId());
        return updatedPlant;
    }
}