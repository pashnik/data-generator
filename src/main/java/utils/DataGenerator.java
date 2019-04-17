package utils;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class DataGenerator {

    private static final Faker generator = new Faker();
    private static final Random randomizer = new Random();

    private DataGenerator() {
    }

    public static final int MIN_ENGINE_POWER = 150;
    public static final int MAX_ENGINE_POWER = 500;

    public static final int MIN_WORKING_VOLUME = 1;
    public static final int MAX_WORKING_VOLUME = 4;

    public static final int MIN_ECOLOGICAL_CLASS = 2;
    public static final int MAX_ECOLOGICAL_CLASS = 5;

    public static final List<String> COUNTRIES = Arrays.asList("Italy",
            "Russia", "Germany", "Japan", "USA", "France");

    public static final List<String> MANUFACTURERS = Arrays.asList("Toyota",
            "BMW", "Mercedes", "Porshe", "Kia", "Lamborgini", "Skoda",
            "Volkswagen", "Nissan", "Pegeuot", "Land Rover");

    public static final List<String> PREFIX_CAR_NAME = Arrays.asList("Ur",
            "Li", "A", "Gl", "Nos", "Liof", "Urus", "Pr", "Gf", "Nt", "Pe",
            "Nc", "Se", "Ee", "Yg", "Ko", "Mze", "Lix");

    public static final List<String> POSTFIX_CAR_NAME = Arrays.asList("U1",
            "R4", "O2", "U0", "C2", "I9", "B2", "C4", "U6", "Ce8",
            "L0", "L8", "H1", "S5");

    public static final List<String> TRANSMISSION_TYPE = Arrays.asList("Mechanical",
            "Automatic");

    public static final List<String> BODY_TYPE = Arrays.asList("Sedan",
            "Hatchback", "Coupe", "Limousine", "Cabriolet", "Minivan");

    public static final List<String> PREFIX_OPTIONAL = Arrays.asList("QNX",
            "Aqu-Sys", "GoodNf", "ElseRouteX", "NouQx", "NGINX-S", "PolQ",
            "Ovi-Oks", "Lopte-L", "Kgq-rf", "Kgv-Ls", "NourentGq", "ListYou",
            "OkoRt-t", "NsbgIO", "LkRw", "ZOPQ-a");

    public static final List<String> POSTFIX_OPTIONAL = Arrays.asList("O8",
            "9I", "Ic", "ce3", "asd2", "iY6", "90", "op1", "La2", "p4", "yt4");

    public static final List<String> ENGINE_TYPE = Arrays.asList("Petrol",
            "Diesel");

    private static final List<String> POST_TYPE = Arrays.asList("Ремонт",
            "Рассказ", "Улучшение");

    public static String getLogin() {
        return generator.name().username();
    }

    public static String getPassword() {
        return generator.internet().password();
    }

    public static String getEmail() {
        return generator.internet().emailAddress();
    }

    public static String getManufacturer() {
        return generator.company().name();
    }

    public static String getCountry() {
        return generator.address().country();
    }

    public static String getCarName() {
        return generator.address().lastName();
    }

    public static String getOptional() {
        return generator.address().firstName();
    }

    public static String getDescription() {
        return generator.lorem().sentence();
    }

    public static String getTransmission() {
        // TODO
        return null;
    }

    public static String getBody() {
        // TODO
        return null;
    }

    public static String getEngine() {
        // TODO
        return null;
    }

    public static String getPostType() {
        // TODO
        return null;
    }

    public static int getWorkingVolume() {
        // TODO
        return 0;
    }

    public static int getEnginePower() {
        // TODO
        return 0;
    }

    public static int getEcologicalClass() {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(generator.lorem().sentence());
    }


}
