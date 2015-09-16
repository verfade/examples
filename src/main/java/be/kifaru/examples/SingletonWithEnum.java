package be.kifaru.examples;

/**
 * @author Devid Verfaillie
 * @since 2015-09-07
 */
public enum SingletonWithEnum {

    /**
     * Singleton instance of this class.
     * <p/>
     * See Effective Java 2nd Edition, items 3 (Enforce the singleton property with a private constructor or an enum
     * type) & 77 (For instance control, prefer enum types to readResolve).
     */
    INSTANCE;

    // example method...
    public String concat(String a, String b) {
        return a.concat(b);
    }
}
