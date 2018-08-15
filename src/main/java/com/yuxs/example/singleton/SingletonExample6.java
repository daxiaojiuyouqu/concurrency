package com.yuxs.example.singleton;

import com.yuxs.annoations.Recommend;
import com.yuxs.annoations.ThreadSafe;


/**
 * 枚举模式:最安全的
 */
@ThreadSafe
@Recommend
public class SingletonExample6 {

    /**
     * 私有的构造方法
     */
    private SingletonExample6() {

    }

    /**
     * 为什么比较推荐这种模式呢?
     * 相比于懒汉模式更能保证,相比饿汉模式是在实际调用的时候才做初始化,减少不必要的性能开销
     */
    public static SingletonExample6 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample6 singletonExample6;

        //JVM保证这个方法绝对只会被调用一次
        Singleton() {
            singletonExample6 = new SingletonExample6();
        }

        private SingletonExample6 getInstance() {
            return singletonExample6;
        }
    }
}
