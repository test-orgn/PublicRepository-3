package DAO;

import Model.OVChipkaart;
import Model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO
{
    boolean save(Product product) throws SQLException;
    boolean update(Product product) throws SQLException;
    boolean delete(Product product) throws SQLException;
    List<Product> findByOVChipkaart(OVChipkaart ovKaart) throws SQLException;
    List<Product> findAll() throws SQLException;
    Product findbyId(int id) throws SQLException;
}