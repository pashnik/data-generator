package dao;

import dao.daoInterfaces.FindableByType;
import models.Transmission;

public class TransmissionDao extends AbstractDao<Transmission> implements FindableByType<Transmission> {

    @Override
    protected Class getEntity() {
        return Transmission.class;
    }

    @Override
    public Transmission findByType(String type) {
        //TODO
        return null;
    }
}
