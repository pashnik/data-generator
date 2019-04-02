package dao;

import models.Optional;

public class OptionalDao extends AbstractDao<Optional> {

    @Override
    protected Class getEntity() {
        return Optional.class;
    }
}
