package app;

import app.model.MockPlantDAO;
import app.model.Plant;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ResultsPage extends VBox
{
    private final MockPlantDAO plantDAO = new MockPlantDAO();

    public ResultsPage()
    {
        this("");
    }

    public ResultsPage(String query)
    {
        Header header = new Header();
        getChildren().add(header);

        VBox list = new VBox();

        addExamplePlants();
        List<Plant> results = plantDAO.search(query);
        String displayedQuery = query == null ? "" : query.trim();
        Label label = new Label("Showing " + results.size() + " results"
            + (displayedQuery.isEmpty() ? "." : " for '" + displayedQuery + "'."));
        list.getChildren().add(label);
        
        //add results
        for (Plant plant : results)
        {
            SearchResult result = new SearchResult(plant);
            result.setOnMouseClicked(event -> openPlantPage(plant));
            // HBox.setHgrow(result, Priority.ALWAYS);

            list.getChildren().add(result);
        }

        list.setSpacing(Design.spacing);
        list.setPadding(new Insets(Design.padding));
        // list.setAlignment(Pos.TOP_LEFT);
        // list.setMinWidth(0);

        Button showMoreButton = new Button("Show more");
        showMoreButton.setOnAction(event -> loadMoreResults());
        
        //create a new VBox so 'more' button can sit centred
        VBox bottomSection = new VBox(showMoreButton);
        bottomSection.setAlignment(Pos.CENTER);

        getChildren().addAll(list, bottomSection);
        // setAlignment(Pos.CENTER_LEFT); //does this stop header from shrinking-anchor being the centre
    }

    private void addExamplePlants()
    {
        plantDAO.addPlant(new Plant(
            "A hardy native shrub with bright yellow flowers.",
            "",
            "QLD",
            "Acacia pycnantha",
            "Plantae",
            "Tracheophyta",
            "Magnoliopsida",
            "Fabales",
            "Fabaceae",
            "Acacia",
            "species",
            "Golden Wattle",
            "pycnantha"
        ));
        plantDAO.addPlant(new Plant(
            "A distinctive eucalyptus known for its pale flowers and smooth bark.",
            "",
            "VIC",
            "Eucalyptus caesia",
            "Plantae",
            "Tracheophyta",
            "Magnoliopsida",
            "Myrtales",
            "Myrtaceae",
            "Eucalyptus",
            "species",
            "Silver Princess",
            "caesia"
        ));
        plantDAO.addPlant(new Plant(
            "A colourful flowering plant found across eastern Australia.",
            "",
            "NSW",
            "Grevillea robusta",
            "Plantae",
            "Tracheophyta",
            "Magnoliopsida",
            "Proteales",
            "Proteaceae",
            "Grevillea",
            "species",
            "Silky Oak",
            "robusta"
        ));
    }

    void openPlantPage(Plant plant)
    {
        HelloApplication.scene.setRoot(new PlantPage(plant));
    }

    void loadMoreResults()
    {
        
    }
}