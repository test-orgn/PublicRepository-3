package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domain.AdresEntity;
import nl.hu.dp.ovchip.domain.ReizigerEntity;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO
{
    private Session session;

    public AdresDAOHibernate(Session session)
    {
        this.session = session;
    }

    @Override
    public boolean save(AdresEntity adres) throws SQLException
    {
        session.save(adres);
        return true;
    }

    @Override
    public boolean update(AdresEntity adres) throws SQLException
    {
        session.update(adres);
        return true;
    }

    @Override
    public boolean delete(AdresEntity adres) throws SQLException
    {
        session.delete(adres);
        return true;
    }

    @Override
    public AdresEntity findByReiziger(ReizigerEntity reiziger) throws SQLException
    {
        return (AdresEntity) session.get(AdresEntity.class, reiziger.getId());
    }

    @Override
    public AdresEntity findById(int id) throws SQLException
    {
        return (AdresEntity) session.get(AdresEntity.class, id);
    }

    @Override
    public List<AdresEntity> findAll() throws SQLException
    {
        return (List<AdresEntity>) session.createQuery("FROM adres").list();
    }
}