package zyz.steve.ratelimiter;

import java.util.LinkedList;
import java.util.Queue;

public class LeakyBucket {
    private int capacity; // 桶的容量
    private int leakRate; // 每秒漏出的固定数量
    private Queue<Long> bucket; // 用队列模拟桶

    public LeakyBucket(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.bucket = new LinkedList<>();
    }

    public boolean add(int data) {
        // 先漏掉上次到现在的水量
        leak();

        // 如果桶未满,则加入数据
        if (bucket.size() < capacity) {
            bucket.offer((long)data);
            return true;
        }

        // 桶已满,拒绝加入
        return false;
    }

    private void leak() {
        long currentTime = System.currentTimeMillis();
        long lastLeakTime = bucket.isEmpty() ? currentTime : bucket.peek();

        // 计算漏出的数据量
        long elapsedTime = currentTime - lastLeakTime;
        int leakedData = (int) (elapsedTime / 1000.0 * leakRate);

        // 从队列头部移除漏出的数据
        for (int i = 0; i < leakedData; i++) {
            if (!bucket.isEmpty()) {
                bucket.poll();
            }
        }

        // 如果队列非空,则更新最后一次漏水时间
        if (!bucket.isEmpty()) {
            bucket.offer(currentTime);
        }
    }

    public static void main(String[] args) {
        LeakyBucket bucket = new LeakyBucket(10, 2); // 容量10,每秒漏2个

        for (int i = 0; i < 20; i++) {
            boolean success = bucket.add(i);
            System.out.println("Add " + i + ": " + success);
            try {
                Thread.sleep(500); // 每0.5秒产生一个请求
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}