package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ResultsPage extends VBox
{
    public ResultsPage()
    {
        Header header = new Header();
        getChildren().add(header);

        VBox list = new VBox();

        Label label = new Label("Showing 3 results for 'SearchTerm'.");
        list.getChildren().add(label);
        
        //add results
        for (int i = 0; i < 3; i++)
        {
            SearchResult result = new SearchResult();
            result.setOnMouseClicked(event -> openPlantPage());
            // HBox.setHgrow(result, Priority.ALWAYS);

            list.getChildren().add(result);
        }

        list.setSpacing(Design.spacing);
        list.setPadding(new Insets(Design.padding));
        // list.setAlignment(Pos.TOP_LEFT);
        // list.setMinWidth(0);

        Button showMoreButton = new Button("Show more");
        showMoreButton.setOnAction(event -> openPlantPage());
        
        //create a new VBox so 'more' button can sit centred
        VBox bottomSection = new VBox(showMoreButton);
        bottomSection.setAlignment(Pos.CENTER);

        getChildren().addAll(list, bottomSection);
        // setAlignment(Pos.CENTER_LEFT); //does this stop header from shrinking-anchor being the centre

        // setTop(header);
        // setCenter(list);
    }

    void openPlantPage()
    {
        HelloApplication.scene.setRoot(new PlantPage());
    }
}