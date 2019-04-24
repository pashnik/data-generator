package generator;

import dao.*;
import models.*;
import org.hibernate.Session;
import utils.HibernateSessionFactory;
import utils.ObjectGenerator;
import utils.objectResources.DataConfigResource;

import static utils.Randomizer.*;

import java.util.List;
import java.util.stream.Stream;

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
        Stream.generate(new IntegerStreamSupplier())
                .limit(manufacturerNumber)
                .forEach(i -> {
                    Manufacturer manufacturer =
                            ObjectGenerator.getInstance().getManufacturerObject();
                    manufacturerDao.save(manufacturer);
                });
    }

    private void generateCar() {
        Stream.generate(new IntegerStreamSupplier())
                .limit(carNumber)
                .forEach(i -> {
                    Manufacturer manufacturer = manufacturerDao.findById(
                            randOne(manufacturerNumber)
                    );
                    Car car = ObjectGenerator.getInstance().getCarObject(manufacturer);
                    carDao.save(car);
                });
    }

    private void generateTransmission() {
        Stream.generate(new IntegerStreamSupplier())
                .limit(transmissionNumber)
                .forEach(i -> {
                    Transmission transmission =
                            ObjectGenerator.getInstance().getTransmissionObject();
                    transmissionDao.save(transmission);
                });
    }

    private void generateBody() {
        Stream.generate(new IntegerStreamSupplier())
                .limit(bodyNumber)
                .forEach(i -> {
                    Body body = ObjectGenerator.getInstance().getBodyObject();
                    bodyDao.save(body);
                });
    }

    private void generateOptional() {
        Stream.generate(new IntegerStreamSupplier())
                .limit(optionalNumber)
                .forEach(i -> {
                    Optional optional =
                            ObjectGenerator.getInstance().getOptionalObject();
                    optionalDao.save(optional);
                });
    }

    private void generateEngine() {
        Stream.generate(new IntegerStreamSupplier())
                .limit(engineNumber)
                .forEach(i -> {
                    Engine engine =
                            ObjectGenerator.getInstance().getEngineObject();
                    engineDao.save(engine);
                });
    }

    private void generateComplectation() {
        Stream.generate(new IntegerStreamSupplier())
                .limit(complectationNumber)
                .forEach(i -> {
                    Transmission transmission = transmissionDao.findById(
                            randOne(transmissionNumber)
                    );

                    Body body = bodyDao.findById(randOne(bodyNumber));
                    Car car = carDao.findById(randOne(carNumber));

                    Complectation complectation =
                            ObjectGenerator.getInstance().getComplectationObject(car, transmission, body);
                    complectationDao.save(complectation);
                });
    }

    private void bindCompOptional() {
        List<Complectation> complectations = complectationDao.findAll();
        complectations.forEach(complectation -> {
            Stream.generate(new IntegerStreamSupplier())
                    .limit(complectationOptionalNumber)
                    .forEach(i ->
                            complectation.addOptional(optionalDao.findById(
                                    randOne(optionalNumber)
                            )));
            complectationDao.update(complectation);
        });
    }

    private void bindOptionalComp() {
        List<Optional> optionals = optionalDao.findAll();
        optionals.forEach(optional -> {
            Stream.generate(new IntegerStreamSupplier())
                    .limit(optionalComplectationNumber)
                    .forEach(i ->
                            optional.addComplectation(complectationDao.findById(
                                    randOne(complectationNumber)
                            )));
            optionalDao.update(optional);
        });
    }

    private void bindCompEngine() {
        List<Complectation> complectations = complectationDao.findAll();
        complectations.forEach(complectation -> {
            Stream.generate(new IntegerStreamSupplier())
                    .limit(complectationEngineNumber)
                    .forEach(i ->
                            complectation.addEngine(engineDao.findById(
                                    randOne(engineNumber)
                            )));
            complectationDao.update(complectation);
        });
    }

    private void bindEngineComp() {
        List<Engine> engines = engineDao.findAll();
        engines.forEach(engine -> {
            Stream.generate(new IntegerStreamSupplier())
                    .limit(engineComplectationNumber)
                    .forEach(i -> engine.addComplectation(complectationDao.findById(
                            randOne(complectationNumber)
                    )));
            engineDao.update(engine);
        });
    }

    private void generateAdverts() {
        // TODO
    }

    private void generatePostTypes() {
        // TODO
    }

    private void generateUsers() {
        // TODO
    }

    private void generatePosts() {
        // TODO
    }

    private void generateUsersOwnership() {
        // TODO
    }


}
