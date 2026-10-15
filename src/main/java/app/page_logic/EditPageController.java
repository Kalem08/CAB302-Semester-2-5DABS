package app.page_logic;

import app.Navigation;
import app.model.Plant;
import app.model.SQLitePlantDAO;
import javafx.scene.control.Alert;

public class EditPageController
{
    //MARK: Save edits
    /**
     * Save the changes made to a plant to the database.
     * @param updatedPlant The new plant object to insert into the database
     * @param searchQuery The term to use when returning to the PlantPage
     */
    public static void saveEdits(Plant updatedPlant, String searchQuery) //note: addPlant method won't have the query paramater
    {
        if (updatedPlant == null)
        {
            showError("No plant entry is available to update.", false);
            return;
        }

        try
        {
            new SQLitePlantDAO().updatePlant(updatedPlant);
            Navigation.openPlantPage(updatedPlant, searchQuery);
        }
        catch (RuntimeException exception)
        {
            showError("The plant entry could not be updated: " + exception.getMessage(), false);
        }
    }

    //MARK: Add plant
    public static void addPlant(Plant newPlant, String searchQuery)
    {
        if (newPlant == null)
        {
            showError("No plant entry is available to update.", true);
            return;
        }

        try
        {
            new SQLitePlantDAO().addPlant(newPlant);
            Navigation.openPlantPage(newPlant, searchQuery);
        }
        catch (RuntimeException exception)
        {
            showError("The plant entry could not be added: " + exception.getMessage(), true);
        }
    }

    //MARK: Show error
    /**
     * Shows an error message.
     * @param message The message to display.
     */
    private static void showError(String message, boolean isAdding)
    {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle(isAdding ? "Unable to add plant" : "Unable to update plant");
        error.setHeaderText("The plant entry could not be saved.");
        error.setContentText(message);
        error.showAndWait();
    }    
}