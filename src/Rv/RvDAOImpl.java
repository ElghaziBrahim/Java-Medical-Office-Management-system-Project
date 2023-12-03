package Rv;
import DataBase.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RvDAOImpl implements RvDAOInterface {
    private Connection connection;

    public RvDAOImpl() {
    	try {
            this.connection = DatabaseConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Rv> getAllRvs() throws SQLException {
        List<Rv> rvList = new ArrayList<>();
        String query = "SELECT * FROM Rv";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Rv rv = new Rv(
                        resultSet.getLong("id"),
                        resultSet.getDate("jour"),
                        resultSet.getLong("id_client"),
                        resultSet.getLong("id_creneau")
                );
                rvList.add(rv);
            }
        }
        return rvList;
    }

    @Override
    public Rv getRvById(long id) throws SQLException {
        String query = "SELECT * FROM Rv WHERE id = ?";
        Rv rv = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rv = new Rv(
                            resultSet.getLong("id"),
                            resultSet.getDate("jour"),
                            resultSet.getLong("id_client"),
                            resultSet.getLong("id_creneau")
                    );
                }
            }
        }
        return rv;
    }

    @Override
    public void addRv(Rv rv) throws SQLException {
        String query = "INSERT INTO Rv (jour, id_client, id_creneau) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, new java.sql.Date(rv.getJour().getTime()));
            statement.setLong(2, rv.getId_client());
            statement.setLong(3, rv.getId_creneau());

            statement.executeUpdate();
        }
    }

    @Override
    public void updateRv(Rv rv) throws SQLException {
        String query = "UPDATE Rv SET jour = ?, id_client = ?, id_creneau = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, new java.sql.Date(rv.getJour().getTime()));
            statement.setLong(2, rv.getId_client());
            statement.setLong(3, rv.getId_creneau());
            statement.setLong(4, rv.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteRv(long id) throws SQLException {
        String query = "DELETE FROM Rv WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        }
    }
}
