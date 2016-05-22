package be.kifaru.examples;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Devid Verfaillie
 * @since 2015-09-01
 */
@SuppressWarnings("unused")
public class RandomString {

    public static final String ALPHA_NUMERIC_MIXED_CASE_CHARS =
            "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String HEXADECIMAL_CHARS = "0123456789ABCDEF";

    public static final String PRINTABLE_ASCII_CHARS =
            " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";

    // ==========================================================================
    // Generating the printable ASCII characters instead of using a fixed string:
    // (Cf. https://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters)
    // ==========================================================================
//    public static final String PRINTABLE_ASCII_CHARS;
//
//    private static final int FIRST_PRINTABLE_CHAR = ' ';
//
//    private static final int LAST_PRINTABLE_CHAR = '~';
//
//    static {
//        char[] c = new char[LAST_PRINTABLE_CHAR - FIRST_PRINTABLE_CHAR + 1];
//
//        for (int i = 0; i < c.length; i++) {
//            c[i] = (char) (FIRST_PRINTABLE_CHAR + i);
//        }
//
//        PRINTABLE_ASCII_CHARS = new String(c);
//        System.out.println("PRINTABLE_ASCII_CHARS = [" + PRINTABLE_ASCII_CHARS + "]");
//    }

    private static final Random RANDOM = new Random();

    private static AtomicLong findSeedInRandomClass() {
        try {
            Field seedField = RANDOM.getClass().getDeclaredField("seed");
            seedField.setAccessible(true);
            return (AtomicLong) seedField.get(RANDOM);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Could not get 'seed' field from Random.class", e);
        }
    }

    public static String generateRandomString(int requestedLength) {
        return generateRandomString(ALPHA_NUMERIC_MIXED_CASE_CHARS, requestedLength);
    }

    public static String generateRandomString(String possibleCharacters, int requestedLength) {
        // we could be defensive about the parameters, but meh

        char[] result = new char[requestedLength];

        for (int i = 0; i < requestedLength; i++) {
            int randomCharIndex = RANDOM.nextInt(possibleCharacters.length());
            char randomCharacter = possibleCharacters.charAt(randomCharIndex);
            result[i] = randomCharacter;
        }

        return new String(result);
    }

    /**
     * Gets the seed used by the PRNG. Useful to rerun a (failed) test with the same random values.
     * <p>
     * Note that the seed changes after every Random#next(int) method call so you must call this method up front (e.g.
     * in the @{@link org.junit.Before} method).
     */
    // @VisibleForTesting
    static long getRandomSeed() {
        AtomicLong seedValue = findSeedInRandomClass();
        long scrambledSeed = seedValue.get();
        // there is no need to unscramble as we will set it the same way as we got it (via reflection)
        return scrambledSeed;
    }

    // @VisibleForTesting
    static void setRandomSeed(long scrambledSeed) {
        AtomicLong seedValue = findSeedInRandomClass();
        seedValue.set(scrambledSeed);
    }
}
