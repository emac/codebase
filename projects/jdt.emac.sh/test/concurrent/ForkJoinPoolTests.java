package concurrent;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Emac
 * @since 2016-08-31
 */
public class ForkJoinPoolTests {

    @Test
    public void testParallelStream() throws ExecutionException, InterruptedException {
        HashSet<String> threadnames = new HashSet<>();
        IntStream.range(1, 20).parallel().forEach(i -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // ignore
            }
            threadnames.add(Thread.currentThread().getName());
        });
        threadnames.forEach(System.out::println);
        assertEquals(Runtime.getRuntime().availableProcessors(), threadnames.size());

        threadnames.clear();

        ForkJoinPool pool = new ForkJoinPool(50);
        pool.submit(() -> IntStream.range(1, 50).parallel().forEach(i -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // ignore
            }
            threadnames.add(Thread.currentThread().getName());
        })).get();
        pool.shutdown();
        threadnames.forEach(System.out::println);
        assertTrue(threadnames.size() > Runtime.getRuntime().availableProcessors());
    }
}
