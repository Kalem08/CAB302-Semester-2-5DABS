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