package dao;

import dao.daoInterfaces.FindableByType;
import models.Body;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

public class BodyDao extends AbstractDao<Body> implements FindableByType<Body> {

    @Override
    protected Class getEntity() {
        return Body.class;
    }

    @Override
    public Body findByType(String bodyType) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Body b where b.bodyType = :name");
        query.setParameter("name", bodyType);
        Body body = (Body) query.uniqueResult();
        try {
            transaction.commit();
        } finally {
            session.close();
        }
        return body;
    }
}
