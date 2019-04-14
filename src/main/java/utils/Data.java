package utils;

import java.util.Arrays;
import java.util.List;

public class Data {

    private Data() {
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
}
