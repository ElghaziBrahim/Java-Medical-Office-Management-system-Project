package Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientsDAOImplSer implements ClientsDAOInterface, Serializable {
    private static final String FILE_PATH = "clients.ser";
    private static final long serialVersionUID = 1L;
    private List<Client> clientsList;

    public ClientsDAOImplSer() {
        this.clientsList = loadClientsFromFile();
    }

    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(clientsList);
    }

    @Override
    public Client getClientById(long id) {
        // Find and return the client by ID
        for (Client client : clientsList) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null; // Return null if not found
    }

    @Override
    public void addClient(Client client) {
        // Assign a new ID (for simplicity, you might want to use a more robust method)
        long newId = System.currentTimeMillis();
        client.setId(newId);

        clientsList.add(client);
        saveClientsToFile();
    }

    @Override
    public void updateClient(Client updatedClient) {
        // Find and update the client by ID
        for (int i = 0; i < clientsList.size(); i++) {
            if (clientsList.get(i).getId() == updatedClient.getId()) {
                clientsList.set(i, updatedClient);
                saveClientsToFile();
                return;
            }
        }
    }

    @Override
    public void deleteClient(long id) {
        // Remove the client by ID
        clientsList.removeIf(client -> client.getId() == id);
        saveClientsToFile();
    }

    private List<Client> loadClientsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Client>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions appropriately (e.g., file not found for the first run)
            return new ArrayList<>();
        }
    }

    private void saveClientsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(clientsList);
        } catch (IOException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }
}
