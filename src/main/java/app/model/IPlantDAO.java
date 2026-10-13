package app.model;

import java.util.List;
/**
 * IPlantDAO is an interface that defines the methods for interacting with a plant database.
 */
public interface IPlantDAO {
    /**
     * Adds a new plant to the database.
     * @param plant The plant to add.
     */
     void addPlant(Plant plant);

    /**
     * Updates an existing plant in the database.
     * @param plant The plant to update.
     */
    void updatePlant(Plant plant);

    /**
     * Deletes a plant from the database.
     * @param plant The plant to delete.
     */
    void deletePlant(Plant plant);

    /**
     * Retrieves a plant from the database.
     * @param id the id of the plant to retrieve.
     * @return The plant with the given id, or null if not found.
     */
    Plant getPlant(int id);

    /**
     * Retrieves all plants from the database.
     * @return A list of all plants in the database.
     */
    List<Plant> getAllPlants();

    /**
     * Searches plants by scientific or vernacular name.
     * @param query the name text to search for
     * @return all plants when the query is blank, otherwise matching plants
     */
    List<Plant> search(String query);
}
