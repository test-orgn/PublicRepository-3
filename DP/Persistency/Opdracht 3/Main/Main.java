package Main;

import DAO.AdresDAO;
import DAO.AdresDAOPsql;
import DAO.ReizigerDAO;
import DAO.ReizigerDAOPsql;
import Model.Adres;
import Model.Reiziger;
import java.sql.*;
import java.time.LocalDate;
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

            // Classes for SQL stuff
            ReizigerDAOPsql rdao = new ReizigerDAOPsql(connection);
            AdresDAOPsql adao = new AdresDAOPsql(connection);
            rdao.setAdao(adao);
            adao.setRdao(rdao);

            // Traveller testing
            testReizigerDAO(rdao);

            // Address testing
            Reiziger s = new Reiziger(78, "S", "", "Boers", LocalDate.of(1981, 3, 14));
            rdao.save(s);
            testAdresDAO(adao);
            rdao.delete(s);

            // Close connection
            closeConnection();
        }
        catch (SQLException e)
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
        Reiziger s = new Reiziger(78, "S", "", "Boers", LocalDate.of(1981, 3, 14));
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

    private static void testAdresDAO(AdresDAO adao) throws SQLException
    {
        System.out.println("\n---------- Test AdresDAO -------------");

        // Read addresses
        System.out.println("[READ] AdresDAO.findAll() geeft de volgende reizigers:");
        for (Adres a : adao.findAll())
        {
            System.out.println(a);
        }
        System.out.println();

        final int ADRES_ID = 420;
        Adres adres = new Adres(ADRES_ID, "1234AB", "5A", "Schapenweg", "Schaapdorp", 78);

        // Create address
        System.out.print("[CREATE] Eerst " + adao.findAll().size() + " adressen, NA create ");
        adao.save(adres);
        System.out.println(adao.findAll().size() + " adressen");

        // Update address
        System.out.print("[UPDATE] Adres huisnummer VOOR update: " + adao.findById(ADRES_ID).getHuisnummer());
        adres.setHuisnummer("5B");
        adao.update(adres);
        System.out.println(", NA update: " + adao.findById(ADRES_ID).getHuisnummer());

        // Delete address
        System.out.print("[DELETE] Eerst " + adao.findAll().size() + " adressen, NA delete ");
        adao.delete(adres);
        System.out.println(adao.findAll().size() + " adressen");
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