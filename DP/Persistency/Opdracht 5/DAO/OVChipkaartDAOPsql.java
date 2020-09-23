package DAO;

import Model.Adres;
import Model.OVChipkaart;
import Model.Product;
import Model.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO
{
    private Connection conn;
    private ReizigerDAO rdao;
    private ProductDAO pdao;

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

        boolean result = (statement.executeUpdate() == 1);

        for(Product product : ovkaart.getProducten())
        {
            result = result && pdao.save(product);
        }

        return result;
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

        boolean result = (statement.executeUpdate() == 1);

        for(Product product : ovkaart.getProducten())
        {
            result = result && pdao.update(product);
        }

        return result;
    }

    @Override
    public boolean delete(OVChipkaart ovkaart) throws SQLException
    {
        String SQL = "DELETE FROM ov_chipkaart WHERE kaart_nummer=?";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, ovkaart.getKaart_nummer());

        boolean result = (statement.executeUpdate() == 1);

        for(Product product : ovkaart.getProducten())
        {
            result = result && pdao.delete(product);
        }

        return result;
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
            OVChipkaart ovKaart = new OVChipkaart(rs.getInt("kaart_nummer"), rs.getInt("klasse"), rs.getInt("reiziger_id"),
                    rs.getDate("geldig_tot").toLocalDate(), rs.getFloat("saldo"));
            ovKaart.setReiziger(reiziger);

            for(Product product : pdao.findByOVChipkaart(ovKaart))
            {
                ovKaart.addProduct(product);
            }

            ovKaarten.add(ovKaart);
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
            OVChipkaart ovKaart =  new OVChipkaart(rs.getInt("kaart_nummer"), rs.getInt("klasse"), rs.getInt("reiziger_id"),
                    rs.getDate("geldig_tot").toLocalDate(), rs.getFloat("saldo"));
            ovKaart.setReiziger(rdao.findByID(ovKaart.getReiziger_id()));

            for(Product product : pdao.findByOVChipkaart(ovKaart))
            {
                ovKaart.addProduct(product);
            }

            return ovKaart;
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
            OVChipkaart ovKaart = new OVChipkaart(rs.getInt("kaart_nummer"), rs.getInt("klasse"), rs.getInt("reiziger_id"),
                    rs.getDate("geldig_tot").toLocalDate(), rs.getFloat("saldo"));
            ovKaart.setReiziger(rdao.findByID(ovKaart.getReiziger_id()));

            for(Product product : pdao.findByOVChipkaart(ovKaart))
            {
                ovKaart.addProduct(product);
            }

            ovKaarten.add(ovKaart);
        }

        return ovKaarten;
    }

    @Override
    public List<OVChipkaart> findByProduct(Product product) throws SQLException
    {
        String SQL =
                "SELECT * FROM ov_chipkaart_product AS ocp JOIN ov_chipkaart ON ov_chipkaart.kaart_nummer=ocp.kaart_nummer WHERE product_nummer=?;";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, product.getProduct_nummer());
        ResultSet rs = statement.executeQuery();
        List<OVChipkaart> ovKaarten = new ArrayList<>();

        while (rs.next())
        {
            OVChipkaart ovKaart = new OVChipkaart(rs.getInt("kaart_nummer"), rs.getInt("klasse"), rs.getInt("reiziger_id"),
                    rs.getDate("geldig_tot").toLocalDate(), rs.getFloat("saldo"));
            ovKaart.setReiziger(rdao.findByID(ovKaart.getReiziger_id()));
            ovKaart.addProduct(product);
            ovKaarten.add(ovKaart);
        }

        return ovKaarten;
    }

    public void setRdao(ReizigerDAO rdao)
    {
        this.rdao = rdao;
    }

    public void setPdao(ProductDAO pdao)
    {
        this.pdao = pdao;
    }
}