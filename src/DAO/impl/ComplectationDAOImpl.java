package DAO.impl;

import DAO.ComplectationDAO;
import Entity.Complectation;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplectationDAOImpl implements ComplectationDAO {

    private static volatile ComplectationDAO INSTANCE = null;
    private static final String getAllComplQuery = "SELECT Tot_compl.ID, complectation.Art, COLOR.NAME, NAME.Name, UNIT_MEASURE.Name, complectation.VALUE from NAME, UNIT_MEASURE, complectation\n" +
            "  JOIN Tot_compl on COMPLECTATION.ID=Tot_compl.ART_ID join COLOR on Tot_compl.COLOR_ID=COLOR.ID\n" +
            "WHERE NAME_ID=NAME.ID && UNIT_MEASURE_ID=UNIT_MEASURE.ID&& COLOR.NAME='-'";

    private static final String getComplbyColorQuery = "SELECT Tot_compl.ID, complectation.Art, COLOR.NAME, NAME.Name, UNIT_MEASURE.Name, complectation.VALUE from NAME, UNIT_MEASURE, complectation\n" +
            "  JOIN Tot_compl on COMPLECTATION.ID=Tot_compl.ART_ID join COLOR on Tot_compl.COLOR_ID=COLOR.ID\n" +
            "WHERE NAME_ID=NAME.ID && UNIT_MEASURE_ID=UNIT_MEASURE.ID&& (COLOR.NAME='00'||COLOR.NAME=?)";
    Connection cn=ConnectionManager.getConnection();
    private PreparedStatement psGetAllCompl;
    private PreparedStatement psGetByColorCompl;

    {
        try {
            psGetAllCompl = cn.prepareStatement(getAllComplQuery);
            psGetByColorCompl=cn.prepareStatement(getComplbyColorQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ComplectationDAO getInstance() {
        ComplectationDAO complDao = INSTANCE;
        if (complDao == null) {
            synchronized (ComplectationDAOImpl.class) {
                complDao = INSTANCE;
                if (complDao == null) {
                    INSTANCE = complDao = new ComplectationDAOImpl();
                }
            }
        }

        return complDao;
    }
    @Override
    public List<Complectation> getAll() throws SQLException {
        psGetAllCompl.execute();
        ResultSet rs = psGetAllCompl.getResultSet();
        List<Complectation> list = new ArrayList<>();
        while (rs.next()) {
            Complectation complectation= new Complectation();
            complectation.setId(rs.getInt(1));
            complectation.setArt(rs.getString(2));
            complectation.setName(rs.getString(3));
            complectation.setUm(rs.getString(4));
            complectation.setColor(rs.getString(5));
            complectation.setValue(rs.getDouble(6));
            list.add(complectation);
        }
        rs.close();
        return list;
    }

    @Override
    public List<Complectation> getByColor(String color) throws SQLException {
        psGetByColorCompl.setString(1,color);
        psGetByColorCompl.execute();
        ResultSet rs = psGetByColorCompl.getResultSet();
        List<Complectation> list = new ArrayList<>();
        while (rs.next()) {
            Complectation complectation= new Complectation();
            complectation.setId(rs.getInt(1));
            complectation.setArt(rs.getString(2));
            complectation.setName(rs.getString(3));
            complectation.setUm(rs.getString(4));
            complectation.setColor(rs.getString(5));
            complectation.setValue(rs.getDouble(6));
            list.add(complectation);
        }
        rs.close();
        return list;
    }
}
