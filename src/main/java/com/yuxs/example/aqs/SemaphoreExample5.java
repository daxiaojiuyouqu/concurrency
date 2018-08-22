package com.yuxs.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample5 {

    public final static Logger logger = LoggerFactory.getLogger(SemaphoreExample5.class);

    private final static int threadCount = 20;

    /**
     * 这个示例表示有20个线程,每次放行2个线程执行
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
                    if(semaphore.tryAcquire(2,1L,TimeUnit.SECONDS)){//必须获取到两个许可才执行,如果获取不到2个许可,
                                                                                    // 可以留1秒的时间等待获取,如果还是获取不到那就被丢弃了(只在1秒之内的执行)
                        test(threadNum);
                        semaphore.release(2);//释放2个许可(这里如果就释放了一个许可那么就只会执行一次)
                    }
                } catch (InterruptedException e) {
                    logger.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadCount) throws InterruptedException {
        logger.info("{}", threadCount);
    }
}
