package com.yuxs.example.singleton;

import com.yuxs.annoations.NotThreadSafe;


/**
 * 单例实例在第一次使用时被创建
 * 懒汉模式->双重同步锁单例模式
 */
@NotThreadSafe
public class SingletonExample4 {

    /**
     * 私有的构造方法
     */
    private SingletonExample4() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample4 instance = null;

    /**
     * 静态的工厂方法
     * 使用双重同步锁单例模式,但是并不是线程安全的
     * 分析原因:
     * instance = new SingletonExample4();cpu会执行以下指令
     * 1.memory = allocate()分配对象的内存空间
     * 2.ctorInstance()初始化对象
     * 3.instance = memory设置instance指向刚分配的内存
     * jvm和cpu优化,发生了指令重排
     * 变成了1,3,2这样的执行顺序
     */
    public static SingletonExample4 getInstance() {
        if (instance == null) {//双重检测
            synchronized (SingletonExample4.class) {//同步锁
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
