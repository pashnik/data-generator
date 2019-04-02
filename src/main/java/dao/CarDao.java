package dao;

import dao.daoInterfaces.FindableByName;
import models.Car;

public class CarDao extends AbstractDao<Car> implements FindableByName<Car> {

    @Override
    protected Class getEntity() {
        return Car.class;
    }

    @Override
    public Car findByName(String name) {
        // TODO
        return null;
    }
}
