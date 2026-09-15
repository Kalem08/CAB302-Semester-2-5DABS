import java.util.List;
import app.model.Plant;
import app.model.SQLitePlantDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SQLitePlantDAOTest {

    @Test
    public void getPlantShouldReturnPlantFromDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        Plant plant = dao.getPlant(1);

        assertNotNull(plant);
        assertEquals(1, plant.getId());
        assertEquals("Gungurru", plant.getVernacularName());
        assertEquals("Eucalyptus caesia", plant.getSciName());
    }

    @Test
    public void getAllPlantsShouldReturnAllPlantsFromDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        List<Plant> plants = dao.getAllPlants();

        assertNotNull(plants);
        assertEquals(313, plants.size());
    }

    @Test
    public void addPlantShouldPersistPlantToDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        Plant newPlant = new Plant(
                "TDD test plant for SQLite persistence",
                "",
                "Queensland",
                "Testus plantus",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "Testus",
                "species",
                "TDD Test Plant",
                "Testus plantus"
        );

        dao.addPlant(newPlant);

        assertTrue(newPlant.getId() > 0);

        List<Plant> plants = dao.getAllPlants();

        boolean found = plants.stream()
                .anyMatch(plant ->
                        "TDD Test Plant".equals(plant.getVernacularName())
                                && "Testus plantus".equals(plant.getSciName())
                );

        assertEquals(true, found);
    }

    @Test
    public void updatePlantShouldPersistChangesToDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        Plant plant = dao.getPlant(1);

        assertNotNull(plant);

        String originalName = plant.getVernacularName();

        plant.setVernacularName("Updated TDD Plant");

        dao.updatePlant(plant);

        Plant updatedPlant = dao.getPlant(1);

        assertNotNull(updatedPlant);
        assertEquals("Updated TDD Plant", updatedPlant.getVernacularName());

        // Restore original database value after test
        plant.setVernacularName(originalName);
        dao.updatePlant(plant);
    }

    @Test
    void deletePlantShouldRemovePlantFromDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange
        Plant plant = new Plant(
                "Plant to be deleted",
                "delete-test.jpg",
                "Queensland",
                "Delete test scientific name",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "Eucalyptus",
                "species",
                "Delete Test Plant",
                "deleteSpecies"
        );

        dao.addPlant(plant);

        // Act
        dao.deletePlant(plant);

        // Assert
        Plant deletedPlant = dao.getPlant(plant.getId());

        assertNull(deletedPlant);
    }
}