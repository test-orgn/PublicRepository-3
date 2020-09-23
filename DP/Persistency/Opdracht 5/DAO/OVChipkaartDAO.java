package DAO;

import Model.Adres;
import Model.OVChipkaart;
import Model.Product;
import Model.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO
{
    boolean save(OVChipkaart ovkaart) throws SQLException;
    boolean update(OVChipkaart ovkaart) throws SQLException;
    boolean delete(OVChipkaart ovkaart) throws SQLException;
    OVChipkaart findById(int id) throws SQLException;
    List<OVChipkaart> findAll() throws SQLException;
    List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
    List<OVChipkaart> findByProduct(Product product) throws SQLException;
}