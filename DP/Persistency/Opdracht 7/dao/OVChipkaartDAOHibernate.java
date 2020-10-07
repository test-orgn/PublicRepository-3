package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domain.AdresEntity;
import nl.hu.dp.ovchip.domain.OvEntity;
import nl.hu.dp.ovchip.domain.ReizigerEntity;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO
{
    private Session session;

    public OVChipkaartDAOHibernate(Session session)
    {
        this.session = session;
    }

    @Override
    public boolean save(OvEntity ovkaart) throws SQLException
    {
        session.save(ovkaart);
        return true;
    }

    @Override
    public boolean update(OvEntity ovkaart) throws SQLException
    {
        session.update(ovkaart);
        return true;
    }

    @Override
    public boolean delete(OvEntity ovkaart) throws SQLException
    {
        session.delete(ovkaart);
        return true;
    }

    @Override
    public OvEntity findById(int id) throws SQLException
    {
        return (OvEntity) session.get(OvEntity.class, id);
    }

    @Override
    public List<OvEntity> findAll() throws SQLException
    {
        return (List<OvEntity>) session.createQuery("FROM ov_chipkaart").list();
    }

    @Override
    public List<OvEntity> findByReiziger(ReizigerEntity reiziger) throws SQLException
    {
        return (List<OvEntity>) session.createQuery("FROM ov_chipkaart WHERE reizigerEntity = :reiziger").setParameter("reiziger", reiziger).list();
    }
}