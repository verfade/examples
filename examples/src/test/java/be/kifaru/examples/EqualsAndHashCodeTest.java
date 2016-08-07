package be.kifaru.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Tests {@link EqualsAndHashCode}.
 *
 * @author Devid Verfaillie
 * @since 2015-09-12
 */
public class EqualsAndHashCodeTest {

    /**
     * See Effective Java 2nd Edition, item 8 (Obey the general contract when overriding equals).
     */
    @Test
    public void equals_aCompletelyDifferentClass_shouldReturnFalse() throws Exception {
        EqualsAndHashCode instance = new EqualsAndHashCode("a1", "b2", "c3");

        //noinspection EqualsBetweenInconvertibleTypes
        assertThat("should not be equal to a completely different class",      // EOL comment to help the code formatter
                   instance.equals(Integer.valueOf(1)), is(false));
    }

    /**
     * See Effective Java 2nd Edition, item 8 (Obey the general contract when overriding equals).
     */
    @Test
    public void equals_nonNullity_shouldNotBeEqualToNull() throws Exception {
        EqualsAndHashCode instance = new EqualsAndHashCode("a1", "b2", "c3");
        assertThat("should not be equal to null", instance, is(not(equalTo(null))));
    }

    /**
     * See Effective Java 2nd Edition, item 8 (Obey the general contract when overriding equals).
     */
    @Test
    public void equals_reflexivity_shouldBeEqualToItself() throws Exception {
        EqualsAndHashCode instance = new EqualsAndHashCode("a1", "b2", "c3");
        assertThat("should be reflexive", instance, is(equalTo(instance)));
    }

    /**
     * See Effective Java 2nd Edition, item 8 (Obey the general contract when overriding equals).
     */
    @Test
    public void equals_sameField1And2DifferentField3_shouldReturnFalse() throws Exception {
        EqualsAndHashCode instance_1 = new EqualsAndHashCode("a1", "b2", "c3");
        EqualsAndHashCode instance_2 = new EqualsAndHashCode("a1", "b2", "different");
        assertThat("should not be equal", instance_1, is(not(equalTo(instance_2))));
    }

    /**
     * See Effective Java 2nd Edition, item 8 (Obey the general contract when overriding equals).
     */
    @Test
    public void equals_sameField2And3DifferentField1_shouldReturnFalse() throws Exception {
        EqualsAndHashCode instance_1 = new EqualsAndHashCode("a1", "b2", "c3");
        EqualsAndHashCode instance_2 = new EqualsAndHashCode("different", "b2", "c3");
        assertThat("should not be equal", instance_1, is(not(equalTo(instance_2))));
    }

    /**
     * See Effective Java 2nd Edition, item 8 (Obey the general contract when overriding equals).
     */
    @Test
    public void equals_symmetry_differentInstancesWithSameState_shouldBeEqualInBothDirections() throws Exception {
        EqualsAndHashCode instance_1 = new EqualsAndHashCode("a1", "b2", "c3");
        EqualsAndHashCode instance_2 = new EqualsAndHashCode("a1", "b2", "c3");
        assertThat("should be symmetric 1 -> 2", instance_1, is(equalTo(instance_2)));
        assertThat("should be symmetric 2 -> 1", instance_2, is(equalTo(instance_1)));
    }

    /**
     * See Effective Java 2nd Edition, item 8 (Obey the general contract when overriding equals).
     */
    @Test
    public void equals_transitivity_differentInstancesWithSameState_shouldBeEqualInAllDirections() throws Exception {
        EqualsAndHashCode instance_1 = new EqualsAndHashCode("a1", "b2", "c3");
        EqualsAndHashCode instance_2 = new EqualsAndHashCode("a1", "b2", "c3");
        EqualsAndHashCode instance_3 = new EqualsAndHashCode("a1", "b2", "c3");
        assertThat("should be transitive 1 -> 2", instance_1, is(equalTo(instance_2)));
        assertThat("should be transitive 2 -> 3", instance_2, is(equalTo(instance_3)));
        assertThat("should be transitive 1 -> 3", instance_1, is(equalTo(instance_3)));
    }

    /**
     * See Effective Java 2nd Edition, items 8 (Obey the general contract when overriding equals) & 9 (Always override
     * hashCode when you override equals).
     */
    @Test
    public void hashCode_differentInstancesWithSameState_shouldProduceSameHashCode() throws Exception {
        EqualsAndHashCode instance_1 = new EqualsAndHashCode("a1", "b2", "c3");
        EqualsAndHashCode instance_2 = new EqualsAndHashCode("a1", "b2", "c3");
        assertThat("should be equal 1 -> 2", instance_1, is(equalTo(instance_2)));
        assertThat("should be equal 2 -> 1", instance_2, is(equalTo(instance_1)));
        assertThat("equal instances should produce same hash code",            // EOL comment to help the code formatter
                   instance_1.hashCode(), is(equalTo(instance_2.hashCode())));
    }
}
