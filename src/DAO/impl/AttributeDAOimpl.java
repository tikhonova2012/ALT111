package DAO.impl;

import DAO.AttributeDAO;
import Entity.Attributes;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttributeDAOimpl implements AttributeDAO {
    private static volatile AttributeDAO INSTANCE = null;
    private static final String getAllAttrQuery = "SELECT ID, NAME FROM ATTRIBUTES";

    Connection cn=ConnectionManager.getConnection();
    private PreparedStatement psGetAll;

    {
        try {
            psGetAll = cn.prepareStatement(getAllAttrQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static AttributeDAO getInstance() {
        AttributeDAO attrDao = INSTANCE;
        if (attrDao == null) {
            synchronized (AttributeDAOimpl.class) {
               attrDao = INSTANCE;
                if (attrDao == null) {
                    INSTANCE =attrDao = new AttributeDAOimpl();
                }
            }
        }

        return attrDao;
    }

    @Override
    public Attributes save(Attributes attributes) throws SQLException {
        return null;
    }

    @Override
    public Attributes get(Long id) throws SQLException {
        return null;
    }

    @Override
    public void update(Attributes attributes) throws SQLException {

    }

    @Override
    public int delete(Long id) throws SQLException {
        return 0;
    }

    @Override
    public HashMap<String, String> getAll() throws SQLException {
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();

        HashMap<String,String> map=new HashMap<String, String>();
        while (rs.next()) {
            Attributes attribute= new Attributes();
            attribute.setId(rs.getInt(1));
            attribute.setName(rs.getString(2));
            map.put(attribute.getName(),"0");
        }
        rs.close();
        return map;
    }


}
