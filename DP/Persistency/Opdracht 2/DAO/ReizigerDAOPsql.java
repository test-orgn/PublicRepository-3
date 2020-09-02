package DAO;

import Model.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO
{
    private Connection conn;

    public ReizigerDAOPsql(Connection conn)
    {
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger)
    {
        try
        {
            String SQL =
                    "INSERT INTO " +
                            "reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum)" +
                            "values (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(SQL);

            statement.setInt(1, reiziger.getId());
            statement.setString(2, reiziger.getVoorletters());
            statement.setString(3, reiziger.getTussenvoegsel());
            statement.setString(4, reiziger.getAchternaam());
            statement.setDate(5, reiziger.getGeboortedatum());
            statement.executeUpdate();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Reiziger reiziger)
    {
        try
        {
            String SQL = "UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=? WHERE reiziger_id=?";
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setString(1, reiziger.getVoorletters());
            statement.setString(2, reiziger.getTussenvoegsel());
            statement.setString(3, reiziger.getAchternaam());
            statement.setDate(4, reiziger.getGeboortedatum());
            statement.setInt(5, reiziger.getId());
            statement.executeUpdate();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger)
    {
        try
        {
            String SQL = "DELETE FROM reiziger WHERE reiziger_id=?";
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setInt(1, reiziger.getId());
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Reiziger findByID(int id)
    {
        try
        {
            List<Reiziger> reizigers = new ArrayList<>();

            String SQL = "SELECT * FROM reiziger WHERE reiziger_id=?;";
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next())
            {
                return new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"), rs.getDate("geboortedatum"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String date)
    {
        try
        {
            List<Reiziger> reizigers = new ArrayList<>();

            String SQL = "SELECT * FROM reiziger WHERE geboortedatum=?;";
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                reizigers.add(new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"), rs.getDate("geboortedatum")));
            }

            return reizigers;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Reiziger> findAll()
    {
        try
        {
            List<Reiziger> reizigers = new ArrayList<>();

            String SQL = "SELECT * FROM reiziger";
            PreparedStatement statement = conn.prepareStatement(SQL);
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                reizigers.add(new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"), rs.getDate("geboortedatum")));
            }

            return reizigers;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}