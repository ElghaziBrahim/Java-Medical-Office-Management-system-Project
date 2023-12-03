package Factory;
import Client.ClientsDAOInterface;
import Creneau.CreneauxDAOInterface;
import Medecin.MedecinsDAOInterface;
import Rv.RvDAOInterface;

public interface DAOFactory {
    ClientsDAOInterface getClientsDAO();
    MedecinsDAOInterface getMedecinsDAO();
    CreneauxDAOInterface getCreneauxDAO();
    RvDAOInterface getRvDAO();
}