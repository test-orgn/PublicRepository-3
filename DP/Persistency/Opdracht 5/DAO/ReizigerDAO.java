package DAO;

import Model.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO
{
    boolean save(Reiziger reiziger) throws SQLException;
    boolean update(Reiziger reiziger) throws SQLException;
    boolean delete(Reiziger reiziger) throws SQLException;
    Reiziger findByID(int id) throws SQLException;
    List<Reiziger> findByGbdatum(String date) throws SQLException;
    List<Reiziger> findAll() throws SQLException;
}