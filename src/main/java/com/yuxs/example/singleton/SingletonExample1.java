package com.yuxs.example.singleton;

import com.yuxs.annoations.NotThreadSafe;


/**
 * 单例实例在第一次使用时被创建
 * 懒汉模式
 */
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 私有的构造方法
     */
    private SingletonExample1() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample1 instance = null;

    /**
     *  静态的工厂方法
     *  线程不安全的,问题出现在
     *  if (instance == null) {
     *       instance = new SingletonExample1();
     *  }
     *  如果单线程没问题,如果多线程同时访问,instance = new SingletonExample1() 会被创建成不同的对象,如果
     *  私有构造里面有对资源的处理或者运算,就会有问题
     */
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
