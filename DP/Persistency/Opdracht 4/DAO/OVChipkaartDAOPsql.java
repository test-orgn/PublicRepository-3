package DAO;

import Model.Adres;
import Model.OVChipkaart;
import Model.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO
{
    Connection conn;

    public OVChipkaartDAOPsql(Connection conn)
    {
        this.conn = conn;
    }

    @Override
    public boolean save(OVChipkaart ovkaart) throws SQLException
    {
        String SQL =
                "INSERT INTO " +
                        "ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id)" +
                        "values (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(SQL);

        statement.setInt(1, ovkaart.getKaart_nummer());
        statement.setDate(2,  Date.valueOf(ovkaart.getGeldig_tot()));
        statement.setInt(3, ovkaart.getKlasse());
        statement.setFloat(4, ovkaart.getSaldo());
        statement.setInt(5, ovkaart.getReiziger_id());

        return statement.executeUpdate() == 1;
    }

    @Override
    public boolean update(OVChipkaart ovkaart) throws SQLException
    {
        String SQL =
                "UPDATE ov_chipkaart SET geldig_tot=?, klasse=?, saldo=?, reiziger_id=? WHERE kaart_nummer=?";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setDate(1, Date.valueOf(ovkaart.getGeldig_tot()));
        statement.setInt(2, ovkaart.getKlasse());
        statement.setFloat(3, ovkaart.getSaldo());
        statement.setInt(4, ovkaart.getReiziger_id());
        statement.setInt(5, ovkaart.getKaart_nummer());

        return statement.executeUpdate() == 1;
    }

    @Override
    public boolean delete(OVChipkaart ovkaart) throws SQLException
    {
        String SQL = "DELETE FROM ov_chipkaart WHERE kaart_nummer=?";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, ovkaart.getKaart_nummer());

        return statement.executeUpdate() == 1;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException
    {
        String SQL = "SELECT * FROM ov_chipkaart WHERE reiziger_id=?;";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, reiziger.getId());
        ResultSet rs = statement.executeQuery();
        List<OVChipkaart> ovKaarten = new ArrayList<>();

        while (rs.next())
        {
            ovKaarten.add(new OVChipkaart(rs.getInt("kaart_nummer"), rs.getInt("klasse"), rs.getInt("reiziger_id"),
                    rs.getDate("geldig_tot").toLocalDate(), rs.getFloat("saldo")));
        }

        return ovKaarten;
    }

    @Override
    public OVChipkaart findById(int id) throws SQLException
    {
        String SQL = "SELECT * FROM ov_chipkaart WHERE kaart_nummer=?;";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        if (rs.next())
        {
            return new OVChipkaart(rs.getInt("kaart_nummer"), rs.getInt("klasse"), rs.getInt("reiziger_id"),
                    rs.getDate("geldig_tot").toLocalDate(), rs.getFloat("saldo"));
        }

        return null;
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException
    {
        List<Adres> adressen = new ArrayList<>();

        String SQL = "SELECT * FROM ov_chipkaart";
        PreparedStatement statement = conn.prepareStatement(SQL);
        ResultSet rs = statement.executeQuery();

        List<OVChipkaart> ovKaarten = new ArrayList<>();

        while (rs.next())
        {
            ovKaarten.add(new OVChipkaart(rs.getInt("kaart_nummer"), rs.getInt("klasse"), rs.getInt("reiziger_id"),
                    rs.getDate("geldig_tot").toLocalDate(), rs.getFloat("saldo")));
        }

        return ovKaarten;
    }
}