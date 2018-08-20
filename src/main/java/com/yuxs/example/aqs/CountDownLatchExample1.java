package com.yuxs.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample1 {

    public final static Logger logger = LoggerFactory.getLogger(CountDownLatchExample1.class);

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    logger.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        logger.info("finish");
        exec.shutdown();
    }

    private static void test(int threadCount) throws InterruptedException {
        Thread.sleep(1000);
        logger.info("{}", threadCount);
        logger.info("我已经执行了。。。");
        Thread.sleep(1000);
    }
}
