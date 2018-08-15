package com.yuxs.example.atomic;

import com.yuxs.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author yuxsh
 * @date 2018/4/23.
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static final Logger logger = LoggerFactory.getLogger(AtomicExample4.class);

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2);
        count.compareAndSet(1, 3);
        count.compareAndSet(3, 6);
        count.compareAndSet(2, 4);
        count.compareAndSet(6, 9);
        logger.info("count={}", count.get());
    }
}
