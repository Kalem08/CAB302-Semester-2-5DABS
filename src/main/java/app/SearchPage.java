package app;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SearchPage extends BorderPane
{
    public SearchPage()
    {
        Label label = new Label("Showing 5 results for 'searchTerm'...");
        Label label1 = new Label("Plant 1");
        Label label2 = new Label("Plant 2");
        Label label3 = new Label("Plant 3");
        Label label4 = new Label("Plant 4");
        Label label5 = new Label("Plant 5");

        Button plantPageButton = new Button("Open plant page");
        plantPageButton.setOnAction(event -> openPlantPage());

        VBox list = new VBox(
            label,
            label1,
            label2,
            label3,
            label4,
            label5,
            plantPageButton
        );
        list.setSpacing(10);
        list.setPadding(new Insets(10));

        Header header = new Header();

        setTop(header);
        setCenter(list);

        // list.setAlignment(Pos.CENTER);
        // header.setAlignment(Pos.CENTER);
    }

    void openPlantPage()
    {
        HelloApplication.scene.setRoot(new PlantPage());
    }
}