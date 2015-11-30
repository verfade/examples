package be.kifaru.examples;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Devid Verfaillie
 * @since 2015-09-01
 */
public enum BirdsEnum {

    COMMON_KESTREL("common kestrel", "Falco tinnunculus"),

    // someone duplicates a line and forgets to change the values => should be caught by sanity checks below
//    COMMON_KESTREL_COPY("common kestrel", "Falco tinnunculus"),

    ROOSTER("rooster", "Gallus gallus"),

    WHITE_THROATED_DIPPER("white-throated dipper",
                          "someone changed the original value instead of the value in the copy below"
                                  + " => should be caught by BirdsEnumTest#test_knownEnumData_shouldBeValid()"),
    WHITE_THROATED_DIPPER_COPY("a unique name", "Cinclus cinclus"),

    WHITE_STORK("white stork", "Ciconia ciconia"),
    // always have a comma after the last value for easy addition and merging

    // keep this ';' on an empty line for easy merging
    ;

    /**
     * Contains the common name and species (Strings) to Enum mappings.
     */
    private static final Map<String, BirdsEnum> MAPPINGS;

    static {
        Map<String, BirdsEnum> mappings = new HashMap<>();

        for (BirdsEnum enumValue : BirdsEnum.values()) {
            BirdsEnum sanityCheck = mappings.put(enumValue.commonName, enumValue);

            if (null != sanityCheck) {
                String errorMessage = "BirdsEnum common name [%s] from enum [%s] already exists for enum [%s]";
                throw new AssertionError(
                        String.format(errorMessage, enumValue.commonName, enumValue.name(), sanityCheck.name()));
            }

            sanityCheck = mappings.put(enumValue.species, enumValue);

            if (null != sanityCheck) {
                String errorMessage = "BirdsEnum species [%s] from enum [%s] already exists for enum [%s]";
                throw new AssertionError(
                        String.format(errorMessage, enumValue.species, enumValue.name(), sanityCheck.name()));
            }
        }

        MAPPINGS = Collections.unmodifiableMap(mappings);
    }

    public static BirdsEnum fromString(String key) {
        return MAPPINGS.get(key);
    }

    public static BirdsEnum fromStringNotNull(String key) {
        BirdsEnum result = fromString(key);
        if (null != result) {
            return result;
        }

        throw new IllegalArgumentException(String.format("Unknown search string [%s]", key));
    }

    private final String commonName;

    private final String species;

    BirdsEnum(String commonName, String species) {
        this.commonName = commonName;
        this.species = species;
    }

    /**
     * See Effective Java 2nd Edition, item 10 (Always override toString).
     */
    @Override
    public String toString() {
        // Enum#toString only contains the Enum name. You should override #toString to add more information
        // and hopefully no-one will use #toString when they want #name.

        return "BirdsEnum{" +
                // make sure to add the enum name to the string
                "name='" + name() + '\'' +
                ", commonName='" + commonName + '\'' +
                ", species='" + species + '\'' +
                '}';
    }

    public String getCommonName() {
        return commonName;
    }

    public String getSpecies() {
        return species;
    }
}
