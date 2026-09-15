package app.model;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLitePlantDAO implements IPlantDAO {

    @Override
    public void addPlant(Plant plant) {

        String sql = """
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
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, plant.getGeneralisedInfo());
            statement.setString(2, plant.getImage());
            statement.setString(3, plant.getLocation());
            statement.setString(4, plant.getSciName());
            statement.setString(5, plant.getKingdom());
            statement.setString(6, plant.getPhylum());
            statement.setString(7, plant.getPclass());
            statement.setString(8, plant.getOrder());
            statement.setString(9, plant.getFamily());
            statement.setString(10, plant.getGenus());
            statement.setString(11, plant.getTaxonRank());
            statement.setString(12, plant.getVernacularName());
            statement.setString(13, plant.getSpecies());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add plant to database", e);
        }
    }

    @Override
    public void updatePlant(Plant plant) {

    }

    @Override
    public void deletePlant(Plant plant) {

    }

    @Override
    public Plant getPlant(int id) {

        String sql = """
            SELECT *
            FROM PlantsData
            WHERE PlantID = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Plant plant = new Plant(
                            resultSet.getString("Generalised_info"),
                            resultSet.getString("images"),
                            resultSet.getString("stateProvince"),
                            resultSet.getString("scientificName"),
                            resultSet.getString("kingdom"),
                            resultSet.getString("phylum"),
                            resultSet.getString("class"),
                            resultSet.getString("order"),
                            resultSet.getString("family"),
                            resultSet.getString("genus"),
                            resultSet.getString("taxonRank"),
                            resultSet.getString("vernacularName"),
                            resultSet.getString("species")
                    );

                    plant.setID(resultSet.getInt("PlantID"));

                    return plant;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Unable to retrieve plant with ID " + id,
                    e
            );
        }

        return null;
    }

    @Override
    public List<Plant> getAllPlants() {

        List<Plant> plants = new ArrayList<>();

        String sql = """
            SELECT *
            FROM PlantsData
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Plant plant = new Plant(
                        resultSet.getString("Generalised_info"),
                        resultSet.getString("images"),
                        resultSet.getString("stateProvince"),
                        resultSet.getString("scientificName"),
                        resultSet.getString("kingdom"),
                        resultSet.getString("phylum"),
                        resultSet.getString("class"),
                        resultSet.getString("order"),
                        resultSet.getString("family"),
                        resultSet.getString("genus"),
                        resultSet.getString("taxonRank"),
                        resultSet.getString("vernacularName"),
                        resultSet.getString("species")
                );

                plant.setID(resultSet.getInt("PlantID"));

                plants.add(plant);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Unable to retrieve plants from database",
                    e
            );
        }

        return plants;
    }
}