package be.kifaru.examples;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * @author Devid Verfaillie
 * @since 2015-09-25
 */
public class SlowMethodTest {

    // The JUnit category can be used on class level and on method level.

    @Test
    public void aFastRunningTestMethod() throws Exception {
        System.out.println("Executing SlowMethodTest.aFastRunningTestMethod");
    }

    @Test
    @Category(SlowTestsCategory.class)
    public void aSlowRunningTestMethod() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println("Executing SlowMethodTest.aSlowRunningTestMethod");

            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200));
        }
    }
}
