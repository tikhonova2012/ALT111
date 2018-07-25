package DAO;

import Entity.Complectation;
import java.sql.SQLException;
import java.util.List;

public interface ComplectationDAO {
    List<Complectation> getAll() throws SQLException;
    List<Complectation> getByColor(String color) throws SQLException;
}
