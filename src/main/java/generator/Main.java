package generator;

import dao.ManufacturerDao;
import models.Car;
import models.Manufacturer;

public class Main {
    public static void main(String[] args) {
        Manufacturer audi = new Manufacturer("Germany", "Audi");

        Car car = new Car("A8", audi);
        car.setManufacturer(audi);

        audi.addCar(car);

        ManufacturerDao dao = new ManufacturerDao();
        dao.save(audi);
    }
}
