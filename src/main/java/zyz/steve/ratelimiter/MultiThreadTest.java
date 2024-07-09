package zyz.steve.ratelimiter;

import java.util.concurrent.Callable;

public class MultiThreadTest {

    class MyCallable implements Callable<String>{

        @Override public String call() {
            return Thread.currentThread().getName();
        }
    }

    public static void main(String[] args) {

    }

}
