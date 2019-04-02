package dao;

import models.Body;

public class BodyDao extends AbstractDao<Body> {

    @Override
    protected Class getEntity() {
        return Body.class;
    }
}
