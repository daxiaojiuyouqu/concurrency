package com.yuxs.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample4 {

    public final static Logger logger = LoggerFactory.getLogger(SemaphoreExample4.class);

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
                    if(semaphore.tryAcquire(1L,TimeUnit.SECONDS)){//如果获取不到许可,可以留1秒的时间等待获取,如果还是获取不到那就被丢弃了
                        test(threadNum);
                        semaphore.release();//释放一个许可
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
