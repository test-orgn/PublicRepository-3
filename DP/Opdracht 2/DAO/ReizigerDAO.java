package DAO;

import Model.Reiziger;

import java.util.List;

public interface ReizigerDAO
{
    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findByID(int id);
    List<Reiziger> findByGbdatum(String date);
    List<Reiziger> findAll();
}