package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domain.AdresEntity;
import nl.hu.dp.ovchip.domain.ReizigerEntity;

import java.sql.SQLException;
import java.util.List;

public interface AdresDAO
{
    boolean save(AdresEntity adres) throws SQLException;
    boolean update(AdresEntity adres) throws SQLException;
    boolean delete(AdresEntity adres) throws SQLException;
    AdresEntity findByReiziger(ReizigerEntity reiziger) throws SQLException;
    AdresEntity findById(int id) throws SQLException;
    List<AdresEntity> findAll() throws SQLException;
}