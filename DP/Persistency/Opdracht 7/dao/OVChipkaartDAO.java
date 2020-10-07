package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domain.OvEntity;
import nl.hu.dp.ovchip.domain.ReizigerEntity;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO
{
    boolean save(OvEntity ovkaart) throws SQLException;
    boolean update(OvEntity ovkaart) throws SQLException;
    boolean delete(OvEntity ovkaart) throws SQLException;
    OvEntity findById(int id) throws SQLException;
    List<OvEntity> findAll() throws SQLException;
    List<OvEntity> findByReiziger(ReizigerEntity reiziger) throws SQLException;
}