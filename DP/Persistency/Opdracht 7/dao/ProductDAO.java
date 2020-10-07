package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domain.OvEntity;
import nl.hu.dp.ovchip.domain.ProductEntity;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO
{
    boolean save(ProductEntity product) throws SQLException;
    boolean update(ProductEntity product) throws SQLException;
    boolean delete(ProductEntity product) throws SQLException;
    List<ProductEntity> findByOVChipkaart(OvEntity ovKaart) throws SQLException;
    List<ProductEntity> findAll() throws SQLException;
    ProductEntity findbyId(int id) throws SQLException;
}