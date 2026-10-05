package app.pages;

//MARK: Imports
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.*;

import app.Design;
import app.HelloApplication;
import app.Navigation;
import app.components.Header;
import app.model.Plant;

//this is the page that contains all of the details/content about a plant
public class PlantPage extends VBox
{
    //MARK: Constructors
    public PlantPage()
    {
        this(null);
    }

    public PlantPage(Plant plant)
    {
        this(plant, "");
    }

    public PlantPage(Plant plant, String searchQuery)
    {
        Header header = new Header();

        Region horizontalSpacer = new Region();
        HBox.setHgrow(horizontalSpacer, Priority.ALWAYS);

        //MARK: Left column
        
        String titleText = plant == null ? "Plant Name" : plant.getVernacularName();
        String scientificName = plant == null ? "Subtitle" : plant.getSciName();
        String descriptionText = plant == null ? "Description" : plant.getGeneralisedInfo();
        String growthInfoText = plant == null || plant.getGrowthInfo() == null
            || plant.getGrowthInfo().isBlank()
            ? "Growth information not available."
            : plant.getGrowthInfo();
        String location = plant == null ? "Location" : "Location: " + plant.getLocation();
        String taxonomy = plant == null
            ? ""
            : "Family: " + plant.getFamily() + " | Genus: " + plant.getGenus()
                + " | Species: " + plant.getSpecies();

        Label title = new Label(titleText);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label subtitle = new Label(scientificName);

        //edit prompt
        Label editPrompt = new Label("Want to suggest a change? Click here.");
        editPrompt.setTextFill(Design.hyperlinkColour);
        editPrompt.setUnderline(true);
        editPrompt.setOnMouseClicked(event -> Navigation.openEditPage(plant));

        editPrompt.setOnMouseEntered(event -> {
            editPrompt.setTextFill(Color.BLUE);
        });
        editPrompt.setOnMouseExited(event -> {
            editPrompt.setTextFill(Design.hyperlinkColour);
        });

        Region verticalSpacer = new Region();
        verticalSpacer.setPrefHeight(25);
        Region growthSectionSpacer = new Region();
        growthSectionSpacer.setPrefHeight(Design.spacing);
        Region locationSectionSpacer = new Region();
        locationSectionSpacer.setPrefHeight(Design.spacing);
        Region taxonomySectionSpacer = new Region();
        taxonomySectionSpacer.setPrefHeight(Design.spacing);
        Region dropdownSpacer = new Region();
        dropdownSpacer.setPrefHeight(Design.spacing);

        Label generalInformationHeading = new Label("General Information");
        generalInformationHeading.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Label description = new Label(descriptionText);
        description.setWrapText(true);
        Label growthInformationHeading = new Label("Growth Information");
        growthInformationHeading.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Label growthInformation = new Label(growthInfoText);
        growthInformation.setWrapText(true);
        Label locationText = new Label(location);
        Label taxonomyText = new Label(taxonomy);

        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        Button returnToResultsButton = new Button("Return to search results");
        returnToResultsButton.setOnAction(event ->
            HelloApplication.scene.setRoot(new ResultsPage(searchQuery)));

        VBox leftColumn = new VBox(
            title,
            subtitle,
            verticalSpacer,
            generalInformationHeading,
            description,
            growthSectionSpacer,
            growthInformationHeading,
            growthInformation,
            locationSectionSpacer,
            locationText,
            taxonomySectionSpacer,
            taxonomyText,
            createTaxonomyPane(plant),
            dropdownSpacer,
            createAdditionalInfoPane(plant),

            bottomSpacer,
            returnToResultsButton,
            editPrompt
        );
        // VBox.setVgrow(leftColumn, Priority.ALWAYS);

        //MARK: Right column
        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        if (plant != null && plant.getImageUrl() != null) {
            imageView.setImage(new Image(plant.getImageUrl(), true));
        }

        Label imageSubtitle = new Label("Image from the Atlas of Living Australia.");

        VBox rightColumn = new VBox(
            imageView,
            imageSubtitle
        );

        //MARK: Horizontal
        HBox content = new HBox(
            leftColumn,
            horizontalSpacer,
            rightColumn
        );
        content.setSpacing(Design.spacing);
        content.setPadding(new Insets(Design.padding));
        VBox.setVgrow(content, Priority.ALWAYS);

        getChildren().addAll(
            header,
            content
        );
    }

    private TitledPane createTaxonomyPane(Plant plant)
    {
        GridPane table = new GridPane();
        table.setHgap(Design.spacing * 2);
        table.setVgap(Design.spacing);
        table.setPadding(new Insets(Design.padding));

        addTaxonomyRow(table, 0, "Scientific name", plant == null ? null : plant.getSciName());
        addTaxonomyRow(table, 1, "Kingdom", plant == null ? null : plant.getKingdom());
        addTaxonomyRow(table, 2, "Phylum", plant == null ? null : plant.getPhylum());
        addTaxonomyRow(table, 3, "Class", plant == null ? null : plant.getPclass());
        addTaxonomyRow(table, 4, "Order", plant == null ? null : plant.getOrder());
        addTaxonomyRow(table, 5, "Family", plant == null ? null : plant.getFamily());
        addTaxonomyRow(table, 6, "Genus", plant == null ? null : plant.getGenus());
        addTaxonomyRow(table, 7, "Taxon rank", plant == null ? null : plant.getTaxonRank());
        addTaxonomyRow(table, 8, "Species", plant == null ? null : plant.getSpecies());

        TitledPane taxonomyPane = new TitledPane("Taxonomic information", table);
        taxonomyPane.setExpanded(false);
        taxonomyPane.setMaxWidth(Double.MAX_VALUE);
        return taxonomyPane;
    }

    private TitledPane createAdditionalInfoPane(Plant plant)
    {
        GridPane table = new GridPane();
        table.setHgap(Design.spacing * 2);
        table.setVgap(Design.spacing);
        table.setPadding(new Insets(Design.padding));

        addTaxonomyRow(table, 0, "Edible", plant == null ? null : plant.getEdible());
        addTaxonomyRow(table, 1, "Hazardous", plant == null ? null : plant.getHazardous());
        addTaxonomyRow(table, 2, "Subspecies", plant == null ? null : plant.getSubspecies());

        TitledPane additionalInfoPane = new TitledPane("Additional information", table);
        additionalInfoPane.setExpanded(false);
        additionalInfoPane.setMaxWidth(Double.MAX_VALUE);
        return additionalInfoPane;
    }

    private void addTaxonomyRow(GridPane table, int row, String field, String value)
    {
        Label fieldLabel = new Label(field);
        fieldLabel.setStyle("-fx-font-weight: bold;");
        table.add(fieldLabel, 0, row);
        table.add(new Label(value == null || value.isBlank() ? "Not available" : value), 1, row);
    }
}