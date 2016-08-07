package be.kifaru.examples;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestName;

/**
 * @author Devid Verfaillie
 * @since 2015-09-25
 */
@Category(SlowTestsCategory.class)
public class SlowClassTest {

    // The JUnit category can be used on class level (= this test class) and on method level (= SlowMethodTest class).

    @Rule
    public TestName name = new TestName();

    @Test
    public void aSlowRunningTestClass() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.printf("Executing %s.%s()%n", getClass().getSimpleName(), name.getMethodName());

            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200));
        }
    }
}
