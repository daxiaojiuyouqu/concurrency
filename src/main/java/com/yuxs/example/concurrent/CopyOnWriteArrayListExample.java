package com.yuxs.example.concurrent;

import com.yuxs.ConcurrencyTest;
import com.yuxs.annoations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

@ThreadSafe
public class CopyOnWriteArrayListExample {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrencyTest.class);

    public static final int clienTotal = 5000;

    public static final int threadTotal = 200;

    private final static List<Integer> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clienTotal);
        for (int i = 0; i < clienTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        logger.info("size:{}", list.size());
    }

    private static void add(int i) {
        list.add(i);
    }
}
