package com.yuxs.example.singleton;

import com.yuxs.annoations.ThreadSafe;


/**
 * 单例实例在第一次使用时被创建
 * 懒汉模式->双重同步锁单例模式
 */
@ThreadSafe
public class SingletonExample5 {

    /**
     * 私有的构造方法
     */
    private SingletonExample5() {

    }

    /**
     * 单例对象
     * 加入volatile + 双重检测 ->禁止重排序
     * 这样就变成线程安全的了
     */
    private volatile static SingletonExample5 instance = null;

    /**
     * 静态的工厂方法
     */
    public static SingletonExample5 getInstance() {
        if (instance == null) {//双重检测
            synchronized (SingletonExample5.class) {//同步锁
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
