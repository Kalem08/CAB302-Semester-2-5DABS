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

    @Override
    public List<Plant> search(String query) {
        return search(query, Integer.MAX_VALUE, 0);
    }

    public List<Plant> search(String query, int limit, int offset) {
        if (limit < 1 || offset < 0) {
            throw new IllegalArgumentException("Limit must be positive and offset cannot be negative");
        }

        List<Plant> plants = new ArrayList<>();
        String sql = """
            SELECT *
            FROM PlantsData
            %s
            ORDER BY PlantID
            LIMIT ? OFFSET ?
            """;
        boolean hasQuery = query != null && !query.trim().isEmpty();
        String whereClause = hasQuery
                ? "WHERE LOWER(scientificName) LIKE LOWER(?) OR LOWER(vernacularName) LIKE LOWER(?)"
                : "";
        sql = sql.formatted(whereClause);

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int parameterIndex = 1;
            if (hasQuery) {
                String searchPattern = "%" + query.trim() + "%";
                statement.setString(parameterIndex++, searchPattern);
                statement.setString(parameterIndex++, searchPattern);
            }
            statement.setInt(parameterIndex++, limit);
            statement.setInt(parameterIndex, offset);

            try (ResultSet resultSet = statement.executeQuery()) {
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Unable to search plants in database",
                    e
            );
        }

        return plants;
    }

    public int count(String query) {
        String sql = "SELECT COUNT(*) FROM PlantsData";
        boolean hasQuery = query != null && !query.trim().isEmpty();
        if (hasQuery) {
            sql += " WHERE LOWER(scientificName) LIKE LOWER(?)"
                    + " OR LOWER(vernacularName) LIKE LOWER(?)";
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (hasQuery) {
                String searchPattern = "%" + query.trim() + "%";
                statement.setString(1, searchPattern);
                statement.setString(2, searchPattern);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Unable to count plants in database",
                    e
            );
        }
    }
}