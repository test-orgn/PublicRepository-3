package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domain.ReizigerEntity;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO
{
    private Session session;

    public ReizigerDAOHibernate(Session session)
    {
        this.session = session;
    }

    @Override
    public boolean save(ReizigerEntity reiziger) throws SQLException
    {
        session.save(reiziger);
        return true;
    }

    @Override
    public boolean update(ReizigerEntity reiziger) throws SQLException
    {
        session.update(reiziger);
        return true;
    }

    @Override
    public boolean delete(ReizigerEntity reiziger) throws SQLException
    {
        session.delete(reiziger);
        return true;
    }

    @Override
    public ReizigerEntity findByID(int id) throws SQLException
    {
        return (ReizigerEntity) session.get(ReizigerEntity.class, id);
    }

    @Override
    public List<ReizigerEntity> findByGbdatum(String date) throws SQLException
    {
        return (List<ReizigerEntity>) session.createQuery("FROM reiziger WHERE geboortedatum = :date").setParameter("date", date).list();
    }

    @Override
    public List<ReizigerEntity> findAll() throws SQLException
    {
        return (List<ReizigerEntity>) session.createQuery("FROM reiziger").list();
    }
}