package utils;

import models.*;

import java.util.Date;

public final class ObjectGenerator extends Generator {

    private static ObjectGenerator objectGenerator;

    private ObjectGenerator() {
        super();
    }

    public static ObjectGenerator getInstance() {
        if (objectGenerator == null) {
            objectGenerator = new ObjectGenerator();
        }
        return objectGenerator;
    }

    public Manufacturer getManufacturerObject() {
        String manufacturerName = getManufacturer();
        String countryName = getCountry();
        return new Manufacturer(countryName, manufacturerName);
    }

    public Car getCarObject(Manufacturer manufacturer) {
        String carName = getCar();
        return new Car(carName, manufacturer);
    }

    public Transmission getTransmissionObject() {
        String transmissionType = getTransmission();
        return new Transmission(transmissionType);
    }

    public Body getBodyObject() {
        String bodyType = getBody();
        return new Body(bodyType);
    }

    public Optional getOptionalObject() {
        String mediaCenter = getOptional();
        String airBags = getOptional();
        String emergencySystem = getOptional();
        String headlights = getOptional();
        String securityAlarm = getOptional();
        return new Optional(mediaCenter, airBags, emergencySystem, headlights, securityAlarm);
    }

    public Engine getEngineObject() {
        int power = getEnginePower();
        int workingVolume = getWorkingVolume();
        String engineType = getEngine();
        int ecologicalClass = getEcologicalClass();
        return new Engine(power, engineType, workingVolume, ecologicalClass);
    }

    public Complectation getComplectationObject(Car car, Transmission transmission, Body body) {
        return new Complectation(car, transmission, body);
    }

    public Users generateUserObject() {
        String login = getLogin();
        String password = getPassword();
        String email = getEmail();
        return new Users(login, password, email);
    }


    public PostTypes generatePostTypeObject() {
        String type = getPost();
        return new PostTypes(type);
    }

    public Posts generatePostObject(PostTypes type, Car car) {
        String description = getDescription();
        return new Posts(description, type, car);
    }

    public UsersOwnership generateUsersOwnershipObject(Users user, Car car) {
        Date from = getDate();
        Date to = getDate();
        return new UsersOwnership(user, car, from, to);
    }

    public Adverts generateAdvertObject(UsersOwnership ownership) {
        int viewsNumber = getViewsNumber();
        return new Adverts(viewsNumber, ownership);
    }

    public SaleHistory generateSaleHistory(Complectation complectation,
                                           Engine engine, Optional optional) {
        double price = 12345.1234 + getViewsNumber();
        return new SaleHistory(complectation, price, engine, optional);
    }

}
