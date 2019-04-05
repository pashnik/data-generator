package generator;

import dao.*;
import models.*;

import java.util.List;

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
    private final int complectationNumber;
    private final int transmissionNumber;
    private final int bodyNumber;
    private final int engineNumber;
    private final int optionalNumber;
    private final int complectationOptionalNumber;
    private final int complectationEngineNumber;
    private final int optionalComplectationNumber;
    private final int engineComplectationNumber;

    public DataGenerator(int manufacturerNumber, int carNumber, int complectationNumber,
                         int transmissionNumber, int bodyNumber, int engineNumber,
                         int optionalNumber, int complectationOptionalNumber,
                         int complectationEngineNumber, int optionalComplectationNumber,
                         int engineComplectationNumber) {
        this.manufacturerNumber = manufacturerNumber;
        this.carNumber = carNumber;
        this.complectationNumber = complectationNumber;
        this.transmissionNumber = transmissionNumber;
        this.bodyNumber = bodyNumber;
        this.engineNumber = engineNumber;
        this.optionalNumber = optionalNumber;
        this.complectationOptionalNumber = complectationOptionalNumber;
        this.complectationEngineNumber = complectationEngineNumber;
        this.optionalComplectationNumber = optionalComplectationNumber;
        this.engineComplectationNumber = engineComplectationNumber;
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
        generateOptional();
        generateEngine();

        // generate bindings to optionals and engines
        bindCompEngine();
        bindCompOptional();
        bindEngineComp();
        bindOptionalComp();
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
            engineDao.save(engine);
        }
    }

    private void generateComplectation() {
        for (int i = 0; i < complectationNumber; i++) {
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

    private void bindCompOptional() {
        List<Complectation> complectations = complectationDao.findAll();
        for (Complectation complectation : complectations) {
            for (int i = 0; i < complectationOptionalNumber; i++) {
                complectation.addOptional(optionalDao.findById((int) (Math.random() * optionalNumber) + 1));
            }
        }
    }

    private void bindOptionalComp() {
        List<Optional> optionals = optionalDao.findAll();
        for (Optional optional : optionals) {
            for (int i = 0; i < optionalComplectationNumber; i++) {
                optional.addComplectation(complectationDao.findById((int) (Math.random() *
                        complectationNumber) + 1));
            }
        }
    }

    private void bindCompEngine() {
        List<Complectation> complectations = complectationDao.findAll();
        for (Complectation complectation : complectations) {
            for (int i = 0; i < complectationEngineNumber; i++) {
                complectation.addEngine(engineDao.findById((int) (Math.random() *
                        engineNumber) + 1));
            }
        }
    }

    private void bindEngineComp() {
        List<Engine> engines = engineDao.findAll();
        for (Engine engine : engines) {
            for (int i = 0; i < engineComplectationNumber; i++) {
                engine.addComplectation(complectationDao.findById((int) (Math.random() *
                        complectationNumber) + 1));
            }
        }
    }
}
