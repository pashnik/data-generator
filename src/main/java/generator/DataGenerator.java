package generator;

import dao.*;
import models.Body;
import models.Car;
import models.Manufacturer;
import models.Transmission;

public class DataGenerator {

    private final BodyDao bodyDao = new BodyDao();
    private final CarDao carDao = new CarDao();
    private final ComplectationDao complectationDao = new ComplectationDao();
    private final EngineDao engineDao = new EngineDao();
    private final ManufacturerDao manufacturerDao = new ManufacturerDao();
    private final OptionalDao optionalDao = new OptionalDao();
    private final TransmissionDao transmissionDao = new TransmissionDao();

    private final int manufacturerNumber;
    private final int carNumber;
    private final int complectationNumner;
    private final int transmissionNumber;
    private final int bodyNumber;
    private final int engineNumber;
    private final int optionalNumber;

    public DataGenerator(int manufacturerNumber, int carNumber, int complectationNumner,
                         int transmissionNumber, int bodyNumber, int engineNumber,
                         int optionalNumber) {
        this.manufacturerNumber = manufacturerNumber;
        this.carNumber = carNumber;
        this.complectationNumner = complectationNumner;
        this.transmissionNumber = transmissionNumber;
        this.bodyNumber = bodyNumber;
        this.engineNumber = engineNumber;
        this.optionalNumber = optionalNumber;
    }

    public void generate() {
        generateManufacturer();
        generateCar();
    }

    private void generateManufacturer() {
        for (int i = 0; i < manufacturerNumber; i++) {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setManufacturerName(Data.manufacturers.get((int) (Math.random() *
                    Data.manufacturers.size())));
            manufacturer.setCountryName(Data.countries.get((int) (Math.random() *
                    Data.countries.size())));
            manufacturerDao.save(manufacturer);
        }
    }

    private void generateCar() {
        for (int i = 0; i < carNumber; i++) {
            Car car = new Car();
            String carName = Data.prefixCarName.get((int) (Math.random() * Data.prefixCarName.size())) +
                    Data.postfixCarName.get((int) (Math.random() *
                            Data.postfixCarName.size()));

            car.setName(carName);
            car.setManufacturer(manufacturerDao.findById((int) (Math.random() * manufacturerNumber) + 1)); //check
            carDao.save(car);
        }
    }

    private void generateTransmission() {
        for (int i = 0; i < transmissionNumber; i++) {
            Transmission transmission = new Transmission();
            transmission.setTransmissionType(Data.transmissionType.get((int) (Math.random() *
                    Data.transmissionType.size())));
        }
    }

    private void generateBody() {
        for (int i = 0; i < bodyNumber; i++) {
            Body body = new Body();
            body.setBodyType(Data.bodyType.get((int) (Math.random() *
                    Data.bodyType.size())));
        }
    }

    private void generateOptional() {
        // TODO
    }

    private void generateEngine() {
        // TODO
    }

    private void generateComplectation() {

    }


}
