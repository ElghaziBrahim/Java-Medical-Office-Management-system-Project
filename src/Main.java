import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import Client.*;  
import Creneau.*;  
import Rv.*; 
import Medecin.*; 
import Factory.*;
import Factory.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String storageType = "";
        boolean validStorageType = false;

        while (!validStorageType) {
            System.out.println("Choose storage type (database or serialized): ");
            storageType = scanner.nextLine();

            if ("database".equalsIgnoreCase(storageType) || "serialized".equalsIgnoreCase(storageType)) {
                validStorageType = true;
            } else {
                System.out.println("Invalid storage type. Please enter either 'database' or 'serialized'.");
            }
        }

        

        DAOFactory daoFactory = DAOFactorySelector.getDAOFactory(storageType);

        ClientsDAOInterface clientsDAO = daoFactory.getClientsDAO();
        CreneauxDAOInterface creneauxDAO = daoFactory.getCreneauxDAO();
        RvDAOInterface rvDAO = daoFactory.getRvDAO();
        MedecinsDAOInterface medecinsDAO = daoFactory.getMedecinsDAO();

        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Show data");
            System.out.println("2. Add client");
            System.out.println("3. Add reservation (Rv)");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    showData(clientsDAO, creneauxDAO, rvDAO, medecinsDAO);
                    break;
                case 2:
                    addClient(clientsDAO);
                    break;
                case 3:
                    addReservation(rvDAO,clientsDAO,creneauxDAO);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Program exited. Goodbye!");
    }

    private static void showData(
            ClientsDAOInterface clientsDAO,
            CreneauxDAOInterface creneauxDAO,
            RvDAOInterface rvDAO,
            MedecinsDAOInterface medecinsDAO) {

        List<Client> clients;
		try {
			clients = clientsDAO.getAllClients();
			List<Creneau> creneaux = creneauxDAO.getAllCreneaux();
	        List<Rv> rvs = rvDAO.getAllRvs();
	        List<Medecin> medecins = medecinsDAO.getAllMedecins();
	        System.out.println("Clients:");
	        for (Client client : clients) {
	            System.out.println("Client ID: " + client.getId());
                System.out.println("Name: " + client.getNom() + " " + client.getPrenom());
                System.out.println("---------------");
	        }

	        System.out.println("\nCreneaux:");
	        for (Creneau creneau : creneaux) {
	            System.out.println("Creneau ID: " + creneau.getId());
                System.out.println("Creneau Time Start:  Houre : " + creneau.getHdebut() + " / Minute : " + creneau.getMdebut());
                System.out.println("Creneau Time End:  Houre : " + creneau.getHfin() + " / Minute : " + creneau.getMfin());
                System.out.println("Doctor ID: " + creneau.getId_medecin());
                System.out.println("---------------");
	        }

	        System.out.println("\nReservations (Rv):");
	        for (Rv rv : rvs) {
	            System.out.println("Reservation  ID: " + rv.getId());
                System.out.println("Reservation Time : " + rv.getJour());
                System.out.println("Client Id : " + rv.getId_client());
                System.out.println("---------------");
	        }

	        System.out.println("\nMedecins:");
	        for (Medecin medecin : medecins) {
	        	 System.out.println("Client ID: " + medecin.getId());
	             System.out.println("Name: " + medecin.getNom() + " " + medecin.getPrenom());
	             System.out.println("---------------");
	            
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        

        
    }

    private static void addClient(ClientsDAOInterface clientsDAO) {
    	Scanner scanner = new Scanner(System.in);
    	try {
    		System.out.println("Enter client first name: ");
    		String firstName = scanner.nextLine();

    		System.out.println("Enter client last name: ");
    		String lastName = scanner.nextLine();

    		System.out.println("Enter client version: ");
    		int version = scanner.nextInt();
    		scanner.nextLine(); // Consume the newline character

    		System.out.println("Enter client Title: ");
    		String title = scanner.nextLine();

    		Client newClient = new Client(version, title, lastName, firstName);

    		clientsDAO.addClient(newClient);
    		System.out.println("Client added successfully!");
    	} catch (SQLException e) {
    		System.out.println("Error adding client: " + e.getMessage());
    	}
    }

    private static void addReservation(RvDAOInterface rvDAO, ClientsDAOInterface clientsDAO, CreneauxDAOInterface creneauxDAO) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter client ID for the reservation: ");
            long clientId = scanner.nextLong();
            scanner.nextLine();

            if (clientsDAO.getClientById(clientId) == null) {
                System.out.println("Invalid client ID. Reservation cannot be added.");
                return;
            }

            System.out.println("Enter creneau ID for the reservation: ");
            long creneauId = scanner.nextLong();
            scanner.nextLine(); 

            if (creneauxDAO.getCreneauById(creneauId) == null) {
                System.out.println("Invalid creneau ID. Reservation cannot be added.");
                return;
            }

            System.out.println("Enter reservation date (in yyyy-MM-dd format): ");
            String dateString = scanner.nextLine();
            java.util.Date date = java.sql.Date.valueOf(dateString);

            Rv newReservation = new Rv(date, clientId, creneauId);

            rvDAO.addRv(newReservation);
            System.out.println("Reservation added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding reservation: " + e.getMessage());
        }
    }

}
