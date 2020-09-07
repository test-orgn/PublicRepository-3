package DAO;

import Model.OVChipkaart;
import Model.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO
{
    private Connection conn;
    private AdresDAO adao;
    private OVChipkaartDAO odao;

    public ReizigerDAOPsql(Connection conn)
    {
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException
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
        statement.setDate(5, Date.valueOf(reiziger.getGeboortedatum()));

        boolean result = (statement.executeUpdate() == 1);

        if (reiziger.getAdres() != null)
        {
            result = result && adao.save(reiziger.getAdres());
        }

        for(OVChipkaart ovKaart : reiziger.getOvKaarten())
        {
            result = result && odao.save(ovKaart);
        }

        return result;
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException
    {
        String SQL = "UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=? WHERE reiziger_id=?";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setString(1, reiziger.getVoorletters());
        statement.setString(2, reiziger.getTussenvoegsel());
        statement.setString(3, reiziger.getAchternaam());
        statement.setDate(4, Date.valueOf(reiziger.getGeboortedatum()));
        statement.setInt(5, reiziger.getId());

        boolean result = (statement.executeUpdate() == 1);

        if (reiziger.getAdres() != null)
        {
            result = result && adao.update(reiziger.getAdres());
        }

        for(OVChipkaart ovKaart : reiziger.getOvKaarten())
        {
            result = result && odao.update(ovKaart);
        }

        return result;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException
    {
        String SQL = "DELETE FROM reiziger WHERE reiziger_id=?";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, reiziger.getId());

        boolean result = (statement.executeUpdate() == 1);

        if (reiziger.getAdres() != null)
        {
            result = result && adao.delete(reiziger.getAdres());
        }

        for(OVChipkaart ovKaart : reiziger.getOvKaarten())
        {
            result = result && odao.delete(ovKaart);
        }

        return result;
    }

    @Override
    public Reiziger findByID(int id) throws SQLException
    {
        String SQL = "SELECT * FROM reiziger WHERE reiziger_id=?;";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        if (rs.next())
        {
            Reiziger resultReiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                    rs.getString("achternaam"), rs.getDate("geboortedatum").toLocalDate());
            resultReiziger.setAdres(adao.findByReiziger(resultReiziger));
            for(OVChipkaart ovKaart : odao.findByReiziger(resultReiziger))
            {
                ovKaart.setReiziger(resultReiziger);
                resultReiziger.addOvKaart(ovKaart);
            }
            return resultReiziger;
        }

        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String date) throws SQLException
    {
        List<Reiziger> reizigers = new ArrayList<>();

        String SQL = "SELECT * FROM reiziger WHERE geboortedatum=?;";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setDate(1, java.sql.Date.valueOf(date));
        ResultSet rs = statement.executeQuery();

        while (rs.next())
        {
            Reiziger resultReiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                    rs.getString("achternaam"), rs.getDate("geboortedatum").toLocalDate());
            resultReiziger.setAdres(adao.findByReiziger(resultReiziger));
            for(OVChipkaart ovKaart : odao.findByReiziger(resultReiziger))
            {
                ovKaart.setReiziger(resultReiziger);
                resultReiziger.addOvKaart(ovKaart);
            }
            reizigers.add(resultReiziger);
        }

        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException
    {
        List<Reiziger> reizigers = new ArrayList<>();

        String SQL = "SELECT * FROM reiziger";
        PreparedStatement statement = conn.prepareStatement(SQL);
        ResultSet rs = statement.executeQuery();

        while (rs.next())
        {
            Reiziger resultReiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                    rs.getString("achternaam"), rs.getDate("geboortedatum").toLocalDate());
            resultReiziger.setAdres(adao.findByReiziger(resultReiziger));
            for(OVChipkaart ovKaart : odao.findByReiziger(resultReiziger))
            {
                ovKaart.setReiziger(resultReiziger);
                resultReiziger.addOvKaart(ovKaart);
            }
            reizigers.add(resultReiziger);
        }

        return reizigers;
    }

    public void setAdao(AdresDAO adao)
    {
        this.adao = adao;
    }

    public void setOdao(OVChipkaartDAO odao)
    {
        this.odao = odao;
    }
}