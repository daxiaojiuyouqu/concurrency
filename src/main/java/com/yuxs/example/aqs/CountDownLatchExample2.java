package com.yuxs.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample2 {

    public final static Logger logger = LoggerFactory.getLogger(CountDownLatchExample2.class);

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
        countDownLatch.await(10,TimeUnit.MILLISECONDS);//支持给定时间的等待,需要注意的是虽然不等待了,但是之前给定的线程还是会执行完
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
