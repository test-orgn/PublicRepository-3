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
        String SQL =
                "INSERT INTO " +
                        "product (product_nummer, naam, beschrijving, prijs)" +
                        "VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, product.getProduct_nummer());
        statement.setString(2, product.getNaam());
        statement.setString(3, product.getBeschrijving());
        statement.setFloat(4, product.getPrijs());

        boolean result = (statement.executeUpdate() == 1);

        for(OVChipkaart ovKaart : product.getOvKaarten())
        {
            result = result && odao.save(ovKaart);
        }

        return result;
    }

    @Override
    public boolean update(Product product) throws SQLException
    {
        String SQL =
                "UPDATE product SET naam=?, beschrijving=?, prijs=? WHERE product_nummer=?";
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setString(1, product.getNaam());
        statement.setString(2, product.getBeschrijving());
        statement.setFloat(3, product.getPrijs());
        statement.setInt(4, product.getProduct_nummer());

        boolean result = (statement.executeUpdate() == 1);

        for(OVChipkaart ovKaart : product.getOvKaarten())
        {
            result = result && odao.update(ovKaart);
        }

        return result;
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
            result = result && odao.delete(ovKaart);
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
        String SQL =
                "SELECT * FROM product";
        PreparedStatement statement = conn.prepareStatement(SQL);
        ResultSet rs = statement.executeQuery();
        List<Product> producten = new ArrayList<>();

        while (rs.next())
        {
            Product product = new Product(rs.getInt("product_nummer"), rs.getString("naam"), rs.getString("beschrijving"), rs.getFloat("prijs"));

            for(OVChipkaart ovKaart : odao.findAll())
            {
                for(Product p : ovKaart.getProducten())
                {
                    if(p.getProduct_nummer() == product.getProduct_nummer())
                    {
                        product.addOvKaart(ovKaart);
                    }
                }
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
            for(OVChipkaart ovKaart : odao.findByProduct(product))
            {
                product.addOvKaart(ovKaart);
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