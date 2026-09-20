package app.model;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

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
             PreparedStatement statement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    plant.setID(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add plant to database", e);
        }
    }

    @Override
    public void updatePlant(Plant plant) {

        String sql = """
            UPDATE PlantsData
            SET Generalised_info = ?,
                images = ?,
                stateProvince = ?,
                scientificName = ?,
                kingdom = ?,
                phylum = ?,
                class = ?,
                "order" = ?,
                family = ?,
                genus = ?,
                taxonRank = ?,
                vernacularName = ?,
                species = ?
            WHERE PlantID = ?
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

            statement.setInt(14, plant.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update plant in database", e);
        }
    }

    @Override
    public void deletePlant(Plant plant) {

        String sql = """
            DELETE FROM PlantsData
            WHERE PlantID = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, plant.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete plant from database", e);
        }
    }

    @Override
    public Plant getPlant(int id) {

        String sql = """
            SELECT *
            FROM PlantsData
            WHERE PlantID = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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
        return search(query, "", "", "", limit, offset);
    }

    public List<Plant> search(String query, String location, String family,
                              String genus, int limit, int offset) {
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
        List<String> conditions = new ArrayList<>();
        if (hasQuery) {
            conditions.add("(LOWER(scientificName) LIKE LOWER(?) OR LOWER(vernacularName) LIKE LOWER(?))");
        }
        addFilterCondition(conditions, location, "stateProvince");
        addFilterCondition(conditions, family, "family");
        addFilterCondition(conditions, genus, "genus");
        String whereClause = conditions.isEmpty() ? "" : "WHERE " + String.join(" AND ", conditions);
        sql = sql.formatted(whereClause);

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int parameterIndex = 1;
            if (hasQuery) {
                String searchPattern = "%" + query.trim() + "%";
                statement.setString(parameterIndex++, searchPattern);
                statement.setString(parameterIndex++, searchPattern);
            }
            parameterIndex = setFilterParameter(statement, parameterIndex, location);
            parameterIndex = setFilterParameter(statement, parameterIndex, family);
            parameterIndex = setFilterParameter(statement, parameterIndex, genus);
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
        return count(query, "", "", "");
    }

    public int count(String query, String location, String family, String genus) {
        String sql = "SELECT COUNT(*) FROM PlantsData";
        boolean hasQuery = query != null && !query.trim().isEmpty();
        List<String> conditions = new ArrayList<>();
        if (hasQuery) {
            conditions.add("(LOWER(scientificName) LIKE LOWER(?) OR LOWER(vernacularName) LIKE LOWER(?))");
        }
        addFilterCondition(conditions, location, "stateProvince");
        addFilterCondition(conditions, family, "family");
        addFilterCondition(conditions, genus, "genus");
        if (!conditions.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", conditions);
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (hasQuery) {
                String searchPattern = "%" + query.trim() + "%";
                statement.setString(1, searchPattern);
                statement.setString(2, searchPattern);
            }
            int parameterIndex = hasQuery ? 3 : 1;
            parameterIndex = setFilterParameter(statement, parameterIndex, location);
            parameterIndex = setFilterParameter(statement, parameterIndex, family);
            setFilterParameter(statement, parameterIndex, genus);

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

    public List<String> distinctValues(String column) {
        if (!List.of("stateProvince", "family", "genus").contains(column)) {
            throw new IllegalArgumentException("Unsupported filter column: " + column);
        }

        List<String> values = new ArrayList<>();
        String sql = "SELECT DISTINCT " + column + " FROM PlantsData"
                + " WHERE " + column + " IS NOT NULL AND TRIM(" + column + ") <> ''"
                + " ORDER BY " + column;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                values.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to retrieve filter values", e);
        }

        return values;
    }

    private void addFilterCondition(List<String> conditions, String value, String column) {
        if (value != null && !value.trim().isEmpty()) {
            conditions.add("LOWER(" + column + ") = LOWER(?)");
        }
    }

    private int setFilterParameter(PreparedStatement statement, int parameterIndex, String value)
            throws SQLException {
        if (value != null && !value.trim().isEmpty()) {
            statement.setString(parameterIndex++, value);
        }
        return parameterIndex;
    }
}