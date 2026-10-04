// Import required modules and connections
import database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.List;
import app.model.Plant;
import app.model.SQLitePlantDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SQLitePlantDAOTest {

    private Path testDatabasePath;

    @BeforeEach
    void setUp() throws Exception {
        // Create a temporary SQLite database file for this test
        testDatabasePath = Files.createTempFile("plantopedia-test-", ".db");
        // Redirect DatabaseConnection to the temporary test database
        DatabaseConnection.setDatabaseUrl(
                "jdbc:sqlite:" + testDatabasePath.toAbsolutePath()
        );
        // Create the PlantsData schema required by SQLitePlantDAO
        try (
                Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute("""
                CREATE TABLE PlantsData (
                    PlantID INTEGER PRIMARY KEY AUTOINCREMENT,
                    Generalised_info VARCHAR(512),
                    growthInfo VARCHAR(512),
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
            // Seed the temporary database with three known Plant records
            // used by the DAO retrieval, update, and collection tests
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
            statement.execute("""
                CREATE TABLE AdditionalInfo (
                    PlantID INTEGER PRIMARY KEY,
                    Edible TEXT NOT NULL DEFAULT 'N/a',
                    Hazardous TEXT NOT NULL DEFAULT 'N/a',
                    Subspecies TEXT NOT NULL DEFAULT 'N/a',
                    FOREIGN KEY (PlantID) REFERENCES PlantsData (PlantID)
                )
                """);
            statement.execute("""
                INSERT INTO AdditionalInfo (PlantID)
                SELECT PlantID FROM PlantsData
                """);
        }
    }

    // Test Clean Up - Restores the normal database configuration and deletes
    // the temporary SQLite database created for the completed test.
    @AfterEach
    void tearDown() throws Exception {
        // Restore DatabaseConnection to its normal application database URL
        DatabaseConnection.resetDatabaseUrl();
        // Remove the temporary test database file
        Files.deleteIfExists(testDatabasePath);
    }

    // DAO Retrieval Test - Checks that an existing Plant can be retrieved by ID
    // and that key Plant fields are correctly reconstructed from the database.
    @Test
    public void getPlantShouldReturnPlantFromDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        Plant plant = dao.getPlant(1);

        assertNotNull(plant);
        assertEquals(1, plant.getId());
        assertEquals("Gungurru", plant.getVernacularName());
        assertEquals("Eucalyptus testus", plant.getSciName());
        assertEquals("N/a", plant.getEdible());
        assertEquals("N/a", plant.getHazardous());
        assertEquals("N/a", plant.getSubspecies());
    }

    // DAO Retrieval Edge Case - Checks that requesting a Plant ID that does not
    // exist in the database returns null rather than an invalid Plant object.
    @Test
    public void getPlantShouldReturnNullWhenPlantDoesNotExist() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        Plant plant = dao.getPlant(999);

        assertNull(plant);
    }

    // DAO Collection Retrieval Test - Checks that getAllPlants() retrieves
    // every Plant record currently stored in the SQLite database.
    @Test
    public void getAllPlantsShouldReturnAllPlantsFromDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        List<Plant> plants = dao.getAllPlants();

        assertNotNull(plants);
        assertEquals(3, plants.size());
    }

    @Test
    void getAllPlantsAndSearchShouldIncludeAdditionalInfo() throws Exception {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("""
                 UPDATE AdditionalInfo
                 SET Edible = ?, Hazardous = ?, Subspecies = ?
                 WHERE PlantID = ?
                 """)) {
            statement.setString(1, "yes");
            statement.setString(2, "no");
            statement.setString(3, "yes");
            statement.setInt(4, 2);
            assertEquals(1, statement.executeUpdate());
        }

        SQLitePlantDAO dao = new SQLitePlantDAO();
        Plant fromSearch = dao.search("Acacia testus", 10, 0).get(0);
        Plant fromAllPlants = dao.getAllPlants().stream()
                .filter(plant -> plant.getId() == 2)
                .findFirst()
                .orElseThrow();

        assertEquals("yes", fromSearch.getEdible());
        assertEquals("no", fromSearch.getHazardous());
        assertEquals("yes", fromSearch.getSubspecies());
        assertEquals("yes", fromAllPlants.getEdible());
        assertEquals("no", fromAllPlants.getHazardous());
        assertEquals("yes", fromAllPlants.getSubspecies());
    }

    // DAO Create Test - Checks that addPlant() persists a new Plant to SQLite,
    // assigns it a database ID, and makes the saved Plant retrievable.
    @Test
    public void addPlantShouldPersistPlantToDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        Plant newPlant = new Plant(
                "TDD test plant for SQLite persistence",
                "Shrub to 2 m; full sun; well-drained soil.",
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
                "Testus plantus",
                "yes",
                "no",
                "no"
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
        assertEquals(
                "Shrub to 2 m; full sun; well-drained soil.",
                dao.getPlant(newPlant.getId()).getGrowthInfo()
        );
        assertEquals("yes", dao.getPlant(newPlant.getId()).getEdible());
        assertEquals("no", dao.getPlant(newPlant.getId()).getHazardous());
        assertEquals("no", dao.getPlant(newPlant.getId()).getSubspecies());
    }

    // DAO Update Test - Checks that changes made to an existing Plant object
    // are persisted to SQLite and returned when the Plant is retrieved again.
    @Test
    public void updatePlantShouldPersistChangesToDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        Plant plant = dao.getPlant(1);

        assertNotNull(plant);

        String originalName = plant.getVernacularName();
        String originalGrowthInfo = plant.getGrowthInfo();

        plant.setVernacularName("Updated TDD Plant");
        plant.setGrowthInfo("Updated growth requirements and traits.");

        dao.updatePlant(plant);

        Plant updatedPlant = dao.getPlant(1);

        assertNotNull(updatedPlant);
        assertEquals("Updated TDD Plant", updatedPlant.getVernacularName());
        assertEquals(
                "Updated growth requirements and traits.",
                updatedPlant.getGrowthInfo()
        );

        // Restore original database value after test
        plant.setVernacularName(originalName);
        plant.setGrowthInfo(originalGrowthInfo);
        dao.updatePlant(plant);
    }

    @Test
    void updatePlantShouldPersistAdditionalInfoFields() {
        SQLitePlantDAO dao = new SQLitePlantDAO();
        Plant plant = dao.getPlant(1);

        assertNotNull(plant);
        plant.setEdible("yes");
        plant.setHazardous("no");
        plant.setSubspecies("yes");

        dao.updatePlant(plant);

        Plant updatedPlant = dao.getPlant(1);
        assertNotNull(updatedPlant);
        assertEquals("yes", updatedPlant.getEdible());
        assertEquals("no", updatedPlant.getHazardous());
        assertEquals("yes", updatedPlant.getSubspecies());
    }

    @Test
    void updatePlantShouldCreateMissingAdditionalInfoRow() throws Exception {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM AdditionalInfo WHERE PlantID = ?")) {
            statement.setInt(1, 1);
            assertEquals(1, statement.executeUpdate());
        }

        SQLitePlantDAO dao = new SQLitePlantDAO();
        Plant plant = dao.getPlant(1);
        assertNotNull(plant);
        assertEquals("N/a", plant.getEdible());

        plant.setEdible("yes");
        dao.updatePlant(plant);

        assertEquals("yes", dao.getPlant(1).getEdible());
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM AdditionalInfo WHERE PlantID = ?")) {
            statement.setInt(1, 1);
            try (ResultSet resultSet = statement.executeQuery()) {
                assertTrue(resultSet.next());
                assertEquals(1, resultSet.getInt(1));
            }
        }
    }

    // DAO Update Edge Case - Checks that attempting to update a Plant with an
    // unknown ID does not create, remove, or modify records in the database.
    @Test
    void updateNonExistentPlantShouldNotAffectDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange
        Plant plant = new Plant(
                "Non-existent update plant",
                null,
                "",
                "Queensland",
                "Nonexistent update plantus",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "TestGenus",
                "species",
                "Missing Update Plant",
                "Nonexistent species",
                null,
                null,
                null
        );

        plant.setID(999);

        int numberOfPlantsBeforeUpdate = dao.getAllPlants().size();

        // Act
        dao.updatePlant(plant);

        // Assert
        int numberOfPlantsAfterUpdate = dao.getAllPlants().size();

        assertEquals(numberOfPlantsBeforeUpdate, numberOfPlantsAfterUpdate);
        assertNull(dao.getPlant(999));
    }

    // DAO Create Edge Case - Checks that a Plant with an empty image value can
    // still be persisted and retrieved correctly from the SQLite database.
    @Test
    void addPlantWithEmptyImageShouldPersistToDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange
        Plant plant = new Plant(
                "Plant with no image",
                null,
                "",
                "Queensland",
                "Eucalyptus testus",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "Eucalyptus",
                "species",
                "No Image Test Plant",
                "Eucalyptus testus",
                null,
                null,
                null
        );

        // Act
        dao.addPlant(plant);

        // Assert
        assertTrue(plant.getId() > 0);

        Plant savedPlant = dao.getPlant(plant.getId());

        assertNotNull(savedPlant);
        assertEquals("", savedPlant.getImage());
        assertEquals("No Image Test Plant", savedPlant.getVernacularName());
        assertEquals("N/a", savedPlant.getEdible());
        assertEquals("N/a", savedPlant.getHazardous());
        assertEquals("N/a", savedPlant.getSubspecies());

        // Clean Up
        dao.deletePlant(plant);
    }

    // DAO Delete Test - Checks that deletePlant() removes an existing Plant
    // record so that it can no longer be retrieved from the database.
    @Test
    void deletePlantShouldRemovePlantFromDatabase() throws Exception {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange
        Plant plant = new Plant(
                "Plant to be deleted",
                null,
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
                "deleteSpecies",
                null,
                null,
                null
        );

        dao.addPlant(plant);

        // Act
        dao.deletePlant(plant);

        // Assert
        Plant deletedPlant = dao.getPlant(plant.getId());

        assertNull(deletedPlant);
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM AdditionalInfo WHERE PlantID = ?")) {
            statement.setInt(1, plant.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                assertTrue(resultSet.next());
                assertEquals(0, resultSet.getInt(1));
            }
        }
    }

    // DAO Delete Edge Case - Checks that attempting to delete a Plant with an
    // unknown ID leaves the existing SQLite database records unchanged.
    @Test
    void deleteNonExistentPlantShouldNotAffectDatabase() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange
        Plant plant = new Plant(
                "Non-existent test plant",
                null,
                "",
                "Queensland",
                "Nonexistent plantus",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "TestGenus",
                "species",
                "Missing Plant",
                "Nonexistent species",
                null,
                null,
                null
        );

        plant.setID(999);

        int numberOfPlantsBeforeDelete = dao.getAllPlants().size();

        // Act
        dao.deletePlant(plant);

        // Assert
        int numberOfPlantsAfterDelete = dao.getAllPlants().size();

        assertEquals(numberOfPlantsBeforeDelete, numberOfPlantsAfterDelete);
        assertNull(dao.getPlant(999));
    }

    // DAO Collection Edge Case - Checks that getAllPlants() returns a non-null
    // empty list when the SQLite database contains no Plant records.
    @Test
    void getAllPlantsShouldReturnEmptyListWhenDatabaseIsEmpty() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange
        List<Plant> existingPlants = dao.getAllPlants();

        for (Plant plant : existingPlants) {
            dao.deletePlant(plant);
        }

        // Act
        List<Plant> plants = dao.getAllPlants();

        // Assert
        assertNotNull(plants);
        assertTrue(plants.isEmpty());
    }

    // Persistence Integration Test - Checks that all Plant fields are correctly
    // stored in SQLite and reconstructed after being retrieved from the database
    @Test
    void allPlantFieldsShouldPersistAndBeRetrievedCorrectly() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange
        Plant plant = new Plant(
                "Native Australian tree with distinctive red flowers",
                null,
                "eucalyptus_test.jpg",
                "Queensland",
                "Eucalyptus tereticornis",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "Eucalyptus",
                "species",
                "Forest Red Gum",
                "Eucalyptus tereticornis",
                "no",
                "no",
                "no"
        );

        // Act
        dao.addPlant(plant);

        Plant savedPlant = dao.getPlant(plant.getId());

        // Assert
        assertNotNull(savedPlant);

        assertEquals(plant.getGeneralisedInfo(), savedPlant.getGeneralisedInfo());
        assertEquals(plant.getGrowthInfo(), savedPlant.getGrowthInfo());
        assertEquals(plant.getEdible(), savedPlant.getEdible());
        assertEquals(plant.getHazardous(), savedPlant.getHazardous());
        assertEquals(plant.getSubspecies(), savedPlant.getSubspecies());
        assertEquals(plant.getImage(), savedPlant.getImage());
        assertEquals(plant.getLocation(), savedPlant.getLocation());
        assertEquals(plant.getSciName(), savedPlant.getSciName());
        assertEquals(plant.getKingdom(), savedPlant.getKingdom());
        assertEquals(plant.getPhylum(), savedPlant.getPhylum());
        assertEquals(plant.getPclass(), savedPlant.getPclass());
        assertEquals(plant.getOrder(), savedPlant.getOrder());
        assertEquals(plant.getFamily(), savedPlant.getFamily());
        assertEquals(plant.getGenus(), savedPlant.getGenus());
        assertEquals(plant.getTaxonRank(), savedPlant.getTaxonRank());
        assertEquals(plant.getVernacularName(), savedPlant.getVernacularName());
        assertEquals(plant.getSpecies(), savedPlant.getSpecies());

        // Clean Up
        dao.deletePlant(plant);
    }

    // Persistence Integration Test - Checks that multiple Plant objects are stored
    // as separate database records and can be retrieved independently
    @Test
    void multiplePlantsShouldPersistIndependently() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange
        Plant plantOne = new Plant(
                "Native Australian eucalyptus tree",
                null,
                "eucalyptus.jpg",
                "Queensland",
                "Eucalyptus tereticornis",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "Eucalyptus",
                "species",
                "Forest Red Gum",
                "Eucalyptus tereticornis",
                null,
                null,
                null
        );

        Plant plantTwo = new Plant(
                "Native Australian flowering tree",
                null,
                "bottlebrush.jpg",
                "New South Wales",
                "Callistemon citrinus",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "Callistemon",
                "species",
                "Crimson Bottlebrush",
                "Callistemon citrinus",
                null,
                null,
                null
        );

        // Act
        dao.addPlant(plantOne);
        dao.addPlant(plantTwo);

        Plant savedPlantOne = dao.getPlant(plantOne.getId());
        Plant savedPlantTwo = dao.getPlant(plantTwo.getId());

        // Assert
        assertNotNull(savedPlantOne);
        assertNotNull(savedPlantTwo);

        assertNotEquals(plantOne.getId(), plantTwo.getId());

        assertEquals("Forest Red Gum", savedPlantOne.getVernacularName());
        assertEquals("Crimson Bottlebrush", savedPlantTwo.getVernacularName());

        assertEquals("Eucalyptus tereticornis", savedPlantOne.getSciName());
        assertEquals("Callistemon citrinus", savedPlantTwo.getSciName());

        // Clean Up
        dao.deletePlant(plantOne);
        dao.deletePlant(plantTwo);
    }
    // Persistence Integration Test - Checks that updating one Plant record
    // does not modify another Plant record stored in the database
    @Test
    void updatingOnePlantShouldNotAffectAnotherPlant() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange
        Plant plantOne = new Plant(
                "Native Australian eucalyptus tree",
                null,
                "eucalyptus.jpg",
                "Queensland",
                "Eucalyptus tereticornis",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "Eucalyptus",
                "species",
                "Forest Red Gum",
                "Eucalyptus tereticornis",
                null,
                null,
                null
        );

        Plant plantTwo = new Plant(
                "Native Australian flowering tree",
                null,
                "bottlebrush.jpg",
                "New South Wales",
                "Callistemon citrinus",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "Callistemon",
                "species",
                "Crimson Bottlebrush",
                "Callistemon citrinus",
                null,
                null,
                null
        );

        dao.addPlant(plantOne);
        dao.addPlant(plantTwo);

        // Act
        plantOne.setVernacularName("Updated Forest Red Gum");
        dao.updatePlant(plantOne);

        Plant updatedPlantOne = dao.getPlant(plantOne.getId());
        Plant unchangedPlantTwo = dao.getPlant(plantTwo.getId());

        // Assert
        assertNotNull(updatedPlantOne);
        assertNotNull(unchangedPlantTwo);

        assertEquals(
                "Updated Forest Red Gum",
                updatedPlantOne.getVernacularName()
        );

        assertEquals(
                "Crimson Bottlebrush",
                unchangedPlantTwo.getVernacularName()
        );

        // Clean Up
        dao.deletePlant(plantOne);
        dao.deletePlant(plantTwo);
    }
    // Persistence Integration Test - Checks that deleting one Plant record
    // does not remove or modify another Plant record stored in the database
    @Test
    void deletingOnePlantShouldNotAffectAnotherPlant() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange
        Plant plantOne = new Plant(
                "Native Australian eucalyptus tree",
                null,
                "eucalyptus.jpg",
                "Queensland",
                "Eucalyptus tereticornis",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "Eucalyptus",
                "species",
                "Forest Red Gum",
                "Eucalyptus tereticornis",
                null,
                null,
                null
        );

        Plant plantTwo = new Plant(
                "Native Australian flowering tree",
                null,
                "bottlebrush.jpg",
                "New South Wales",
                "Callistemon citrinus",
                "Plantae",
                "Tracheophyta",
                "Magnoliopsida",
                "Myrtales",
                "Myrtaceae",
                "Callistemon",
                "species",
                "Crimson Bottlebrush",
                "Callistemon citrinus",
                null,
                null,
                null
        );

        dao.addPlant(plantOne);
        dao.addPlant(plantTwo);

        // Act
        dao.deletePlant(plantOne);

        Plant deletedPlant = dao.getPlant(plantOne.getId());
        Plant remainingPlant = dao.getPlant(plantTwo.getId());

        // Assert
        assertNull(deletedPlant);
        assertNotNull(remainingPlant);

        assertEquals(
                "Crimson Bottlebrush",
                remainingPlant.getVernacularName()
        );

        assertEquals(
                "Callistemon citrinus",
                remainingPlant.getSciName()
        );

        // Clean Up
        dao.deletePlant(plantTwo);
    }
    // Persistence Integration Edge Case - Checks that Plant data containing
    // apostrophes and other special characters persists correctly in SQLite
    @Test
    void plantWithSpecialCharactersShouldPersistCorrectly() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange
        Plant plant = new Plant(
                "Native plant that's commonly found in wet, shaded areas.",
                null,
                "bird's-nest-fern.jpg",
                "Queensland, Australia",
                "Asplenium australasicum",
                "Plantae",
                "Tracheophyta",
                "Polypodiopsida",
                "Polypodiales",
                "Aspleniaceae",
                "Asplenium",
                "species",
                "Bird's Nest Fern",
                "Asplenium australasicum",
                null,
                null,
                null
        );

        // Act
        dao.addPlant(plant);

        Plant savedPlant = dao.getPlant(plant.getId());

        // Assert
        assertNotNull(savedPlant);

        assertEquals(
                "Bird's Nest Fern",
                savedPlant.getVernacularName()
        );

        assertEquals(
                "Native plant that's commonly found in wet, shaded areas.",
                savedPlant.getGeneralisedInfo()
        );

        assertEquals(
                "Queensland, Australia",
                savedPlant.getLocation()
        );

        // Clean Up
        dao.deletePlant(plant);
    }


    @Test
    public void searchShouldSupportTwentyPlantPages() {

        SQLitePlantDAO dao = new SQLitePlantDAO();

        // Arrange - add enough controlled records to test pagination.
        // The temporary test database already contains 3 seeded plants.
        for (int i = 4; i <= 25; i++) {
            Plant plant = new Plant(
                    "Pagination test plant " + i,
                    null,
                    "",
                    "Queensland",
                    "Scientific name " + i,
                    "Plantae",
                    "Test phylum",
                    "Test class",
                    "Test order",
                    "Test family",
                    "Test genus",
                    "species",
                    "Test Plant " + i,
                    "Test species " + i,
                    null,
                    null,
                    null
            );

            dao.addPlant(plant);
        }

        // Act
        List<Plant> firstPage = dao.search("", 20, 0);
        List<Plant> secondPage = dao.search("", 20, 20);

        // Assert
        assertEquals(25, dao.count(""));
        assertEquals(20, firstPage.size());
        assertEquals(5, secondPage.size());

        // Verify that the second page begins after the first 20 records.
        assertEquals(1, firstPage.get(0).getId());
        assertEquals(21, secondPage.get(0).getId());
    }
}