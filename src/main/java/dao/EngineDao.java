package dao;

import models.Engine;
import org.hibernate.Session;

public class EngineDao extends AbstractDao<Engine> {

    public EngineDao(Session session) {
        super(session);
    }

    @Override
    protected Class getEntity() {
        return Engine.class;
    }
}
