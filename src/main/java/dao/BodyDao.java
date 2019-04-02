package dao;

import dao.daoInterfaces.FindableByType;
import models.Body;

public class BodyDao extends AbstractDao<Body> implements FindableByType<Body> {

    @Override
    protected Class getEntity() {
        return Body.class;
    }

    @Override
    public Body findByType(String type) {
        // TODO
        return null;
    }
}
