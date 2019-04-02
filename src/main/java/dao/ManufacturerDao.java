package dao;

import models.Manufacturer;

public class ManufacturerDao extends AbstractDao<Manufacturer> {

    @Override
    protected Class getEntity() {
        return Manufacturer.class;
    }
}
