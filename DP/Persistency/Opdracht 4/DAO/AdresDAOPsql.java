package DAO;

import Model.Adres;
import Model.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO
{
    private Connection conn;

    public AdresDAOPsql(Connection conn)
    {
        this.conn = conn;
    }

    @Override
    public boolean save(Adres adres) throws SQLException
    {
        String SQL =
                "INSERT INTO " +
                        "adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id)" +
                        "values (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(SQL);

        statement.setInt(1, adres.getAdres_id());
        statement.setString(2, adres.getPostcode());
        statement.setString(3, adres.getHuisnummer());
        statement.setString(4, adres.getStraat());
        statement.setString(5, adres.getWoonplaats());
        statement.setInt(6, adres.getReiziger_id());

        return statement.executeUpdate() == 1;
    }

    @Override
    public boolean update(Adres adres) throws SQLException
    {
        String SQL =
                "UPDATE adres SET postcode=?, huisnummer=?, straat=?, woonplaats=?, reiziger_id=? WHERE adres_id=?";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setString(1, adres.getPostcode());
        statement.setString(2, adres.getHuisnummer());
        statement.setString(3, adres.getStraat());
        statement.setString(4, adres.getWoonplaats());
        statement.setInt(5, adres.getReiziger_id());
        statement.setInt(6, adres.getAdres_id());

        return statement.executeUpdate() == 1;
    }

    @Override
    public boolean delete(Adres adres) throws SQLException
    {
        String SQL = "DELETE FROM adres WHERE adres_id=?";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, adres.getAdres_id());

        return statement.executeUpdate() == 1;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException
    {
        String SQL = "SELECT * FROM adres WHERE reiziger_id=?;";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, reiziger.getId());
        ResultSet rs = statement.executeQuery();

        if (rs.next())
        {
            return new Adres(rs.getInt("adres_id"), rs.getString("postcode"), rs.getString("huisnummer"),
                    rs.getString("straat"), rs.getString("woonplaats"), rs.getInt("reiziger_id"));
        }

        return null;
    }

    @Override
    public Adres findById(int id) throws SQLException
    {
        String SQL = "SELECT * FROM adres WHERE adres_id=?;";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        if (rs.next())
        {
            return new Adres(rs.getInt("adres_id"), rs.getString("postcode"), rs.getString("huisnummer"),
                    rs.getString("straat"), rs.getString("woonplaats"), rs.getInt("reiziger_id"));
        }

        return null;
    }

    @Override
    public List<Adres> findAll() throws SQLException
    {
        List<Adres> adressen = new ArrayList<>();

        String SQL = "SELECT * FROM adres";
        PreparedStatement statement = conn.prepareStatement(SQL);
        ResultSet rs = statement.executeQuery();

        while (rs.next())
        {
            adressen.add(new Adres(rs.getInt("adres_id"), rs.getString("postcode"), rs.getString("huisnummer"),
                    rs.getString("straat"), rs.getString("woonplaats"), rs.getInt("reiziger_id")));
        }

        return adressen;
    }
}