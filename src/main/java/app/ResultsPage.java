package app;

import app.model.Plant;
import app.model.SQLitePlantDAO;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ResultsPage extends VBox
{
    private static final int PAGE_SIZE = 20;
    private final SQLitePlantDAO plantDAO = new SQLitePlantDAO();

    public ResultsPage()
    {
        this("");
    }

    public ResultsPage(String query)
    {
        Header header = new Header();
        getChildren().add(header);

        int resultCount = plantDAO.count(query);
        String displayedQuery = query == null ? "" : query.trim();
        Label label = new Label("Showing " + resultCount + " results"
            + (displayedQuery.isEmpty() ? "." : " for '" + displayedQuery + "'."));

        int pageCount = Math.max(1, (resultCount + PAGE_SIZE - 1) / PAGE_SIZE);
        Pagination pagination = new Pagination(pageCount, 0);
        pagination.setPageFactory(pageIndex -> createPage(query, pageIndex));
        VBox.setVgrow(pagination, Priority.ALWAYS);

        getChildren().addAll(label, pagination);
    }

    private ScrollPane createPage(String query, int pageIndex)
    {
        VBox list = new VBox(Design.spacing);
        list.setPadding(new Insets(Design.padding));

        List<Plant> results = plantDAO.search(query, PAGE_SIZE, pageIndex * PAGE_SIZE);
        for (Plant plant : results)
        {
            SearchResult result = new SearchResult(plant);
            result.setOnMouseClicked(event -> openPlantPage(plant));
            list.getChildren().add(result);
        }

        ScrollPane scrollPane = new ScrollPane(list);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        return scrollPane;
    }

    void openPlantPage(Plant plant)
    {
        HelloApplication.scene.setRoot(new PlantPage(plant));
    }

}