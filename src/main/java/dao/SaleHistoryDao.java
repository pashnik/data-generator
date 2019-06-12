package dao;

import models.SaleHistory;
import org.hibernate.Session;

public class SaleHistoryDao extends AbstractDao<SaleHistory> {

    public SaleHistoryDao(Session session) {
        super(session);
    }

    @Override
    protected Class getEntity() {
        return null;
    }

}
