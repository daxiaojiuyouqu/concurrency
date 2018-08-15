package com.yuxs.example.threadLocal;

public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long sid) {
        requestHolder.set(sid);
    }

    public static Long get() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }


}
