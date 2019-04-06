package generator;

public class Main {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator(7, 100,
                4, 2, 4, 2, 30,
                1, 1, 1,
                2);
        dataGenerator.generate();
        dataGenerator.stop();
    }
}
