package DAO.impl;

import DAO.ProductDAO;
import Entity.Product;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOimpl implements ProductDAO {

    private static volatile ProductDAO INSTANCE = null;
    private static final String getAllProdQuery = "SELECT Tot_profile.ID, PRODUCT.Art, COLOR.NAME, NAME.Name, UNIT_MEASURE.Name, PRODUCT.VALUE from NAME, UNIT_MEASURE, PRODUCT\n" +
            "                     JOIN Tot_profile on PRODUCT.ID=Tot_profile.ART_ID join COLOR on Tot_profile.COLOR_ID=COLOR.ID\n" +
            "                                                  WHERE NAME_ID=NAME.ID && UNIT_MEASURE_ID=UNIT_MEASURE.ID && COLOR.NAME='00'";

    private static final String getProdbyColorQuery = "SELECT Tot_profile.ID, PRODUCT.Art, COLOR.NAME, NAME.Name, UNIT_MEASURE.Name, PRODUCT.VALUE from NAME, UNIT_MEASURE, PRODUCT\n" +
            "                     JOIN Tot_profile on PRODUCT.ID=Tot_profile.ART_ID join COLOR on Tot_profile.COLOR_ID=COLOR.ID\n" +
            "                                                  WHERE NAME_ID=NAME.ID && UNIT_MEASURE_ID=UNIT_MEASURE.ID && COLOR.NAME=?";
    Connection cn=ConnectionManager.getConnection();
    private PreparedStatement psGetAllProd;
    private PreparedStatement psGetByColorProd;

    {
        try {
            psGetAllProd = cn.prepareStatement(getAllProdQuery);
            psGetByColorProd=cn.prepareStatement(getProdbyColorQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProductDAO getInstance() {
        ProductDAO prodDao = INSTANCE;
        if (prodDao == null) {
            synchronized (ProductDAOimpl.class) {
                prodDao = INSTANCE;
                if (prodDao == null) {
                    INSTANCE = prodDao = new ProductDAOimpl();
                }
            }
        }

        return prodDao;
    }

    @Override
    public Product save(Product product) throws SQLException {
        return null;
    }

    @Override
    public Product get(Long id) throws SQLException {
        return null;
    }

    @Override
    public void update(Product product) throws SQLException {

    }

    @Override
    public int delete(Long id) throws SQLException {
        return 0;
    }



    @Override
    public List<Product> getAll() throws SQLException {
        psGetAllProd.execute();
        ResultSet rs = psGetAllProd.getResultSet();
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product= new Product();
            product.setId(rs.getInt(1));
            product.setArt(rs.getString(2));
            product.setName(rs.getString(3));
            product.setUm(rs.getString(4));
            product.setColor(rs.getString(5));
            product.setValue(rs.getDouble(6));
            list.add(product);
        }
        rs.close();
        return list;
    }

    @Override
    public List<Product> getByColor(String color) throws SQLException {
        psGetByColorProd.setString(1,color);
        psGetByColorProd.execute();
        ResultSet rs = psGetByColorProd.getResultSet();
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product= new Product();
            product.setId(rs.getInt(1));
            product.setArt(rs.getString(2));
            product.setName(rs.getString(3));
            product.setUm(rs.getString(4));
            product.setColor(rs.getString(5));
            product.setValue(rs.getDouble(6));
            list.add(product);
        }
        rs.close();
        return list;
    }
}
