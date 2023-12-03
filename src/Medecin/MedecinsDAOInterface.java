package Medecin;
import java.sql.SQLException;
import java.util.List;

public interface MedecinsDAOInterface {
    List<Medecin> getAllMedecins() throws SQLException;

    Medecin getMedecinById(long id) throws SQLException;

    void addMedecin(Medecin medecin) throws SQLException;

    void updateMedecin(Medecin medecin) throws SQLException;

    void deleteMedecin(long id) throws SQLException;
}
