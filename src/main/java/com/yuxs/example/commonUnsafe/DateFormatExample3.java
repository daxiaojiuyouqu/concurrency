package com.yuxs.example.commonUnsafe;

import com.yuxs.ConcurrencyTest;
import com.yuxs.annoations.NotThreadSafe;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class DateFormatExample3 {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrencyTest.class);

    public static final int clienTotal = 5000;

    public static final int threadTotal = 200;

    private final static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clienTotal);
        for (int i = 0; i < clienTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void update() {
        logger.info("date={}", formatter.parseDateTime("20180128").toDate());
    }
}
