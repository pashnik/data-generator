package generator;

import java.util.function.Supplier;

public class IntegerStreamSupplier implements Supplier<Integer> {

    private int counter;

    @Override
    public Integer get() {
        return counter++;
    }
}
