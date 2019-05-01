package utils;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

/**
 * Generates a random value at certain intervals.
 *
 * @author pashnik
 */
public final class Randomizer {

    private static final Random random = new Random();

    private Randomizer() {
    }

    /**
     * @param from inteval start
     * @param to   interval end
     * @return random value in the interval from {@code from} to {@code to}
     * including boundary values.
     */
    public static int rand(int from, int to) {
        return random.nextInt(to - from + 1) + from;
    }

    /**
     * @param to interval end
     * @return random value in the interval from zero to {@code to}.
     */
    public static int randZero(int to) {
        return rand(0, to);
    }

    /**
     * Special random index for lists.
     *
     * @param to interval end
     * @return random value in the interval from zero to {@code to} - 1.
     */
    public static int listRand(int to) {
        return rand(0, to - 1);
    }

    /**
     * @param to interval end
     * @return random value in the interval from one to {@code to}.
     */
    public static int randOne(int to) {
        if (to == 0) return rand(1, to + 1);
        return rand(1, to);
    }

    public static Date randDate(int time) {
        return new Date(time);
    }
}
