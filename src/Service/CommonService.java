package Service;

import db.ConnectionManager;
import db.DBManagerException;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class CommonService {

    public void startTransaction() throws SQLException {
        ConnectionManager.getConnection().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        ConnectionManager.getConnection().commit();
    }

    public Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    public void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            throw new DBManagerException("что-то пошло не так на этапе соединения с базой");
        }
    }
}
