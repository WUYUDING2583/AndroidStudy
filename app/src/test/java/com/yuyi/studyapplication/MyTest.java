package com.yuyi.studyapplication;

import androidx.annotation.Nullable;

import org.junit.Test;

public class MyTest {

    @Test
    public void test(){
        ThreadLocal<String> sThreadLocal=new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return "yuyi";
            }
        };

        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 1;
            }
        };


        System.out.println(sThreadLocal.get());
        System.out.println(threadLocal.get());
    }
}
