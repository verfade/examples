package be.kifaru.examples;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static be.kifaru.examples.BirdsEnum.COMMON_KESTREL;
import static be.kifaru.examples.BirdsEnum.ROOSTER;
import static be.kifaru.examples.BirdsEnum.fromString;
import static be.kifaru.examples.BirdsEnum.fromStringNotNull;
import static be.kifaru.examples.BirdsEnum.randomValue;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Tests {@link BirdsEnum}.
 *
 * @author Devid Verfaillie
 * @since 2015-09-01
 */
public class BirdsEnumTest {

    // constant for readability
    private static final String NULL_STRING = null;

    // constant for readability
    private static final String EMPTY_STRING = "";

    // constant for readability
    private static final String BLANK_STRING = " ";

    @BeforeClass
    public static void testEnumInitialization() {
        assertThat(COMMON_KESTREL, is(notNullValue()));
        // At this moment the Enum class is loaded and there was no initializer error.

        // uncomment COMMON_KESTREL_COPY in the enum to test this case
    }

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Test(expected = IllegalArgumentException.class)
    public void fromStringNotNull_blankArgument_shouldThrowIllegalArgumentException() {
        fromStringNotNull(BLANK_STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStringNotNull_emptyArgument_shouldThrowIllegalArgumentException() {
        fromStringNotNull(EMPTY_STRING);
    }

    @Test
    public void fromStringNotNull_knownArgument_shouldReturnValidEnum() {
        assertThat(fromStringNotNull("rooster"), is(ROOSTER));
        assertThat(fromStringNotNull("Gallus gallus"), is(ROOSTER));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStringNotNull_nullArgument_shouldThrowIllegalArgumentException() {
        fromStringNotNull(NULL_STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStringNotNull_unknownArgument_shouldThrowIllegalArgumentException() {
        fromStringNotNull("dummy data");
    }

    @Test
    public void fromString_blankArgument_shouldReturnNull() {
        assertThat(fromString(BLANK_STRING), is(nullValue()));
    }

    @Test
    public void fromString_emptyArgument_shouldReturnNull() {
        assertThat(fromString(EMPTY_STRING), is(nullValue()));
    }

    @Test
    public void fromString_knownArgument_shouldReturnValidEnum() {
        assertThat(fromString("rooster"), is(ROOSTER));
        assertThat(fromString("Gallus gallus"), is(ROOSTER));
    }

    /**
     * This test is the same as the one above but uses an ErrorCollector so that the test does not stop on failures.
     */
    @Test
    public void fromString_knownArgument_shouldReturnValidEnum_usingErrorCollector() {
        errorCollector.checkThat(fromString("rooster"), is(ROOSTER));
        errorCollector.checkThat(fromString("Gallus gallus"), is(ROOSTER));

        // uncomment to see ErrorCollector output for failing tests
//        errorCollector.checkThat(fromString("rooster"), is(COMMON_KESTREL));
//        errorCollector.checkThat(fromString("Gallus gallus"), is(WHITE_STORK));
    }

    @Test
    public void fromString_nullArgument_shouldReturnNull() {
        assertThat(fromString(NULL_STRING), is(nullValue()));
    }

    @Test
    public void fromString_unknownArgument_shouldReturnNull() {
        assertThat(fromString("dummy data"), is(nullValue()));
    }

    @Test
    public void test_knownEnumData_shouldBeValid() {
        assertThat(ROOSTER.getCommonName(), is("rooster"));
        assertThat(ROOSTER.getSpecies(), is("Gallus gallus"));

        // uncomment to test this case, the enum values are already prepared
//        assertThat(BirdsEnum.WHITE_THROATED_DIPPER.getCommonName(), is("white-throated dipper"));
//        assertThat(BirdsEnum.WHITE_THROATED_DIPPER.getSpecies(), is("Cinclus cinclus"));
    }

    /**
     * This test is the same as the one above but uses an ErrorCollector so that the test does not stop on failures.
     */
    @Test
    public void test_knownEnumData_shouldBeValid_usingErrorCollector() {
        errorCollector.checkThat(ROOSTER.getCommonName(), is("rooster"));
        errorCollector.checkThat(ROOSTER.getSpecies(), is("Gallus gallus"));

        // uncomment to test this case, the enum values are already prepared
//        errorCollector.checkThat(BirdsEnum.WHITE_THROATED_DIPPER.getCommonName(), is("white-throated dipper"));
//        errorCollector.checkThat(BirdsEnum.WHITE_THROATED_DIPPER.getSpecies(), is("Cinclus cinclus"));
    }

    @Test
    public void toString_shouldContainEnumName() {
        // regenerating #toString() might leave off the enum name
        BirdsEnum randomBird = randomValue();
        assertThat(randomBird.toString(), containsString(randomBird.name()));
    }
}
