package jdk.j11;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Emac
 * @since 2020-06-06
 */
public class HttpClientTests {

    @Test
    public void testOldGet() throws IOException {
        var url = new URL("http://www.baidu.com");
        var connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        assertEquals(200, connection.getResponseCode());

        var body = new StringBuilder();
        var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        var line = "";
        while ((line = reader.readLine()) != null) {
            body.append(line);
            body.append("\n");
        }
        reader.close();
        connection.disconnect();
        System.out.println(body.toString());
    }

    @Test
    public void testGet() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var req = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).GET().build();
        var resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(resp.body());
        assertEquals(200, resp.statusCode());
    }

    @Test
    public void testAsyncGet() throws ExecutionException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var req = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).GET().build();
        var resp = client.sendAsync(req, HttpResponse.BodyHandlers.ofString()).get();
        System.out.println(resp.body());
        assertEquals(200, resp.statusCode());
    }

    @Test
    public void testHttpsGet() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var req = HttpRequest.newBuilder(URI.create("https://www.baidu.com")).GET().build();
        var resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(resp.body());
        assertEquals(200, resp.statusCode());
    }

    @Test
    public void testMultiGet() {
        var client = HttpClient.newHttpClient();
        List<CompletableFuture<HttpResponse<String>>> respFutures = Stream.of("http://www.baidu.com", "https://www.baidu.com", "http://baidu.com", "https://www.baidu.com")
                .parallel()
                .map(url -> {
                    var req = HttpRequest.newBuilder(URI.create("https://www.baidu.com")).GET().build();
                    return client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
                })
                .collect(Collectors.toList());
        var counter = new AtomicInteger();
        respFutures.stream().forEach(f -> f.whenComplete((resp, err) -> {
            counter.incrementAndGet();
        }));
        CompletableFuture.allOf(respFutures.toArray(CompletableFuture[]::new)).join();
        assertEquals(4, counter.get());
    }

    @Test
    public void testHttp2Get() throws IOException, InterruptedException {
        var client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        var req = HttpRequest.newBuilder(URI.create("https://http2.akamai.com/demo")).GET().build();
        var resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(resp.body());
        assertEquals(200, resp.statusCode());
    }

    @Test
    public void testWebSocket() throws InterruptedException {
        var client = HttpClient.newHttpClient();
        var counter = new AtomicInteger();
        var latch = new CountDownLatch(1);
        WebSocket ws = client.newWebSocketBuilder().buildAsync(URI.create("wss://echo.websocket.org/"), new WebSocket.Listener() {
            @Override
            public void onOpen(WebSocket webSocket) {
                System.out.println("onOpen");
                counter.incrementAndGet();
                webSocket.request(1);
            }

            @Override
            public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                System.out.println("onText: " + data);
                counter.incrementAndGet();
                webSocket.request(1);
                return null;
            }

            @Override
            public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
                System.out.println("onPing: " + new String(message.array()));
                webSocket.sendPong(ByteBuffer.wrap("Pong".getBytes()));
                counter.incrementAndGet();
                webSocket.request(1);
                return null;
            }

            @Override
            public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
                System.out.println("onPong: " + new String(message.array()));
                counter.incrementAndGet();
                webSocket.request(1);
                return null;
            }

            @Override
            public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
                System.out.println("OnClose: " + reason);
                counter.incrementAndGet();
                latch.countDown();
                return null;
            }
        }).join();
        ws.sendPing(ByteBuffer.wrap("Ping".getBytes()));
        ws.sendText("Hello", true);
        ws.sendClose(WebSocket.NORMAL_CLOSURE, "Bye").join();
        latch.await();
        assertEquals(4, counter.get());
    }

    @Test
    public void testReactive() throws InterruptedException, ExecutionException {
        var client = HttpClient.newHttpClient();
        var req = HttpRequest.newBuilder(URI.create("https://www.baidu.com")).GET().build();

        HttpResponse.BodySubscriber<Void> sub = HttpResponse.BodySubscribers.fromLineSubscriber(new Flow.Subscriber<>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("[sub] Start receiving data ...");
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("[sub] Received: " + item);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("[sub] Error occurred: " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("[sub] Completed!");
            }
        });
        var resp = client.sendAsync(req, r -> sub).get();
        System.out.println(resp.body());
        assertEquals(200, resp.statusCode());
    }
}
