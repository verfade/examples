package be.kifaru.examples;

/**
 * @author Devid Verfaillie
 * @since 2015-09-07
 */
public class SingletonWithLazyHolder {

    /**
     * Initialization-on-demand holder idiom. Enables a safe, highly concurrent lazy initialization with good
     * performance.
     * <p/>
     * See Effective Java 2nd Edition, item 71 (Use lazy initialization judiciously).
     *
     * @see <a href="http://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom">http://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom</a>
     */
    private static class LazyHolder {

        private static final SingletonWithLazyHolder INSTANCE = new SingletonWithLazyHolder();
    }

    /**
     * Gets the singleton instance.
     *
     * @return the singleton instance.
     */
    public static SingletonWithLazyHolder getInstance() {
        return LazyHolder.INSTANCE;
    }

    private SingletonWithLazyHolder() {
        // prevent instantiation
    }

    // example method...
    public String concat(String a, String b) {
        return a.concat(b);
    }
}
