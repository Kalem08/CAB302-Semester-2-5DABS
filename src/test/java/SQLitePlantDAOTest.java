
import database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;

import java.util.List;
import app.model.Plant;
import app.model.SQLitePlantDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SQLitePlantDAOTest {

    private Path testDatabasePath;

    @BeforeEach
    void setUp() throws Exception {

        testDatabasePath = Files.createTempFile("plantopedia-test-", ".db");

        DatabaseConnection.setDatabaseUrl(
                "jdbc:sqlite:" + testDatabasePath.toAbsolutePath()
        );

        try (
                Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute("""
                CREATE TABLE PlantsData (
                    PlantID INTEGER PRIMARY KEY AUTOINCREMENT,
                    Generalised_info VARCHAR(512),
                    images VARCHAR(512),
                    stateProvince VARCHAR(512),
                    scientificName VARCHAR(512),
                    kingdom VARCHAR(512),
                    phylum VARCHAR(512),
                    class VARCHAR(512),
                    "order" VARCHAR(512),
                    family VARCHAR(512),
                    genus VARCHAR(512),
                    taxonRank VARCHAR(512),
                    vernacularName VARCHAR(512),
                    species VARCHAR(512)
                )
                """);
            statement.execute("""
        INSERT INTO PlantsData (
            Generalised_info,
            images,
            stateProvince,
            scientificName,
            kingdom,
            phylum,
            class,
            "order",
            family,
            genus,
            taxonRank,
            vernacularName,
            species
        )
        VALUES
        (
            'Test plant one',
            '',
            'Queensland',
            'Eucalyptus testus',
            'Plantae',
            'Tracheophyta',
            'Magnoliopsida',
            'Myrtales',
            'Myrtaceae',
            'Eucalyptus',
            'species',
            'Gungurru',
            'Eucalyptus testus'
        ),
        (
            'Test plant two',
            '',
            'New South Wales',
            'Acacia testus',
            'Plantae',
            'Tracheophyta',
            'Magnoliopsida',
            'Fabales',
            'Fabaceae',
            'Acacia',
            'species',
            'Test Wattle',
            'Acacia testus'
        ),
        (
            'Test plant three',
            '',
            'Victoria',
            'Banksia testus',
            'Plantae',
            'Tracheophyta',
            'Magnoliopsida',
            'Proteales',
            'Proteaceae',
            'Banksia',
            'species',
            'Test Banksia',
            'Banksia testus'
        )
        """);
        }
    }

    @AfterEach
    void tearDown() throws Exception {

        DatabaseConnection.resetDatabaseUrl();

        Files.deleteIfExists(testDatabasePath);
    }
    @Test
    public void getPlantShouldReturnPlantFromDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        Plant plant = dao.getPlant(1);

        assertNotNull(plant);
        assertEquals(1, plant.getId());
        assertEquals("Gungurru", plant.getVernacularName());
        assertEquals("Eucalyptus testus", plant.getSciName());
    }

    @Test
    public void getAllPlantsShouldReturnAllPlantsFromDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        List<Plant> plants = dao.getAllPlants();

        assertNotNull(plants);
        assertEquals(3, plants.size());
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