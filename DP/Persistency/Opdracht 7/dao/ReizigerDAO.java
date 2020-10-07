package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domain.ReizigerEntity;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO
{
    boolean save(ReizigerEntity reiziger) throws SQLException;
    boolean update(ReizigerEntity reiziger) throws SQLException;
    boolean delete(ReizigerEntity reiziger) throws SQLException;
    ReizigerEntity findByID(int id) throws SQLException;
    List<ReizigerEntity> findByGbdatum(String date) throws SQLException;
    List<ReizigerEntity> findAll() throws SQLException;
}