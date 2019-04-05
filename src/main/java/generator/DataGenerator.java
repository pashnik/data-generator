package generator;

import dao.*;
import models.*;

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

    /*
    (int) (Math.random() * n) generates a number from 0 to n-1.
     */

    public void generate() {
        generateManufacturer();
        generateCar();
        generateTransmission();
        generateBody();
        generateComplectation();

        // generate bindings to optional and engine!
    }

    private void generateManufacturer() {
        for (int i = 0; i < manufacturerNumber; i++) {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setManufacturerName(Data.MANUFACTURERS.get((int) (Math.random() *
                    Data.MANUFACTURERS.size())));
            manufacturer.setCountryName(Data.COUNTRIES.get((int) (Math.random() *
                    Data.COUNTRIES.size())));
            manufacturerDao.save(manufacturer);
        }
    }

    private void generateCar() {
        for (int i = 0; i < carNumber; i++) {
            Car car = new Car();
            String carName = Data.PREFIX_CAR_NAME.get((int) (Math.random() * Data.PREFIX_CAR_NAME.size())) +
                    Data.POSTFIX_CAR_NAME.get((int) (Math.random() *
                            Data.POSTFIX_CAR_NAME.size()));

            car.setName(carName);
            car.setManufacturer(manufacturerDao.findById((int) (Math.random() * manufacturerNumber) + 1));
            carDao.save(car);
        }
    }

    private void generateTransmission() {
        for (int i = 0; i < transmissionNumber; i++) {
            Transmission transmission = new Transmission();
            transmission.setTransmissionType(Data.TRANSMISSION_TYPE.get((int) (Math.random() *
                    Data.TRANSMISSION_TYPE.size())));
            transmissionDao.save(transmission);
        }
    }

    private void generateBody() {
        for (int i = 0; i < bodyNumber; i++) {
            Body body = new Body();
            body.setBodyType(Data.BODY_TYPE.get((int) (Math.random() *
                    Data.BODY_TYPE.size())));
            bodyDao.save(body);
        }
    }

    private void generateOptional() {
        for (int i = 0; i < optionalNumber; i++) {
            Optional optional = new Optional();
            optional.setMediaCenter(getOptionalSystem());
            optional.setAirBags(getOptionalSystem());
            optional.setEmergencySystem(getOptionalSystem());
            optional.setHeadlights(getOptionalSystem());
            optional.setSecurityAlarm(getOptionalSystem());

            optional.addComplectation(complectationDao.findById((int) (Math.random() *
                    complectationNumner) + 1));
            optionalDao.save(optional);
        }
    }

    private String getOptionalSystem() {
        return Data.PREFIX_OPTIONAL.get((int) (Math.random() *
                Data.PREFIX_OPTIONAL.size())) +
                Data.POSTFIX_OPTIONAL.get((int) (Math.random()) *
                        Data.POSTFIX_OPTIONAL.size());
    }

    private void generateEngine() {
        for (int i = 0; i < engineNumber; i++) {
            Engine engine = new Engine();
            engine.setPower((int) (Math.random() * (Data.MAX_ENGINE_POWER - Data.MIN_ENGINE_POWER)) +
                    Data.MIN_ENGINE_POWER + 1);
            engine.setWorkingVolume((int) (Math.random() * (Data.MAX_WORKING_VOLUME - Data.MIN_WORKING_VOLUME)) +
                    Data.MIN_WORKING_VOLUME + 1);
            engine.setEngineType(Data.ENGINE_TYPE.get((int) (Math.random() * (Data.ENGINE_TYPE.size()))));
            engine.setEcologicalClass((int) (Math.random() * (Data.MAX_ECOLOGICAL_CLASS -
                    Data.MIN_ECOLOGICAL_CLASS)) + Data.MIN_ECOLOGICAL_CLASS + 1);

            engine.addComplectation(complectationDao.findById((int) (Math.random() *
                    complectationNumner) + 1));
            engineDao.save(engine);
        }
    }

    private void generateComplectation() {
        for (int i = 0; i < complectationNumner; i++) {
            Complectation complectation = new Complectation();
            complectation.setTransmission(transmissionDao.findById((int) (Math.random() *
                    transmissionNumber) + 1));
            complectation.setBody(bodyDao.findById((int) (Math.random() *
                    bodyNumber) + 1));
            complectation.setCar(carDao.findById((int) (Math.random() *
                    carNumber) + 1));
            complectationDao.save(complectation);
        }
    }


}
