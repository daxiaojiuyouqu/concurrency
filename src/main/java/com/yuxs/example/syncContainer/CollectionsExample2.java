package com.yuxs.example.syncContainer;

import com.google.common.collect.Sets;
import com.yuxs.ConcurrencyTest;
import com.yuxs.annoations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


@NotThreadSafe
public class CollectionsExample2 {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrencyTest.class);

    public static final int clienTotal = 5000;

    public static final int threadTotal = 200;

    public static Set<Integer> set = Collections.synchronizedSet(Sets.newHashSet());

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clienTotal);
        for (int i = 0; i < clienTotal; i++) {
            final int num = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(num);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        logger.info("len={}", set.size());
    }

    private static void add(int i) {
        set.add(i);
    }
}
