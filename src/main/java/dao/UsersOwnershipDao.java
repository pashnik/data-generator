package dao;

import models.UsersOwnership;
import org.hibernate.Session;

public class UsersOwnershipDao extends AbstractDao<UsersOwnership> {

    public UsersOwnershipDao(Session session) {
        super(session);
    }

    @Override
    protected Class getEntity() {
        return UsersOwnership.class;
    }
}
