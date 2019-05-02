package dao;

import dao.daoInterfaces.MainDaoInterface;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class AbstractDao<T> implements MainDaoInterface<T> {

    protected abstract Class getEntity();

    private final Session session;

    public AbstractDao(Session session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(int id) {
        Transaction transaction = session.beginTransaction();
        T element = (T) session.get(getEntity(), id);
        transaction.commit();
        return element;
    }

    @Override
    public void save(T element) {
        Transaction transaction = session.beginTransaction();
        session.save(element);
        transaction.commit();
    }

    @Override
    public void update(T element) {
        Transaction transaction = session.beginTransaction();
        session.update(element);
        transaction.commit();
    }

    @Override
    public void delete(T element) {
        Transaction transaction = session.beginTransaction();
        session.delete(element);
        transaction.commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        Transaction transaction = session.beginTransaction();
        List<T> list = session.createQuery("From " + getEntity().getSimpleName()).list();
        transaction.commit();
        return list;
    }

    @Override
    public void deleteAll() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from " + getEntity().getSimpleName()).executeUpdate();
        transaction.commit();
    }
}
