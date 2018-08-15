package com.yuxs.example.singleton;

import com.yuxs.annoations.NotRecommend;
import com.yuxs.annoations.ThreadSafe;


/**
 * 单例实例在第一次使用时被创建
 * 懒汉模式
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    /**
     * 私有的构造方法
     */
    private SingletonExample3() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample3 instance = null;

    /**
     * 懒汉模式通过加入synchronized关键字来进行描述,保证同一时间只有一个线程访问此方法保证线程安全
     * 但是这样做会带来性能损耗不推荐
     */
    public synchronized static SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
