package Medecin;
import DataBase.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedecinsDAOImpl implements MedecinsDAOInterface {
    private Connection connection;

    public MedecinsDAOImpl() {
    	try {
            this.connection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medecin> getAllMedecins() throws SQLException {
        List<Medecin> medecinsList = new ArrayList<>();
        String query = "SELECT * FROM Medecins";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Medecin medecin = new Medecin(
                        resultSet.getLong("id"),
                        resultSet.getInt("version"),
                        resultSet.getString("titre"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom")
                );
                medecinsList.add(medecin);
            }
        }
        return medecinsList;
    }

    @Override
    public Medecin getMedecinById(long id) throws SQLException {
        String query = "SELECT * FROM Medecins WHERE id = ?";
        Medecin medecin = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    medecin = new Medecin(
                            resultSet.getLong("id"),
                            resultSet.getInt("version"),
                            resultSet.getString("titre"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom")
                    );
                }
            }
        }
        return medecin;
    }

    @Override
    public void addMedecin(Medecin medecin) throws SQLException {
        String query = "INSERT INTO Medecins (version, titre, nom, prenom) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, medecin.getVersion());
            statement.setString(2, medecin.getTitre());
            statement.setString(3, medecin.getNom());
            statement.setString(4, medecin.getPrenom());

            statement.executeUpdate();
        }
    }

    @Override
    public void updateMedecin(Medecin medecin) throws SQLException {
        String query = "UPDATE Medecins SET version = ?, titre = ?, nom = ?, prenom = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, medecin.getVersion());
            statement.setString(2, medecin.getTitre());
            statement.setString(3, medecin.getNom());
            statement.setString(4, medecin.getPrenom());
            statement.setLong(5, medecin.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteMedecin(long id) throws SQLException {
        String query = "DELETE FROM Medecins WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        }
    }
}
