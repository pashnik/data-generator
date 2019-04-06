package dao;

import models.Complectation;
import org.hibernate.Session;

public class ComplectationDao extends AbstractDao<Complectation> {

    public ComplectationDao(Session session) {
        super(session);
    }

    @Override
    protected Class getEntity() {
        return Complectation.class;
    }
}
