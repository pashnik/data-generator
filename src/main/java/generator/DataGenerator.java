package generator;

import dao.*;
import models.*;
import org.hibernate.Session;
import utils.HibernateSessionFactory;

import java.util.List;

public class DataGenerator {

    private Session session;

    private BodyDao bodyDao;
    private CarDao carDao;
    private ComplectationDao complectationDao;
    private EngineDao engineDao;
    private ManufacturerDao manufacturerDao;
    private OptionalDao optionalDao;
    private TransmissionDao transmissionDao;

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

        this.session = HibernateSessionFactory.getSessionFactory().openSession();

        this.bodyDao = new BodyDao(session);
        this.carDao = new CarDao(session);
        this.complectationDao = new ComplectationDao(session);
        this.engineDao = new EngineDao(session);
        this.manufacturerDao = new ManufacturerDao(session);
        this.optionalDao = new OptionalDao(session);
        this.transmissionDao = new TransmissionDao(session);
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

        session.close();
    }

    public void stop() {
        HibernateSessionFactory.stop();
    }

    private void generateManufacturer() {
        for (int i = 0; i < manufacturerNumber; i++) {
            String manufacturerName = Data.MANUFACTURERS.get((int) (Math.random() *
                    Data.MANUFACTURERS.size()));
            String countryName = Data.COUNTRIES.get((int) (Math.random() *
                    Data.COUNTRIES.size()));

            Manufacturer manufacturer = new Manufacturer(manufacturerName, countryName);
            manufacturerDao.save(manufacturer);
        }
    }

    private void generateCar() {
        for (int i = 0; i < carNumber; i++) {
            String carName = Data.PREFIX_CAR_NAME.get((int) (Math.random() * Data.PREFIX_CAR_NAME.size())) +
                    Data.POSTFIX_CAR_NAME.get((int) (Math.random() *
                            Data.POSTFIX_CAR_NAME.size()));
            Manufacturer manufacturer = manufacturerDao.findById((int) (Math.random() * manufacturerNumber) + 1);

            Car car = new Car(carName, manufacturer);
            carDao.save(car);
        }
    }

    private void generateTransmission() {
        for (int i = 0; i < transmissionNumber; i++) {
            String transmissionType = Data.TRANSMISSION_TYPE.get((int) (Math.random() *
                    Data.TRANSMISSION_TYPE.size()));

            Transmission transmission = new Transmission(transmissionType);
            transmissionDao.save(transmission);
        }
    }

    private void generateBody() {
        for (int i = 0; i < bodyNumber; i++) {
            String bodyType = Data.BODY_TYPE.get((int) (Math.random() *
                    Data.BODY_TYPE.size()));

            Body body = new Body(bodyType);
            bodyDao.save(body);
        }
    }

    private void generateOptional() {
        for (int i = 0; i < optionalNumber; i++) {
            Optional optional = new Optional(getSystem(), getSystem(), getSystem(), getSystem(), getSystem());
            optionalDao.save(optional);
        }
    }

    private String getSystem() {
        return Data.PREFIX_OPTIONAL.get((int) (Math.random() *
                Data.PREFIX_OPTIONAL.size())) +
                Data.POSTFIX_OPTIONAL.get((int) (Math.random()) *
                        Data.POSTFIX_OPTIONAL.size());
    }

    private void generateEngine() {
        for (int i = 0; i < engineNumber; i++) {
            int power = (int) (Math.random() * (Data.MAX_ENGINE_POWER - Data.MIN_ENGINE_POWER)) +
                    Data.MIN_ENGINE_POWER + 1;
            int workingVolume = (int) (Math.random() * (Data.MAX_WORKING_VOLUME - Data.MIN_WORKING_VOLUME)) +
                    Data.MIN_WORKING_VOLUME + 1;
            String engineType = Data.ENGINE_TYPE.get((int) (Math.random() * (Data.ENGINE_TYPE.size())));
            int ecologicalClass = (int) (Math.random() * (Data.MAX_ECOLOGICAL_CLASS -
                    Data.MIN_ECOLOGICAL_CLASS)) + Data.MIN_ECOLOGICAL_CLASS + 1;

            Engine engine = new Engine(power, engineType, workingVolume, ecologicalClass);
            engineDao.save(engine);
        }
    }

    private void generateComplectation() {
        for (int i = 0; i < complectationNumber; i++) {
            Transmission transmission = transmissionDao.findById((int) (Math.random() *
                    transmissionNumber) + 1);
            Body body = bodyDao.findById((int) (Math.random() *
                    bodyNumber) + 1);
            Car car = carDao.findById((int) (Math.random() *
                    carNumber) + 1);

            Complectation complectation = new Complectation(car, transmission, body);
            complectationDao.save(complectation);
        }
    }

    private void bindCompOptional() {
        List<Complectation> complectations = complectationDao.findAll();
        for (Complectation complectation : complectations) {
            for (int i = 0; i < complectationOptionalNumber; i++) {
                complectation.addOptional(optionalDao.findById((int) (Math.random() * optionalNumber) + 1));
            }
            complectationDao.update(complectation);
        }
    }

    private void bindOptionalComp() {
        List<Optional> optionals = optionalDao.findAll();
        for (Optional optional : optionals) {
            for (int i = 0; i < optionalComplectationNumber; i++) {
                optional.addComplectation(complectationDao.findById((int) (Math.random() *
                        complectationNumber) + 1));
            }
            optionalDao.update(optional);
        }
    }

    private void bindCompEngine() {
        List<Complectation> complectations = complectationDao.findAll();
        for (Complectation complectation : complectations) {
            for (int i = 0; i < complectationEngineNumber; i++) {
                complectation.addEngine(engineDao.findById((int) (Math.random() *
                        engineNumber) + 1));
            }
            complectationDao.update(complectation);
        }
    }

    private void bindEngineComp() {
        List<Engine> engines = engineDao.findAll();
        for (Engine engine : engines) {
            for (int i = 0; i < engineComplectationNumber; i++) {
                engine.addComplectation(complectationDao.findById((int) (Math.random() *
                        complectationNumber) + 1));
            }
            engineDao.update(engine);
        }
    }
}
