package com.yuxs.example.count;

import com.yuxs.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author yuxsh
 * @date 2018/4/23.
 */
@Slf4j
@NotThreadSafe
public class CountExample4 {

    private static final Logger logger = LoggerFactory.getLogger(CountExample4.class);

    public static final int clienTotal = 5000;

    public static final int threadTotal = 200;

    public static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clienTotal);
        for (int i = 0; i < clienTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        logger.info("count={}",count);
    }

    private static void add() {
        count++;
        /**
         * 运行结果并不是想要的正确结果,那么为什么使用volatile修饰了计数器还是不能保证线程安全的呢
         * 首先看一下count++做了什么?
         * 1.从主存中取出当前的值
         * 2.执行+1操作
         * 3.将+1后的count刷新到住内存
         * 但是同时有两个线程执行的时候就会出现问题,所以volatile并不能保证线程安全原子性
         * volatile的使用场景:
         * 1.可以表示状态标识符true或false
         *
         */
    }
}
