package app.model;

import java.util.ArrayList;
import java.util.List;

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
}
