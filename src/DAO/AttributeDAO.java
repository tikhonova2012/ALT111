package DAO;

import Entity.Attributes;

import java.sql.SQLException;
import java.util.HashMap;

public interface AttributeDAO extends DAO<Attributes> {
    HashMap<String, String> getAll() throws SQLException;
}
