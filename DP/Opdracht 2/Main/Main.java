package Main;

import DAO.ReizigerDAO;
import DAO.ReizigerDAOPsql;
import Model.Reiziger;

import java.sql.*;
import java.util.Properties;

public class Main
{
    private static Connection connection;

    public static void main(String args[])
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

            // Execute SQL stuff
            ReizigerDAOPsql dao = new ReizigerDAOPsql(connection);
            testReizigerDAO(dao);

            // Close connection
            closeConnection();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Read travellers
        System.out.println("[READ] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : rdao.findAll()) {
            System.out.println(r);
        }
        System.out.println();

        // Create traveller
        String gbdatum = "1981-03-14";
        Reiziger s = new Reiziger(78, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[CREATE] Eerst " + rdao.findAll().size() + " reizigers, NA create ");
        rdao.save(s);
        System.out.println(rdao.findAll().size() + " reizigers");

        // Update traveller
        System.out.print("[UPDATE] Reiziger achternaam VOOR update: " + rdao.findByID(s.getId()).getAchternaam());
        s.setAchternaam("Jansen");
        rdao.update(s);
        System.out.println(", NA update: " + rdao.findByID(s.getId()).getAchternaam());

        // Delete traveller
        System.out.print("[DELETE] Eerst " + rdao.findAll().size() + " reizigers, NA delete ");
        rdao.delete(s);
        System.out.println(rdao.findAll().size() + " reizigers");
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