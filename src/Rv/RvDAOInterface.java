package Rv;
import java.sql.SQLException;
import java.util.List;

public interface RvDAOInterface {
    List<Rv> getAllRvs() throws SQLException;

    Rv getRvById(long id) throws SQLException;

    void addRv(Rv rv) throws SQLException;

    void updateRv(Rv rv) throws SQLException;

    void deleteRv(long id) throws SQLException;
}
