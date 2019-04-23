package dao;

import models.PostTypes;
import org.hibernate.Session;

public class PostTypesDao extends AbstractDao<PostTypes> {

    public PostTypesDao(Session session) {
        super(session);
    }

    @Override
    protected Class getEntity() {
        return PostTypes.class;
    }
}
