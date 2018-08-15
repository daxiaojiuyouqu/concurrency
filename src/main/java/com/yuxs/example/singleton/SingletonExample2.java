package com.yuxs.example.singleton;

import com.yuxs.annoations.ThreadSafe;

/**
 * 单例实例在类被装载时被创建
 * 饿汉模式
 */
@ThreadSafe
public class SingletonExample2 {

    /**
     * 私有的构造方法
     */
    private SingletonExample2() {

    }

    /**
     * 单例对象
     * 静态域的初始化
     */
    private static SingletonExample2 instance = new SingletonExample2();

    /**
     * 饿汉模式是线程安全的
     * 但是是在基于私有构造内部处理不复杂,并且这个类肯定能被用到的情况下,不然会造成资源的浪费,效率较低
     */
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
