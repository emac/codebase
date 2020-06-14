package jdk.j9;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Emac
 * @since 2020-06-06
 */
public class FlowTests {

    @Test
    public void testSingleSubscriber() throws InterruptedException {
        final var COUNT = 100;
        var counter = new AtomicInteger();
        var latch = new CountDownLatch(1);

        var pub = new SubmissionPublisher<Integer>();
        var sub = new Flow.Subscriber<Integer>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("Start receiving data ...");
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("Received: " + item);
                counter.incrementAndGet();
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error occurred: " + throwable.getMessage());
                latch.countDown();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed!");
                latch.countDown();
            }
        };
        pub.subscribe(sub);
        IntStream.range(0, COUNT).parallel().forEach(pub::submit);
        pub.close();
        latch.await();

        assertEquals(COUNT, counter.get());
    }

    @Test
    public void testMultiSubscriber() throws InterruptedException {
        final var COUNT = 100;
        var counter1 = new AtomicInteger();
        var counter2 = new AtomicInteger();
        var latch = new CountDownLatch(2);

        var pub = new SubmissionPublisher<Integer>();
        var sub1 = new Flow.Subscriber<Integer>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("[sub1] Start receiving data ...");
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("[sub1] Received: " + item);
                counter1.incrementAndGet();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("[sub1] Error occurred: " + throwable.getMessage());
                latch.countDown();
            }

            @Override
            public void onComplete() {
                System.out.println("[sub1] Completed!");
                latch.countDown();
            }
        };
        var sub2 = new Flow.Subscriber<Integer>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("[sub2] Start receiving data ...");
                this.subscription = subscription;
                this.subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("[sub2] Received: " + item);
                counter2.incrementAndGet();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("[sub2] Error occurred: " + throwable.getMessage());
                latch.countDown();
            }

            @Override
            public void onComplete() {
                System.out.println("[sub2] Completed!");
                latch.countDown();
            }
        };
        pub.subscribe(sub1);
        pub.subscribe(sub2);
        IntStream.range(0, COUNT).parallel().forEach(pub::submit);
        while (counter2.get() != COUNT) {
            Thread.onSpinWait();
        }
        pub.closeExceptionally(new RuntimeException("Stop immediately after sub2 is completed!"));
        latch.await();

        assertTrue(counter1.get() < COUNT);
    }

    @Test
    public void testBackpressure() throws InterruptedException {
        final var COUNT = Flow.defaultBufferSize() + 10;
        var counter = new AtomicInteger();
        var latch = new CountDownLatch(1);

        var pub = new SubmissionPublisher<Integer>();
        var sub = new Flow.Subscriber<Integer>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("[sub] Start receiving data ...");
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("[sub] Received: " + item);
                counter.incrementAndGet();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("[sub] Error occurred: " + throwable.getMessage());
                latch.countDown();
            }

            @Override
            public void onComplete() {
                System.out.println("[sub] Completed!");
                latch.countDown();
            }
        };
        pub.subscribe(sub);
        IntStream.range(0, COUNT).forEach(i -> {
            System.out.println("[pub] Send: " + i);
            pub.submit(i);
        });
        pub.close();
        latch.await();

        assertEquals(COUNT, counter.get());
    }
}
