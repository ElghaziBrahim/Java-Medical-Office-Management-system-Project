package Factory;

import Client.ClientsDAOImpl;
import Creneau.CreneauxDAOImpl;
import Medecin.MedecinsDAOImpl;
import Rv.RvDAOImpl;

public class DatabaseDAOFactory implements DAOFactory {
    @Override
    public ClientsDAOImpl getClientsDAO() {
        return new ClientsDAOImpl();
    }

    @Override
    public MedecinsDAOImpl getMedecinsDAO() {
        return new MedecinsDAOImpl();
    }

    @Override
    public CreneauxDAOImpl getCreneauxDAO() {
        return new CreneauxDAOImpl();
    }

    @Override
    public RvDAOImpl getRvDAO() {
        return new RvDAOImpl();
    }
}
