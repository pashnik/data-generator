package dao;

import models.Manufacturer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import java.util.List;

public class ManufacturerDao implements DaoInterface<Manufacturer> {

    @Override
    public Manufacturer findById(int id) {
        return (Manufacturer) HibernateSessionFactory.getSessionFactory().openSession().get(Manufacturer.class, id);
    }

    @Override
    public void save(Manufacturer element) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(element);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Manufacturer element) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(element);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Manufacturer element) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(element);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Manufacturer> findAll() {
        return (List<Manufacturer>) HibernateSessionFactory.getSessionFactory()
                .openSession().createQuery("From").list();
    }
}
