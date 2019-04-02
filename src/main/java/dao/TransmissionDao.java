package dao;

import models.Transmission;

public class TransmissionDao extends AbstractDao<Transmission> {

    @Override
    protected Class getEntity() {
        return Transmission.class;
    }
}
