package dao;

import dao.daoInterfaces.MainDaoInterface;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import java.util.List;

public abstract class AbstractDao<T> implements MainDaoInterface<T> {

    protected abstract Class getEntity();

    @SuppressWarnings("unchecked")
    @Override
    public T findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T element = (T) HibernateSessionFactory.getSessionFactory().openStatelessSession().get(getEntity(), id);
        transaction.commit();
        session.close();
        return element;
    }

    @Override
    public void save(T element) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(element);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(T element) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(element);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(T element) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(element);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        return (List<T>) HibernateSessionFactory.getSessionFactory()
                .openSession().createQuery("From" + " " + getEntity().getSimpleName()).list();
    }

    @Override
    public void deleteAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from" + " " + getEntity().getSimpleName()).executeUpdate();
        transaction.commit();
        session.close();
    }
}
