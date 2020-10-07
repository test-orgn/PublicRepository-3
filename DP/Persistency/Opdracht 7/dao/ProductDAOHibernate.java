package nl.hu.dp.ovchip.dao;

import nl.hu.dp.ovchip.domain.OvEntity;
import nl.hu.dp.ovchip.domain.ProductEntity;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO
{
    private Session session;

    public ProductDAOHibernate(Session session)
    {
        this.session = session;
    }

    @Override
    public boolean save(ProductEntity product) throws SQLException
    {
        session.save(product);
        return true;
    }

    @Override
    public boolean update(ProductEntity product) throws SQLException
    {
        session.update(product);
        return true;
    }

    @Override
    public boolean delete(ProductEntity product) throws SQLException
    {
        session.delete(product);
        return true;
    }

    @Override
    public List<ProductEntity> findByOVChipkaart(OvEntity ovKaart) throws SQLException
    {
        String query = "FROM product WHERE ov_chipkaart=:ovKaart";
        return (List<ProductEntity>) session.createQuery(query).setParameter("ovKaart", ovKaart).list();
    }

    @Override
    public List<ProductEntity> findAll() throws SQLException
    {
        return (List<ProductEntity>) session.createQuery("FROM product ").list();
    }

    @Override
    public ProductEntity findbyId(int id) throws SQLException
    {
        return (ProductEntity) session.get(ProductEntity.class, id);
    }
}