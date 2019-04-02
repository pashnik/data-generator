package dao;

import models.Engine;

public class EngineDao extends AbstractDao<Engine> {

    @Override
    protected Class getEntity() {
        return Engine.class;
    }
}
