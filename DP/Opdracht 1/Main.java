import java.sql.*;
import java.util.Properties;

public class Main
{
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
            Connection conn = DriverManager.getConnection(url, props);

            // Retrieve travellers
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM reiziger");

            // Print travellers
            System.out.println("Alle reizigers:");
            while (rs.next())
            {
                String id = "#" + rs.getString("reiziger_id") + ": ";
                String voorletters = rs.getString("voorletters") + ". ";
                String tussenvoegsel = rs.getString("tussenvoegsel") == null ? "" : (rs.getString("tussenvoegsel") + " ");
                String achternaam = rs.getString("achternaam") + " ";
                String geboortedatum = "(" + rs.getString("geboortedatum") + ")";
                System.out.println(id + voorletters + tussenvoegsel + achternaam + geboortedatum);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}