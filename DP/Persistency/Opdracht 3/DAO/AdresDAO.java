package DAO;

import Model.Adres;
import Model.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface AdresDAO
{
    boolean save(Adres adres) throws SQLException;
    boolean update(Adres adres) throws SQLException;
    boolean delete(Adres adres) throws SQLException;
    Adres findByReiziger(Reiziger reiziger) throws SQLException;
    Adres findById(int id) throws SQLException;
    List<Adres> findAll() throws SQLException;
}