package com.yuxs.publish;

import com.yuxs.annoations.NotRecommend;
import com.yuxs.annoations.NotThreadSafe;

@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }


    private class InnerClass {
        public InnerClass() {
            System.out.println(Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }

}
