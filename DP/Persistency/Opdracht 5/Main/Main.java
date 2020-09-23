package Main;

import DAO.*;
import Model.Product;

import java.sql.*;
import java.util.Properties;

public class Main
{
    private static Connection connection;

    public static void main(String[] args)
    {
        try
        {
            // Connection details
            String url = "jdbc:postgresql://localhost/ovchip";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "kip123");
            props.setProperty("ssl", "false");

            // Create connection
            connection = DriverManager.getConnection(url, props);

            // Classes for SQL stuff
            ReizigerDAOPsql rdao = new ReizigerDAOPsql(connection);
            AdresDAOPsql adao = new AdresDAOPsql(connection);
            OVChipkaartDAOPsql odao = new OVChipkaartDAOPsql(connection);
            ProductDAOPsql pdao = new ProductDAOPsql(connection);
            rdao.setAdao(adao);
            rdao.setOdao(odao);
            odao.setRdao(rdao);
            odao.setPdao(pdao);
            pdao.setOdao(odao);

            // Traveller testing
            testProductDAO(pdao);

            // Close connection
            closeConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void testProductDAO(ProductDAO pdao) throws SQLException {
        System.out.println("\n---------- Test ProductDAO -------------");

        // Read products
        System.out.println("[READ] ProductDAO.findAll() geeft de volgende producten:");
        for (Product p : pdao.findAll()) {
            System.out.println(p);
        }
        System.out.println();

        // Create products
        Product p = new Product(123, "test", "dit is een test", 420.0f);
        System.out.print("[CREATE] Eerst " + pdao.findAll().size() + " producten, NA create ");
        pdao.save(p);
        System.out.println(pdao.findAll().size() + " producten");

        // Update products
        System.out.print("[UPDATE] Product naam VOOR update: " + pdao.findbyId(p.getProduct_nummer()).getNaam());
        p.setNaam("andere_test");
        pdao.update(p);
        System.out.println(", NA update: " + pdao.findbyId(p.getProduct_nummer()).getNaam());

        // Delete products
        System.out.print("[DELETE] Eerst " + pdao.findAll().size() + " producten, NA delete ");
        pdao.delete(p);
        System.out.println(pdao.findAll().size() + " producten");
    }

    private static Connection getConnection()
    {
        return connection;
    }

    private static void closeConnection() throws SQLException
    {
        connection.close();
    }
}