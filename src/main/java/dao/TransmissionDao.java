package dao;

import dao.daoInterfaces.FindableByType;
import models.Transmission;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

public class TransmissionDao extends AbstractDao<Transmission> implements FindableByType<Transmission> {

    @Override
    protected Class getEntity() {
        return Transmission.class;
    }

    @Override
    public Transmission findByType(String transmissionType) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Transmission t where t.transmissionType = :name");
        query.setParameter("name", transmissionType);
        Transmission transmission = (Transmission) query.uniqueResult();
        try {
            transaction.commit();
        } finally {
            session.close();
        }
        return transmission;
    }
}
