package com.yuxs.example.immutable;

import com.google.common.collect.Maps;
import com.sun.javafx.collections.UnmodifiableObservableMap;
import com.yuxs.annoations.ThreadSafe;

import java.util.Collections;
import java.util.Map;

@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
//        map.put(1, 10);
        System.out.println(map.get(1));
    }

}
