package com.yuxs.publish;

import com.yuxs.annoations.NotThreadSafe;

import java.util.Arrays;

/**
 * @author yuxsh
 * @date 2018/6/4.
 */

@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish publish = new UnsafePublish();
        System.out.println(Arrays.toString(publish.getStates()));
        publish.getStates()[0] = "d";
        System.out.println(Arrays.toString(publish.getStates()));
    }
}
