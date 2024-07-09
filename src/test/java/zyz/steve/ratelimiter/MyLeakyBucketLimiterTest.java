package zyz.steve.ratelimiter;

import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

public class MyLeakyBucketLimiterTest {

    @Test
    public void testRateLimiter() throws InterruptedException, ExecutionException {

        Clock clk = mock(Clock.class);

        int req = 13;
        int loops = 10000;
        int[] cnt = new int[10];
        while (loops > 0) {
            when(clk.millis()).thenReturn(0L, 0L, 0L, 0L, 0L, 0L, 0L, 1181L, 1182L, 1182L, 1181L, 1181L, 1181L);
            MyLeakyBucketLimiter limiter = new MyLeakyBucketLimiter(3, Duration.ofSeconds(1), 2, clk);
            CompletableFuture<?>[] fs = IntStream.range(0, req)
                    .mapToObj(i -> CompletableFuture.supplyAsync(() -> limiter.allowed("alice")))
                    .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(fs).get();
            long numAllowed = Arrays.stream(fs).filter(f -> {
                try {
                    return (Boolean) (f.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }).count();
            cnt[(int) numAllowed]++;
            if (numAllowed > 5 || numAllowed < 3) {
                System.out.println(numAllowed + " at loop#" + loops);
            }
            Assert.assertTrue(5 >= numAllowed);
            Assert.assertTrue(3 <= numAllowed);
            loops--;
        }

        System.out.println("check req nums");
        for (int e : cnt) {
            System.out.println(e);
        }
    }

}