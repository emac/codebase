package concurrent;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Emac
 * @since 2016-09-21
 */
public class ThreadPoolExecutorTest {

    @Test
    public void testMaximumPoolSize() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 100, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> System.out.println(Thread.currentThread().getName()));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.yield();
        }
    }
}
