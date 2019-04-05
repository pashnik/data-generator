package dao;

import dao.daoInterfaces.FindableByName;
import models.Manufacturer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

public class ManufacturerDao extends AbstractDao<Manufacturer> implements FindableByName<Manufacturer> {

    @Override
    protected Class getEntity() {
        return Manufacturer.class;
    }

    @Override
    public Manufacturer findByName(String manufacturerName) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Manufacturer m where m.manufacturerName = :name");
        query.setParameter("name", manufacturerName);
        Manufacturer manufacturer = (Manufacturer) query.uniqueResult();
        try {
            transaction.commit();
        } finally {
            session.close();
        }
        return manufacturer;
    }
}
