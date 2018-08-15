package com.yuxs.example.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yuxsh
 * @date 2018/5/29.
 * 修饰类,修饰静态方法
 */
public class SynchronizedExample2 {

    private static final Logger log = LoggerFactory.getLogger(SynchronizedExample2.class);

    //修饰一个代码块(作用于调用对象)
    public static void test1(int j) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1,{}-{}", j, i);
            }
        }
    }

    //修饰一个方法(作用于调用对象),不同的调用对象之间是互不影响的,
    // 意思就是两个调用对象都调用同一个方法,但是一个对象调用该方法时,这个方法内部执行顺序是不会受到其他调用对象影响的
    public synchronized static void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2,{}-{}", j, i);
        }
    }

    /**
     * 总结:
     * 1.如果一个方法内部所有的代码用synchronized修饰,那么它和用synchronized修饰的方法效果是一样的
     * 上面的两个示例方法效果是一样的
     * 2.如果一个类是父类,在这个父类的内部有一个方法test(),这个方法用synchronized修饰,
     * 这个父类的子类在调用test()方法时,是不带synchronized的,如果子类想要用synchronized的方法,
     * 需要在子类中自己声明带synchronized的方法
     *
     * @param args
     */

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample2 se1 = new SynchronizedExample2();
        SynchronizedExample2 se2 = new SynchronizedExample2();
        executorService.execute(() -> se1.test2(1));
        executorService.execute(() -> se2.test2(2));
    }


}
