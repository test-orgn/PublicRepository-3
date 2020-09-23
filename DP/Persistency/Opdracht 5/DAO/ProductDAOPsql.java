package DAO;

import Model.OVChipkaart;
import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPsql implements ProductDAO
{
    private Connection conn;
    private OVChipkaartDAO odao;

    public ProductDAOPsql(Connection conn)
    {
        this.conn = conn;
    }

    @Override
    public boolean save(Product product) throws SQLException
    {
        String SQL = "INSERT INTO product (product_nummer, naam, beschrijving, prijs) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, product.getProduct_nummer());
        statement.setString(2, product.getNaam());
        statement.setString(3, product.getBeschrijving());
        statement.setFloat(4, product.getPrijs());

        boolean result = (statement.executeUpdate() == 1);

        for(OVChipkaart ovKaart : product.getOvKaarten())
        {
            String ovSQL = "INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer) VALUES (?, ?)";
            PreparedStatement ovStatement = conn.prepareStatement(ovSQL);
            ovStatement.setInt(1, ovKaart.getKaart_nummer());
            ovStatement.setInt(2, product.getProduct_nummer());

            result = result && (ovStatement.executeUpdate() == 1);
        }

        return result;
    }

    @Override
    public boolean update(Product product) throws SQLException
    {
        return delete(product) && save(product);
    }

    @Override
    public boolean delete(Product product) throws SQLException
    {
        String SQL =
                "DELETE FROM product WHERE product_nummer=?";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, product.getProduct_nummer());

        boolean result = (statement.executeUpdate() == 1);

        for(OVChipkaart ovKaart : product.getOvKaarten())
        {
            String ovSQL = "DELETE FROM ov_chipkaart_product WHERE kaart_nummer=? AND product_nummer=?";
            PreparedStatement ovStatement = conn.prepareStatement(ovSQL);
            ovStatement.setInt(1, ovKaart.getKaart_nummer());
            ovStatement.setInt(2, product.getProduct_nummer());

            result = result && (ovStatement.executeUpdate() == 1);
        }

        return result;
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovKaart) throws SQLException
    {
        String SQL =
                "SELECT * FROM ov_chipkaart_product AS ocp JOIN product ON product.product_nummer=ocp.product_nummer WHERE kaart_nummer=?;";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, ovKaart.getKaart_nummer());
        ResultSet rs = statement.executeQuery();
        List<Product> producten = new ArrayList<>();

        while (rs.next())
        {
            Product product = new Product(rs.getInt("product_nummer"), rs.getString("naam"), rs.getString("beschrijving"), rs.getFloat("prijs"));
            product.addOvKaart(ovKaart);
            producten.add(product);
        }

        return producten;
    }

    @Override
    public List<Product> findAll() throws SQLException
    {
        String SQL = "SELECT * FROM product";
        PreparedStatement statement = conn.prepareStatement(SQL);
        ResultSet rs = statement.executeQuery();
        List<Product> producten = new ArrayList<>();

        while (rs.next())
        {
            Product product = new Product(rs.getInt("product_nummer"), rs.getString("naam"), rs.getString("beschrijving"), rs.getFloat("prijs"));

            String ovSQL =
                    "SELECT * FROM ov_chipkaart_product WHERE product_nummer=?;";
            PreparedStatement ovStatement = conn.prepareStatement(ovSQL);
            ovStatement.setInt(1, product.getProduct_nummer());
            ResultSet ovRS = ovStatement.executeQuery();
            while(ovRS.next())
            {
                product.addOvKaart(odao.findById(ovRS.getInt("kaart_nummer")));
            }

            producten.add(product);
        }

        return producten;
    }

    @Override
    public Product findbyId(int id) throws SQLException
    {
        String SQL = "SELECT * FROM product WHERE product_nummer=?;";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        if (rs.next())
        {
            Product product = new Product(rs.getInt("product_nummer"), rs.getString("naam"), rs.getString("beschrijving"), rs.getFloat("prijs"));

            String ovSQL =
                    "SELECT * FROM ov_chipkaart_product WHERE product_nummer=?;";
            PreparedStatement ovStatement = conn.prepareStatement(ovSQL);
            ovStatement.setInt(1, id);
            ResultSet ovRS = ovStatement.executeQuery();
            while(rs.next())
            {
                product.addOvKaart(odao.findById(rs.getInt("kaart_nummer")));
            }

            return product;
        }

        return null;
    }

    public void setOdao(OVChipkaartDAO odao)
    {
        this.odao = odao;
    }
}