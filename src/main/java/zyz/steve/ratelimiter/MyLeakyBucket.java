package zyz.steve.ratelimiter;

import java.time.Clock;

public class MyLeakyBucket {

    final private int capacity;

    private final Clock clock;

    private final int rate;

    private long curLevel;
     private long leakLast;
    public MyLeakyBucket(int capacity, long timestamp, long curLevel, Clock clock, int rate){
        this.capacity= capacity;
        this.leakLast = timestamp;
        this.curLevel = curLevel;
        this.clock = clock;
        this.rate = rate;
    }
     synchronized
     public void leak(){//can be synchronized
        long now = clock.millis();
//        System.out.println(Thread.currentThread().getName()+":"+now);
        long secPassed = (now - leakLast)/1000;
        long numToBeLeaked = secPassed*rate;
        if(numToBeLeaked>0){
            curLevel = Math.max(0, curLevel -numToBeLeaked);
            leakLast = now;
        }
    }
     synchronized
     public boolean processed(){//can be synchronized
        if(curLevel < capacity){
            ++curLevel;
            return true;
        }else {
            return false;
        }
    }

}
