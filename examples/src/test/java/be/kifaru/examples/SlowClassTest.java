package be.kifaru.examples;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * @author Devid Verfaillie
 * @since 2015-09-25
 */
@Category(SlowTestsCategory.class)
public class SlowClassTest {

    // The JUnit category can be used on class level and on method level.

    @Test
    public void aSlowRunningTestClass() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println("Executing SlowClassTest.aSlowRunningTestClass");

            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200));
        }
    }
}
