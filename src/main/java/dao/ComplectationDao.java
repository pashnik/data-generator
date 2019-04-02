package dao;

import models.Complectation;

public class ComplectationDao extends AbstractDao<Complectation> {

    @Override
    protected Class getEntity() {
        return Complectation.class;
    }
}
