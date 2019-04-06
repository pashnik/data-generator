package dao;

import models.Optional;
import org.hibernate.Session;

public class OptionalDao extends AbstractDao<Optional> {

    public OptionalDao(Session session) {
        super(session);
    }

    @Override
    protected Class getEntity() {
        return Optional.class;
    }
}
