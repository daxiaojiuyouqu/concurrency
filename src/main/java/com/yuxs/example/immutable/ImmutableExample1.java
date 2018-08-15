package com.yuxs.example.immutable;

import com.google.common.collect.Maps;

import java.util.Map;

public class ImmutableExample1 {

    private final static int a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a = 10;
//        b = "5";
//        map = Maps.newHashMap();
        map.put(1, 10);
        System.out.println(map.get(1));
    }

    public static void test(final int a) {
//        a = 2;
    }
}
