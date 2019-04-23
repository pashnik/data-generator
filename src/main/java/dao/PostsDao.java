package dao;

import models.Posts;
import org.hibernate.Session;

public class PostsDao extends AbstractDao<Posts> {

    public PostsDao(Session session) {
        super(session);
    }

    @Override
    protected Class getEntity() {
        return Posts.class;
    }
}
