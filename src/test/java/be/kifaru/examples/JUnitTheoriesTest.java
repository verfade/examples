package be.kifaru.examples;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static be.kifaru.examples.BirdsEnum.COMMON_KESTREL;
import static be.kifaru.examples.BirdsEnum.ROOSTER;
import static be.kifaru.examples.BirdsEnum.fromString;
import static be.kifaru.examples.BirdsEnum.fromStringNotNull;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assume.*;

/**
 * Describes how to use JUnit {@link Theory} and {@link DataPoints}.
 *
 * @author Devid Verfaillie
 * @since 2015-09-11
 */
@RunWith(Theories.class)
public class JUnitTheoriesTest {

    // constant for readability
    private static final String NULL_STRING = null;

    // constant for readability
    private static final String EMPTY_STRING = "";

    // constant for readability
    private static final String BLANK_STRING = " ";

    // =================================================================================================================

    @DataPoints("invalidNamesStringArray")
    public static String[] dataPoints_invalidNamesStringArray =
            new String[] {NULL_STRING, EMPTY_STRING, BLANK_STRING, "dummy data"};

    /**
     * Using {@link DataPoints} is usually better than specifying separate methods per case.
     * <p/>
     * Strangely enough you can and must combine @Theory and @Test to use the 'expected' parameter, else you can use the
     * {@link ExpectedException} rule.
     */
    @Theory
    @Test(expected = IllegalArgumentException.class)
    public void fromStringNotNull_invalidArgument_shouldThrowIllegalArgumentException(
            @FromDataPoints("invalidNamesStringArray") String name) {
//        System.out.println("name = [" + name + "]");
        fromStringNotNull(name);
    }

    // =================================================================================================================

    /**
     * DataPoints do not need to be a String array from JUnit 4.12.
     */
    @DataPoints("ints")
    public static int[] dataPoints_ints() {
        return new int[] {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};
    }

    // =================================================================================================================

    @DataPoints("invalidNamesStringList")
    public static List<String> dataPoints_invalidNamesStringList() {
        return Arrays.asList(NULL_STRING, EMPTY_STRING, BLANK_STRING, "dummy data");
    }

    /**
     * Using @DataPoints is usually better than specifying separate methods per case.
     */
    @Theory
    public void fromString_invalidArgument_shouldReturnNull(@FromDataPoints("invalidNamesStringList") String name) {
//        System.out.println("name = [" + name + "]");
        assertThat(fromString(name), is(nullValue()));
    }

    @Theory
    public void fromString_invalidArgument_usingAssume_shouldReturnNull(
            @FromDataPoints("invalidNamesStringList") String name) {
//        System.out.println("before Assume: name = [" + name + "]");

        assumeThat(name, is(notNullValue()));
        assumeThat(name.trim(), is(not(isEmptyString())));
//        System.out.println(" after Assume: name = [" + name + "]");

        assertThat(fromString(name), is(nullValue()));
    }

    // =================================================================================================================

    @DataPoints("namesAndExpectedResults")
    public static Object[][] dataPoints_namesAndExpectedResults() {
        return new Object[][] {
                {NULL_STRING, null},                                           // EOL comment to help the code formatter
                {EMPTY_STRING, null},                                          // EOL comment to help the code formatter
                {BLANK_STRING, null},                                          // EOL comment to help the code formatter
                {"dummy data", null},                                          // EOL comment to help the code formatter
                {"common kestrel", COMMON_KESTREL},                            // EOL comment to help the code formatter
                {"Falco tinnunculus", COMMON_KESTREL},                         // EOL comment to help the code formatter
                {"Gallus gallus", ROOSTER},                                    // EOL comment to help the code formatter
                {"rooster", ROOSTER},                                          // EOL comment to help the code formatter
        };
    }

    @Theory
    public void fromString_mixedArgument_shouldReturnExpectedResult(
            @FromDataPoints("namesAndExpectedResults") Object[] nameAndExpectedResult) {
        String name = (String) nameAndExpectedResult[0];
        BirdsEnum expectedResult = (BirdsEnum) nameAndExpectedResult[1];
//        System.out.println("[" + name + "] => [" + expectedResult + "] ?");
        assertThat(fromString(name), is(expectedResult));
    }
}
