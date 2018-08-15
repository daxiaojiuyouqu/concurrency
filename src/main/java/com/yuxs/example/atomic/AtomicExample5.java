package com.yuxs.example.atomic;

import com.yuxs.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yuxsh
 * @date 2018/4/23.
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static final Logger logger = LoggerFactory.getLogger(AtomicExample5.class);

    private static final int clienTotal = 5000;

    private static final int threadTotal = 200;

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clienTotal);
        for (int i = 0; i < clienTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        logger.info("isHappened={}",isHappened.get());

    }

    private static void test(){
        if(isHappened.compareAndSet(false,true)){
            logger.info("execute");
        }
    }
}
