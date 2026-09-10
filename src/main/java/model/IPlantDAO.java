package model;

import java.util.List;

public interface IPlantDAO {
    /**
     * Adds a new plant to the database.
     * @param plant The plant to add.
     */
    public void addPlant(Plant plant);

    /**
     * Updates an existing plant in the database.
     * @param plant The plant to update.
     */
    public void updatePlant(Plant plant);

    /**
     * Deletes a plant from the database.
     * @param plant The plant to delete.
     */
    public void deletePlant(Plant plant);

    /**
     * Retrieves a plant from the database.
     * @param id the id of the plant to retrieve.
     * @return The plant with the given id, or null if not found.
     */
    public Plant getPlant(int id);

    /**
     * Retrieves all plants from the database.
     * @return A list of all plants in the database.
     */
    public List<Plant> getAllPlants();
}
