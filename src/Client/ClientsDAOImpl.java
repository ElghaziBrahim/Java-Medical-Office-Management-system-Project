package Client;
import DataBase.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientsDAOImpl implements ClientsDAOInterface {
    private Connection connection;

    public ClientsDAOImpl() {
    	try {
            this.connection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        List<Client> clientsList = new ArrayList<>();
        String query = "SELECT * FROM Clients";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getLong("id"),
                        resultSet.getInt("version"),
                        resultSet.getString("titre"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom")
                );
                clientsList.add(client);
            }
        }
        return clientsList;
    }

    @Override
    public Client getClientById(long id) throws SQLException {
        String query = "SELECT * FROM Clients WHERE id = ?";
        Client client = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    client = new Client(
                            resultSet.getLong("id"),
                            resultSet.getInt("version"),
                            resultSet.getString("titre"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom")
                    );
                }
            }
        }
        return client;
    }

    @Override
    public void addClient(Client client) throws SQLException {
        String query = "INSERT INTO Clients (version, titre, nom, prenom) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, client.getVersion());
            statement.setString(2, client.getTitre());
            statement.setString(3, client.getNom());
            statement.setString(4, client.getPrenom());

            statement.executeUpdate();
        }
    }

    @Override
    public void updateClient(Client client) throws SQLException {
        String query = "UPDATE Clients SET version = ?, titre = ?, nom = ?, prenom = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, client.getVersion());
            statement.setString(2, client.getTitre());
            statement.setString(3, client.getNom());
            statement.setString(4, client.getPrenom());
            statement.setLong(5, client.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteClient(long id) throws SQLException {
        String query = "DELETE FROM Clients WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        }
    }
}
