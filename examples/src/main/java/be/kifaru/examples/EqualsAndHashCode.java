package be.kifaru.examples;

import java.util.Objects;

/**
 * @author Devid Verfaillie
 * @since 2015-09-10
 */
public class EqualsAndHashCode {

    private final String field1;

    private final String field2;

    private final String field3;

    public EqualsAndHashCode(String field1, String field2, String field3) {
        Objects.requireNonNull(field1, "field1 cannot be null");
        Objects.requireNonNull(field2, "field2 cannot be null");
        Objects.requireNonNull(field3, "field3 cannot be null");

        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    // =================================================================================================================
    //  Java version <= 6
    // =================================================================================================================

    //    @Override
    public boolean equalsWithJava6(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EqualsAndHashCode)) {
            return false;
        }

        EqualsAndHashCode that = (EqualsAndHashCode) o;

        if (!field1.equals(that.field1)) {
            return false;
        }
        if (!field2.equals(that.field2)) {
            return false;
        }
        return field3.equals(that.field3);
    }

    //    @Override
    public int hashCodeWithJava6() {
        // The value 31 was chosen because it is an odd prime. If it were even and the multiplication overflowed,
        // information would be lost, as multiplication by 2 is equivalent to shifting. The advantage of using a prime
        // is less clear, but it is traditional. A nice property of 31 is that the multiplication can be replaced by a
        // shift and a subtraction for better performance: 31 * i == (i << 5) - i. Modern VMs do this sort of
        // optimization automatically.
        // (Effective Java 2nd Edition, item 9 (Always override hashCode when you override equals).)

        int result = field1.hashCode();
        result = 31 * result + field2.hashCode();
        result = 31 * result + field3.hashCode();
        return result;
    }

    // =================================================================================================================
    //  Java version >= 7
    // =================================================================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EqualsAndHashCode)) {
            return false;
        }
        EqualsAndHashCode that = (EqualsAndHashCode) o;
        return Objects.equals(field1, that.field1) &&
                Objects.equals(field2, that.field2) &&
                Objects.equals(field3, that.field3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, field3);
    }

    /**
     * See Effective Java 2nd Edition, item 10 (Always override toString).
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EqualsAndHashCode{");
        sb.append("field1='").append(field1).append('\'');
        sb.append(", field2='").append(field2).append('\'');
        sb.append(", field3='").append(field3).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
