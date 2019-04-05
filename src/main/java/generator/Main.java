package generator;

public class Main {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator(7, 100,
                40, 2, 4, 2, 30,
                1, 12, 14,
                2);
        dataGenerator.generate();
    }
}
