package nl.hu.dp.ovchip;

import nl.hu.dp.ovchip.dao.*;
import nl.hu.dp.ovchip.domain.AdresEntity;
import nl.hu.dp.ovchip.domain.OvEntity;
import nl.hu.dp.ovchip.domain.ProductEntity;
import nl.hu.dp.ovchip.domain.ReizigerEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main
{
    private static final SessionFactory factory;

    static
    {
        try
        {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex)
        {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(String[] args) throws SQLException
    {
        // Create session
        Session session = null;
        try
        {
            session = factory.openSession();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }

        // Create DAOs
        ReizigerDAO rdao = new ReizigerDAOHibernate(session);
        AdresDAO adao = new AdresDAOHibernate(session);
        OVChipkaartDAO odao = new OVChipkaartDAOHibernate(session);
        ProductDAO pdao = new ProductDAOHibernate(session);

        // Test DAOs
        try
        {
            session.beginTransaction();
            testReizigerDAO(rdao);
            testAdresDAO(adao, rdao);
            testOvkaartDAO(odao, rdao);
            testProductDAO(pdao);
            session.getTransaction().commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        // Close session
        session.close();
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Read travellers
        System.out.println("[READ] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (ReizigerEntity r : rdao.findAll()) {
            System.out.println(r);
        }
        System.out.println();

        // Create traveller
        ReizigerEntity s = new ReizigerEntity(78, "S", "", "Boers", LocalDate.of(1981, 3, 14));
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

    private static void testAdresDAO(AdresDAO adao, ReizigerDAO rdao) throws SQLException
    {
        System.out.println("\n---------- Test AdresDAO -------------");

        // Read addresses
        System.out.println("[READ] AdresDAO.findAll() geeft de volgende adressen:");
        for (AdresEntity a : adao.findAll())
        {
            System.out.println(a);
        }
        System.out.println();

        final int ADRES_ID = 420;
        ReizigerEntity reiziger = new ReizigerEntity(123, "S", "", "Boerz", LocalDate.of(1981, 3, 14));
        AdresEntity adres = new AdresEntity(ADRES_ID, "1234AB", "5A", "Schapenweg", "Schaapdorp", reiziger);

        // Create address
        System.out.print("[CREATE] Eerst " + adao.findAll().size() + " adressen, NA create ");
        rdao.save(reiziger);
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
        rdao.delete(reiziger);
        System.out.println(adao.findAll().size() + " adressen");
    }

    private static void testOvkaartDAO(OVChipkaartDAO odao, ReizigerDAO rdao) throws SQLException
    {
        System.out.println("\n---------- Test OVChipkaartDAO -------------");

        // Read addresses
        System.out.println("[READ] OVChipkaartDAO.findAll() geeft de volgende ovkaarten:");
        for (OvEntity o : odao.findAll())
        {
            System.out.println(o);
        }
        System.out.println();

        ReizigerEntity reiziger = new ReizigerEntity(123, "S", "", "Boerz", LocalDate.of(1981, 3, 14));
        OvEntity ovkaart = new OvEntity(12345, 1, LocalDate.of(2020, 12, 5), 420.0f, reiziger);

        // Create address
        System.out.print("[CREATE] Eerst " + odao.findAll().size() + " ovkaarten, NA create ");
        rdao.save(reiziger);
        odao.save(ovkaart);
        System.out.println(odao.findAll().size() + " ovkaarten");

        // Update address
        System.out.print("[UPDATE] Ovkaart saldo VOOR update: " + odao.findById(12345).getSaldo());
        ovkaart.setSaldo(69.0f);
        odao.update(ovkaart);
        System.out.println(", NA update: " + odao.findById(12345).getSaldo());

        // Delete address
        System.out.print("[DELETE] Eerst " + odao.findAll().size() + " ovkaarten, NA delete ");
        odao.delete(ovkaart);
        rdao.delete(reiziger);
        System.out.println(odao.findAll().size() + " ovkaarten");
    }

    private static void testProductDAO(ProductDAO pdao) throws SQLException {
        System.out.println("\n---------- Test ProductDAO -------------");

        // Read products
        System.out.println("[READ] ProductDAO.findAll() geeft de volgende producten:");
        for (ProductEntity p : pdao.findAll()) {
            System.out.println(p);
        }
        System.out.println();

        // Create products
        ProductEntity p = new ProductEntity(123, "test", "dit is een test", 420.0f, new ArrayList<>());
        System.out.print("[CREATE] Eerst " + pdao.findAll().size() + " producten, NA create ");
        pdao.save(p);
        System.out.println(pdao.findAll().size() + " producten");

        // Update products
        System.out.print("[UPDATE] Product naam VOOR update: " + pdao.findbyId(p.getId()).getNaam());
        p.setNaam("andere_test");
        pdao.update(p);
        System.out.println(", NA update: " + pdao.findbyId(p.getId()).getNaam());

        // Delete products
        System.out.print("[DELETE] Eerst " + pdao.findAll().size() + " producten, NA delete ");
        pdao.delete(p);
        System.out.println(pdao.findAll().size() + " producten");
    }
}