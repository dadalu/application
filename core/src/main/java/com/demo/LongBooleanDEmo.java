package com.demo;

public class LongBooleanDEmo {
    public static void main(String[] args) throws InterruptedException {
        long time = System.currentTimeMillis();
        Thread.sleep(10);
        long c = System.currentTimeMillis()-time;
        System.out.println(Double.valueOf(c));
    }
}
