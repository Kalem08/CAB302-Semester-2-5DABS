package app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * MockPlantDAO is a mock implementation of the IPlantDAO interface for testing purposes.
 */
public class MockPlantDAO implements IPlantDAO {
    /**
     * A static list of plants to be used as a mock database.
     */
    public ArrayList<Plant> plants = new ArrayList<>();

    private int autoIncrementedId = 0;

    @Override
    public void addPlant(Plant plant) {
        plant.setID(autoIncrementedId);
        autoIncrementedId++;
        plants.add(plant);
    }

    @Override
    public void updatePlant(Plant plant) {
        for (int i =0; i < plants.size(); i++) {
            if (plants.get(i).getId() == plant.getId()) {
                plants.set(i, plant);
                break;
            }
        }
    }

    @Override
    public void deletePlant(Plant plant) {
        plants.remove(plant);
    }

    @Override
    public Plant getPlant(int id) {
        for (Plant plant : plants) {
            if (plant.getId() == id) {
                return plant;
            }
        }
        return null;
    }

    @Override
    public List<Plant> getAllPlants() {
        return new ArrayList<>(plants);
    }

    @Override
    public List<Plant> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllPlants();
        }

        String normalisedQuery = query.trim().toLowerCase(Locale.ROOT);
        List<Plant> matches = new ArrayList<>();
        for (Plant plant : plants) {
            if (plant.getSciName().toLowerCase(Locale.ROOT).contains(normalisedQuery)
                    || plant.getVernacularName().toLowerCase(Locale.ROOT).contains(normalisedQuery)) {
                matches.add(plant);
            }
        }
        return matches;
    }
}
