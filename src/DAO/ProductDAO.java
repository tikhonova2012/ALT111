package DAO;


import Entity.Product;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends DAO<Product> {
    List<Product> getAll() throws SQLException;
    List<Product> getByColor(String color) throws SQLException;
}
