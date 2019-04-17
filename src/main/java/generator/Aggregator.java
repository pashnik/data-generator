package generator;

import dao.*;
import models.*;
import org.hibernate.Session;
import utils.HibernateSessionFactory;
import utils.ObjectGenerator;
import utils.objectResources.DataConfigResource;

import static utils.Randomizer.*;

import java.util.List;

public class Aggregator {

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

    public Aggregator(int manufacturerNumber, int carNumber, int complectationNumber,
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

    public Aggregator(DataConfigResource cfg) {
        this(cfg.getManufacturerNumber(), cfg.getCarNumber(),
                cfg.getComplectationNumber(), cfg.getTransmissionNumber(), cfg.getBodyNumber(),
                cfg.getEngineNumber(), cfg.getOptionalNumber(), cfg.getComplectationOptionalNumber(),
                cfg.getComplectationEngineNumber(), cfg.getOptionalComplectationNumber(),
                cfg.getEngineComplectationNumber());
    }

    public void fillTables() {
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
            Manufacturer manufacturer =
                    ObjectGenerator.getInstance().getManufacturerObject();
            manufacturerDao.save(manufacturer);
        }
    }

    private void generateCar() {
        for (int i = 0; i < carNumber; i++) {
            Manufacturer manufacturer = manufacturerDao.findById(
                    randOne(manufacturerNumber)
            );
            Car car = ObjectGenerator.getInstance().getCarObject(manufacturer);
            carDao.save(car);
        }
    }

    private void generateTransmission() {
        for (int i = 0; i < transmissionNumber; i++) {
            Transmission transmission =
                    ObjectGenerator.getInstance().getTransmissionObject();
            transmissionDao.save(transmission);
        }
    }

    private void generateBody() {
        for (int i = 0; i < bodyNumber; i++) {
            Body body = ObjectGenerator.getInstance().getBodyObject();
            bodyDao.save(body);
        }
    }

    private void generateOptional() {
        for (int i = 0; i < optionalNumber; i++) {
            Optional optional =
                    ObjectGenerator.getInstance().getOptionalObject();
            optionalDao.save(optional);
        }
    }

    private void generateEngine() {
        for (int i = 0; i < engineNumber; i++) {
            Engine engine =
                    ObjectGenerator.getInstance().getEngineObject();
            engineDao.save(engine);
        }
    }

    private void generateComplectation() {
        for (int i = 0; i < complectationNumber; i++) {
            Transmission transmission = transmissionDao.findById(
                    randOne(transmissionNumber)
            );

            Body body = bodyDao.findById(randOne(bodyNumber));
            Car car = carDao.findById(randOne(carNumber));

            Complectation complectation =
                    ObjectGenerator.getInstance().getComplectationObject(car, transmission, body);
            complectationDao.save(complectation);
        }
    }

    private void bindCompOptional() {
        List<Complectation> complectations = complectationDao.findAll();
        for (Complectation complectation : complectations) {
            for (int i = 0; i < complectationOptionalNumber; i++) {
                complectation.addOptional(optionalDao.findById(
                        randOne(optionalNumber)
                ));
            }
            complectationDao.update(complectation);
        }
    }

    private void bindOptionalComp() {
        List<Optional> optionals = optionalDao.findAll();
        for (Optional optional : optionals) {
            for (int i = 0; i < optionalComplectationNumber; i++) {
                optional.addComplectation(complectationDao.findById(
                        randOne(complectationNumber)
                ));
            }
            optionalDao.update(optional);
        }
    }

    private void bindCompEngine() {
        List<Complectation> complectations = complectationDao.findAll();
        for (Complectation complectation : complectations) {
            for (int i = 0; i < complectationEngineNumber; i++) {
                complectation.addEngine(engineDao.findById(
                        randOne(engineNumber)
                ));
            }
            complectationDao.update(complectation);
        }
    }

    private void bindEngineComp() {
        List<Engine> engines = engineDao.findAll();
        for (Engine engine : engines) {
            for (int i = 0; i < engineComplectationNumber; i++) {
                engine.addComplectation(complectationDao.findById(
                        randOne(complectationNumber)
                ));
            }
            engineDao.update(engine);
        }
    }
}
