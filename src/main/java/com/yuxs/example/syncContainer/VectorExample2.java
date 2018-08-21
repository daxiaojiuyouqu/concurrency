package com.yuxs.example.syncContainer;

import com.yuxs.annoations.NotThreadSafe;
import com.yuxs.annoations.ThreadSafe;

import java.util.Vector;

@NotThreadSafe
public class VectorExample2 {

    public static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
