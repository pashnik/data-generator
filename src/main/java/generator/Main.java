package generator;

import dao.ManufacturerDao;
import models.Manufacturer;

public class Main {
    public static void main(String[] args) {
        ManufacturerDao manufacturerDao = new ManufacturerDao();
        Manufacturer manufacturer = new Manufacturer("Germay", "Mercedes");
        Manufacturer manufacturer1 = new Manufacturer("Germany", "BMW");

        manufacturerDao.save(manufacturer);
        manufacturerDao.save(manufacturer1);

//        CarDao carDao = new CarDao();
//        carDao.deleteAll();
    }
}
