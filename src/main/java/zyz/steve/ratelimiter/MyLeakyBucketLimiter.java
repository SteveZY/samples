package zyz.steve.ratelimiter;

import java.time.Clock;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyLeakyBucketLimiter {

    private final int capacity;

    private final Duration period;

    private final int rate;

    private final Clock clock;

    private final Map<String, MyLeakyBucket> users = new ConcurrentHashMap<>(); //Concurrent Hashmap to handle concurrency
    public MyLeakyBucketLimiter(int capacity, Duration period, int rate, Clock clock){
        this.capacity = capacity;
        this.period = period;
        this.rate = rate;
        this.clock = clock;
    }

    public boolean allowed(String userId) {

        MyLeakyBucket bucket = users.computeIfAbsent(userId, k -> new MyLeakyBucket(this.capacity, clock.millis(), 0, clock, rate));
//        System.out.println(bucket + " from "+Thread.currentThread().getName());
        bucket.leak();
        boolean ret = bucket.processed();
//        if (ret) {
//        }
//        System.out.println(Thread.currentThread().getName() + ":" + ret);

        return ret;
    }

}
