package com.demo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(16, 32, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        while (true) {
            executor.execute(() -> test());
        }

    }

    static void test() {
        int i = 0;
        System.out.println(Thread.currentThread().toString()+":"+i);
        i++;
    }
}
