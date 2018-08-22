package com.yuxs.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample2 {

    public final static Logger logger = LoggerFactory.getLogger(SemaphoreExample2.class);

    private final static int threadCount = 20;

    /**
     * 这个示例表示有20个线程,每次放行2个线程执行,但是每次获取2个许可才执行,所以相当于单线程在执行
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire(2);//获取多个许可
                    test(threadNum);
                    semaphore.release(2);//释放多个许可
                } catch (InterruptedException e) {
                    logger.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadCount) throws InterruptedException {
        logger.info("{}", threadCount);
        Thread.sleep(1000);

    }
}
