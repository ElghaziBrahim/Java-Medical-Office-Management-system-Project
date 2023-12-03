package Creneau;
import DataBase.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreneauxDAOImpl implements CreneauxDAOInterface {
    private Connection connection;

    public CreneauxDAOImpl() {
    	try {
            this.connection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Creneau> getAllCreneaux() throws SQLException {
        List<Creneau> creneauxList = new ArrayList<>();
        String query = "SELECT * FROM Creneaux";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Creneau creneau = new Creneau(
                        resultSet.getLong("id"),
                        resultSet.getInt("version"),
                        resultSet.getInt("hdebut"),
                        resultSet.getInt("mdebut"),
                        resultSet.getInt("hfin"),
                        resultSet.getInt("mfin"),
                        resultSet.getLong("id_medecin")
                );
                creneauxList.add(creneau);
            }
        }
        return creneauxList;
    }

    @Override
    public Creneau getCreneauById(long id) throws SQLException {
        String query = "SELECT * FROM Creneaux WHERE id = ?";
        Creneau creneau = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    creneau = new Creneau(
                            resultSet.getLong("id"),
                            resultSet.getInt("version"),
                            resultSet.getInt("hdebut"),
                            resultSet.getInt("mdebut"),
                            resultSet.getInt("hfin"),
                            resultSet.getInt("mfin"),
                            resultSet.getLong("id_medecin")
                    );
                }
            }
        }
        return creneau;
    }

    @Override
    public void addCreneau(Creneau creneau) throws SQLException {
        String query = "INSERT INTO Creneaux (version, hdebut, mdebut, hfin, mfin, id_medecin) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, creneau.getVersion());
            statement.setInt(2, creneau.getHdebut());
            statement.setInt(3, creneau.getMdebut());
            statement.setInt(4, creneau.getHfin());
            statement.setInt(5, creneau.getMfin());
            statement.setLong(6, creneau.getId_medecin());

            statement.executeUpdate();
        }
    }

    @Override
    public void updateCreneau(Creneau creneau) throws SQLException {
        String query = "UPDATE Creneaux SET version = ?, hdebut = ?, mdebut = ?, hfin = ?, mfin = ?, id_medecin = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, creneau.getVersion());
            statement.setInt(2, creneau.getHdebut());
            statement.setInt(3, creneau.getMdebut());
            statement.setInt(4, creneau.getHfin());
            statement.setInt(5, creneau.getMfin());
            statement.setLong(6, creneau.getId_medecin());
            statement.setLong(7, creneau.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteCreneau(long id) throws SQLException {
        String query = "DELETE FROM Creneaux WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        }
    }
}
