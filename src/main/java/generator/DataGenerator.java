package generator;

import dao.*;
import models.*;
import org.hibernate.Session;
import utils.HibernateSessionFactory;
import utils.res.DataConfigResource;

import static java.lang.Math.*;
import static utils.DataGenerator.*;

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

    public DataGenerator(DataConfigResource cfg) {
        this(cfg.getManufacturerNumber(), cfg.getCarNumber(),
                cfg.getComplectationNumber(), cfg.getTransmissionNumber(), cfg.getBodyNumber(),
                cfg.getEngineNumber(), cfg.getOptionalNumber(), cfg.getComplectationOptionalNumber(),
                cfg.getComplectationEngineNumber(), cfg.getOptionalComplectationNumber(),
                cfg.getEngineComplectationNumber());
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
            String manufacturerName = MANUFACTURERS.get((int) (random() *
                    MANUFACTURERS.size()));
            String countryName = COUNTRIES.get((int) (random() *
                    COUNTRIES.size()));

            Manufacturer manufacturer = new Manufacturer(manufacturerName, countryName);
            manufacturerDao.save(manufacturer);
        }
    }

    private void generateCar() {
        for (int i = 0; i < carNumber; i++) {
            String carName = PREFIX_CAR_NAME.get((int) (random() * PREFIX_CAR_NAME.size())) +
                    POSTFIX_CAR_NAME.get((int) (random() *
                            POSTFIX_CAR_NAME.size()));
            Manufacturer manufacturer = manufacturerDao.findById((int) (random() * manufacturerNumber) + 1);

            Car car = new Car(carName, manufacturer);
            carDao.save(car);
        }
    }

    private void generateTransmission() {
        for (int i = 0; i < transmissionNumber; i++) {
            String transmissionType = TRANSMISSION_TYPE.get((int) (random() *
                    TRANSMISSION_TYPE.size()));

            Transmission transmission = new Transmission(transmissionType);
            transmissionDao.save(transmission);
        }
    }

    private void generateBody() {
        for (int i = 0; i < bodyNumber; i++) {
            String bodyType = BODY_TYPE.get((int) (random() *
                    BODY_TYPE.size()));

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
        return PREFIX_OPTIONAL.get((int) (random() *
                PREFIX_OPTIONAL.size())) +
                POSTFIX_OPTIONAL.get((int) (random()) *
                        POSTFIX_OPTIONAL.size());
    }

    private void generateEngine() {
        for (int i = 0; i < engineNumber; i++) {
            int power = (int) (random() * (MAX_ENGINE_POWER - MIN_ENGINE_POWER)) +
                    MIN_ENGINE_POWER + 1;
            int workingVolume = (int) (random() * (MAX_WORKING_VOLUME - MIN_WORKING_VOLUME)) +
                    MIN_WORKING_VOLUME + 1;
            String engineType = ENGINE_TYPE.get((int) (random() * (ENGINE_TYPE.size())));
            int ecologicalClass = (int) (random() * (MAX_ECOLOGICAL_CLASS -
                    MIN_ECOLOGICAL_CLASS)) + MIN_ECOLOGICAL_CLASS + 1;

            Engine engine = new Engine(power, engineType, workingVolume, ecologicalClass);
            engineDao.save(engine);
        }
    }

    private void generateComplectation() {
        for (int i = 0; i < complectationNumber; i++) {
            Transmission transmission = transmissionDao.findById((int) (random() *
                    transmissionNumber) + 1);
            Body body = bodyDao.findById((int) (random() *
                    bodyNumber) + 1);
            Car car = carDao.findById((int) (random() *
                    carNumber) + 1);

            Complectation complectation = new Complectation(car, transmission, body);
            complectationDao.save(complectation);
        }
    }

    private void bindCompOptional() {
        List<Complectation> complectations = complectationDao.findAll();
        for (Complectation complectation : complectations) {
            for (int i = 0; i < complectationOptionalNumber; i++) {
                complectation.addOptional(optionalDao.findById((int) (random() * optionalNumber) + 1));
            }
            complectationDao.update(complectation);
        }
    }

    private void bindOptionalComp() {
        List<Optional> optionals = optionalDao.findAll();
        for (Optional optional : optionals) {
            for (int i = 0; i < optionalComplectationNumber; i++) {
                optional.addComplectation(complectationDao.findById((int) (random() *
                        complectationNumber) + 1));
            }
            optionalDao.update(optional);
        }
    }

    private void bindCompEngine() {
        List<Complectation> complectations = complectationDao.findAll();
        for (Complectation complectation : complectations) {
            for (int i = 0; i < complectationEngineNumber; i++) {
                complectation.addEngine(engineDao.findById((int) (random() *
                        engineNumber) + 1));
            }
            complectationDao.update(complectation);
        }
    }

    private void bindEngineComp() {
        List<Engine> engines = engineDao.findAll();
        for (Engine engine : engines) {
            for (int i = 0; i < engineComplectationNumber; i++) {
                engine.addComplectation(complectationDao.findById((int) (random() *
                        complectationNumber) + 1));
            }
            engineDao.update(engine);
        }
    }
}
