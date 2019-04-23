package dao;

import models.Users;
import org.hibernate.Session;

public class UsersDao extends AbstractDao<Users> {

    public UsersDao(Session session) {
        super(session);
    }

    @Override
    protected Class getEntity() {
        return Users.class;
    }
}
