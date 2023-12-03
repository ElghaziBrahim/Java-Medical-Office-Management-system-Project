package Creneau;
import java.sql.SQLException;
import java.util.List;

public interface CreneauxDAOInterface {
    List<Creneau> getAllCreneaux() throws SQLException;

    Creneau getCreneauById(long id) throws SQLException;

    void addCreneau(Creneau creneau) throws SQLException;

    void updateCreneau(Creneau creneau) throws SQLException;

    void deleteCreneau(long id) throws SQLException;
}
