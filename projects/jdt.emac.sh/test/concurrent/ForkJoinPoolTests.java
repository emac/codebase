package concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

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
        Assert.assertEquals(Runtime.getRuntime().availableProcessors(), threadnames.size());

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
        Assert.assertTrue(threadnames.size() > Runtime.getRuntime().availableProcessors());
    }
}
