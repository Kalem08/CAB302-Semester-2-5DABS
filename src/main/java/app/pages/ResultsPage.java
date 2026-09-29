package app.pages;

//MARK: Imports
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.List;

import app.Design;
import app.Navigation;
import app.components.Header;
import app.components.SearchResult;
import app.model.Plant;
import app.model.SQLitePlantDAO;

public class ResultsPage extends VBox
{
    //MARK: Variables
    private static final int PAGE_SIZE = 20;
    private final SQLitePlantDAO plantDAO = new SQLitePlantDAO();
    private final String query;
    private final ComboBox<String> locationFilter = createFilter("Location", "stateProvince");
    private final ComboBox<String> familyFilter = createFilter("Family", "family");
    private final ComboBox<String> genusFilter = createFilter("Genus", "genus");
    private final Label resultLabel = new Label();
    private final Pagination pagination = new Pagination();

    public ResultsPage()
    {
        this("");
    }

    public ResultsPage(String query)
    {
        this.query = query == null ? "" : query;
        Header header = new Header();
        getChildren().add(header);

        HBox filters = new HBox(Design.spacing, locationFilter, familyFilter, genusFilter);
        filters.setPadding(new Insets(Design.padding, Design.padding, 0, Design.padding));
        locationFilter.setOnAction(event -> refreshResults());
        familyFilter.setOnAction(event -> refreshResults());
        genusFilter.setOnAction(event -> refreshResults());

        getChildren().addAll(resultLabel, filters, pagination);
        VBox.setVgrow(pagination, Priority.ALWAYS);
        refreshResults();
    }

    //MARK: Create filter
    private ComboBox<String> createFilter(String prompt, String column)
    {
        ComboBox<String> filter = new ComboBox<>();
        filter.setPromptText(prompt);
        filter.getItems().add("All " + prompt.toLowerCase());
        filter.getItems().addAll(plantDAO.distinctValues(column));
        filter.getSelectionModel().selectFirst();
        return filter;
    }

    //MARK: Refresh results
    private void refreshResults()
    {
        int resultCount = plantDAO.count(query, selectedValue(locationFilter),
            selectedValue(familyFilter), selectedValue(genusFilter));
        String displayedQuery = query.trim();
        resultLabel.setText("Showing " + resultCount + " results"
            + (displayedQuery.isEmpty() ? "." : " for '" + displayedQuery + "'."));

        int pageCount = Math.max(1, (resultCount + PAGE_SIZE - 1) / PAGE_SIZE);
        pagination.setPageCount(pageCount);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(this::createPage);
    }

    private String selectedValue(ComboBox<String> filter)
    {
        String value = filter.getValue();
        return value == null || value.startsWith("All ") ? "" : value;
    }

    //MARK: Create page
    private ScrollPane createPage(int pageIndex)
    {
        VBox list = new VBox(Design.spacing);
        list.setPadding(new Insets(Design.padding));

        List<Plant> results = plantDAO.search(query, selectedValue(locationFilter),
            selectedValue(familyFilter), selectedValue(genusFilter), PAGE_SIZE, pageIndex * PAGE_SIZE);
        for (Plant plant : results)
        {
            SearchResult result = new SearchResult(plant);
            result.setOnMouseClicked(event -> Navigation.openPlantPage(plant, query));
            list.getChildren().add(result);
        }

        ScrollPane scrollPane = new ScrollPane(list);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        return scrollPane;
    }
}