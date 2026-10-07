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

/**
 * A page that displays the results of a given search.
 */
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

    //MARK: Constructors
    /**
     * Creates a page with the results of a search with no query specified.
     */
    public ResultsPage()
    {
        this("");
    }

    /**
     * Creates a page with the results of a search with the specified query.
     * @param query The string to use when searching the database by plant name
     */
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
    /**
     * Creates a drop-down option for each of the distince values listed in a particular column of the plant database.
     * @param prompt The text shown when the drop-down is collapsed
     * @param column The name of the column in the database to retrieve distinct values from
     * @return A drop-down [ComboBox]
     */
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
    /**
     * Switches page in the pagination element, and updates the 'results' count at the top of the page.
     */
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

    //MARK: Selected value
    /**
     * Returns a string based on the filter selected in the ComboBox drop-down.
     * @param filter The ComboBox/drop-down to filter with
     * @return The value selected from the filter [String]
     */
    private String selectedValue(ComboBox<String> filter)
    {
        String value = filter.getValue();
        return (value == null || value.startsWith("All ")) ? "" : value;
    }

    //MARK: Create page
    /**
     * Creates a scrolling page with the search results, based on the filters selected.
     * @param pageIndex The index of the current page in the pagination
     * @return A ScrollPane with the results
     */
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