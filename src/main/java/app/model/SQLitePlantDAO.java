package app.model;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public List<Plant> getAllPlants() {
        return List.of();
    }
}