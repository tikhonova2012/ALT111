package DAO;

import java.sql.SQLException;

public interface DAO<T> {
    T save(T t) throws SQLException;
    T get(Long id) throws SQLException;
    void update(T t) throws SQLException;
    int delete(Long id) throws SQLException;
}
