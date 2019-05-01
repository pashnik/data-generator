package utils;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static utils.Randomizer.*;

public abstract class Generator {

    private static final Faker generator = new Faker();

    protected Generator() {
    }

    protected static final int MIN_ENGINE_POWER = 150;
    protected static final int MAX_ENGINE_POWER = 500;

    protected static final int MIN_WORKING_VOLUME = 1;
    protected static final int MAX_WORKING_VOLUME = 4;

    protected static final int MIN_ECOLOGICAL_CLASS = 2;
    protected static final int MAX_ECOLOGICAL_CLASS = 5;

    protected static final List<String> TRANSMISSION_TYPE = Arrays.asList("Mechanical",
            "Automatic");

    protected static final List<String> BODY_TYPE = Arrays.asList("Sedan",
            "Hatchback", "Coupe", "Limousine", "Cabriolet", "Minivan");

    protected static final List<String> ENGINE_TYPE = Arrays.asList("Petrol",
            "Diesel");

    protected static final List<String> POST_TYPE = Arrays.asList("Repair",
            "Story", "Improvement");

    protected String getLogin() {
        return generator.name().username();
    }

    protected String getPassword() {
        return generator.internet().password();
    }

    protected String getEmail() {
        return generator.internet().emailAddress();
    }

    protected String getManufacturer() {
        return generator.company().name();
    }

    protected String getCountry() {
        return generator.address().country();
    }

    protected String getCar() {
        return generator.address().lastName();
    }

    protected String getOptional() {
        return generator.address().firstName();
    }

    protected String getDescription() {
        return generator.lorem().sentence();
    }

    protected String getTransmission() {
        return TRANSMISSION_TYPE.get(
                listRand(TRANSMISSION_TYPE.size())
        );
    }

    protected String getBody() {
        return BODY_TYPE.get(
                listRand(BODY_TYPE.size())
        );
    }

    protected String getEngine() {
        return ENGINE_TYPE.get(
                listRand(ENGINE_TYPE.size())
        );
    }

    protected String getPost() {
        return POST_TYPE.get(
                listRand(POST_TYPE.size())
        );
    }

    protected int getWorkingVolume() {
        return rand(MIN_WORKING_VOLUME, MAX_WORKING_VOLUME);
    }

    protected int getEnginePower() {
        return rand(MIN_ENGINE_POWER, MAX_ENGINE_POWER);
    }

    protected int getEcologicalClass() {
        return rand(MIN_ECOLOGICAL_CLASS, MAX_ECOLOGICAL_CLASS);
    }

    protected Date getDate() {
        return randDate(rand(MIN_ENGINE_POWER, MAX_ENGINE_POWER));
    }

    protected int getViewsNumber() {
        return rand(1, Integer.MAX_VALUE - 1);
    }
}