package dao;

import models.Car;

public class CarDao extends AbstractDao<Car> {

    @Override
    protected Class getEntity() {
        return Car.class;
    }
}
