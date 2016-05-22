package be.kifaru.examples;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static be.kifaru.examples.RandomString.RANDOM;
import static be.kifaru.examples.RandomString.generateRandomString;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assume.*;

/**
 * Tests {@link RandomString}.
 *
 * @author Devid Verfaillie
 * @since 2015-09-01
 */
@RunWith(Theories.class)
public class RandomStringTest {

    private static AtomicLong findSeedInRandomClass(Random randomInstance) {
        try {
            Field seedField = randomInstance.getClass().getDeclaredField("seed");
            seedField.setAccessible(true);
            return (AtomicLong) seedField.get(randomInstance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Could not get 'seed' field from Random.class", e);
        }
    }

    /**
     * Gets the seed used by the PRNG. Useful to rerun a (failed) test with the same random values.
     * <p>
     * Note that the seed changes after every Random#next(int) method call so you must call this method up front (e.g.
     * in the @{@link org.junit.Before} method).
     */
    private static long getRandomSeed(Random randomInstance) {
        AtomicLong seedValue = findSeedInRandomClass(randomInstance);
        long scrambledSeed = seedValue.get();
        // there is no need to unscramble as we will set it the same way as we got it (via reflection)
        return scrambledSeed;
    }

    private static void setRandomSeed(Random randomInstance, long scrambledSeed) {
        AtomicLong seedValue = findSeedInRandomClass(randomInstance);
        seedValue.set(scrambledSeed);
    }

    private long randomSeed;

    @Before
    public void before() throws Exception {
        // set the random seed to rerun a (failed) test with the same random values
//        setRandomSeed(60862971477113L);
        randomSeed = getRandomSeed(RANDOM);
    }

//    @Test(expected = OutOfMemoryError.class)
//    public void generateRandomString_withIntegerMaxValueLength_shouldThrowOutOfMemoryError() {
//        int expectedLength = Integer.MAX_VALUE;
//        generateRandomString(expectedLength);
//    }

    @Theory
    public void generateRandomString_twice_shouldReturnDifferentStrings(@FromDataPoints("validLengths") int length) {
        // length "0" creates empty strings which are equal to each other
        // length "1" has a quite high change of collisions
        assumeThat(length, is(greaterThan(1)));
        String randomString1 = generateRandomString(length);
        String randomString2 = generateRandomString(length);
        assertThat("randomString1=[" + randomString1 + "] should not be equal to randomString2=[" + randomString2
                           + "], random seed=[" + randomSeed + "]", randomString1, is(not(equalTo(randomString2))));
    }

    @DataPoints("negativeLengths")
    public static int[] dataPoints_negativeLengths() {
        // bounds checking
        return new int[] {Integer.MIN_VALUE, -50, -2, -1};
    }

    @Test(expected = NegativeArraySizeException.class)
    @Theory
    public void generateRandomString_withNegativeLength_shouldThrowNegativeArraySizeException(
            @FromDataPoints("negativeLengths") int length) {
        generateRandomString(length);
    }

    @DataPoints("validLengths")
    public static int[] dataPoints_validLengths() {
        // bounds checking
        return new int[] {0, 1, 2, 10, 20, 50, 1000};
    }

    @Theory
    public void generateRandomString_withValidLength_shouldReturnValidString(
            @FromDataPoints("validLengths") int length) {
        String randomString = generateRandomString(length);
        assertThat(randomString, is(notNullValue()));
        assertThat(randomString.length(), is(length));
    }

    @Test
    public void getAndSetRandomSeed_generatedStringsShouldBeEqual() {
        int length = 10;
        String randomStringBeforeSetRandomSeed_1 = generateRandomString(length);
        String randomStringBeforeSetRandomSeed_2 = generateRandomString(length);

        setRandomSeed(RANDOM, randomSeed);

        String randomStringAfterSetRandomSeed_1 = generateRandomString(length);
        String randomStringAfterSetRandomSeed_2 = generateRandomString(length);

        assertThat(randomStringBeforeSetRandomSeed_1, is(equalTo(randomStringAfterSetRandomSeed_1)));
        assertThat(randomStringBeforeSetRandomSeed_2, is(equalTo(randomStringAfterSetRandomSeed_2)));
    }
}
