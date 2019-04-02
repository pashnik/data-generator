package dao;

import dao.daoInterfaces.FindableByName;
import models.Car;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

public class CarDao extends AbstractDao<Car> implements FindableByName<Car> {

    @Override
    protected Class getEntity() {
        return Car.class;
    }

    @Override
    public Car findByName(String carName) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Car c where c.name = :name");
        query.setParameter("name", carName);
        Car car = (Car) query.uniqueResult();
        transaction.commit();
        session.close();
        return car;
    }
}
