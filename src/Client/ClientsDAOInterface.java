package Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientsDAOInterface {
    List<Client> getAllClients() throws SQLException;

    Client getClientById(long id) throws SQLException;

    void addClient(Client client) throws SQLException;

    void updateClient(Client client) throws SQLException;

    void deleteClient(long id) throws SQLException;
}
