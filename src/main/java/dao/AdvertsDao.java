package dao;

import models.Adverts;
import org.hibernate.Session;

public class AdvertsDao extends AbstractDao<Adverts> {

    public AdvertsDao(Session session) {
        super(session);
    }

    @Override
    protected Class getEntity() {
        return Adverts.class;
    }
}
