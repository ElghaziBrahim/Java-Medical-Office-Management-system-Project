package Factory;

//import Client.ClientsDAOInterface;
//import Creneau.CreneauxDAOInterface;
//import Medecin.MedecinsDAOInterface;
//import Rv.RvDAOInterface;

import Client.ClientsDAOImplSer;
import Creneau.CreneauxDAOImplSer;
import Medecin.MedecinsDAOImplSer;
import Rv.RvDAOImplSer;

public class SerializedDAOFactory implements DAOFactory {
    @Override
    public ClientsDAOImplSer getClientsDAO() {
        return new ClientsDAOImplSer();
    }

    @Override
    public MedecinsDAOImplSer getMedecinsDAO() {
        return new MedecinsDAOImplSer();
    }

    @Override
    public CreneauxDAOImplSer getCreneauxDAO() {
        return new CreneauxDAOImplSer();
    }

    @Override
    public RvDAOImplSer getRvDAO() {
        return new RvDAOImplSer();
    }
}


