package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import java.util.List;

public abstract class AbstractDao<T> implements DaoInterface<T> {

    protected abstract Class getEntity();

    @SuppressWarnings("unchecked")
    @Override
    public T findById(int id) {
        return (T) HibernateSessionFactory.getSessionFactory().openStatelessSession().get(getEntity(), id);
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

}
