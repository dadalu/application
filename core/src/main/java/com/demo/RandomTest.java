package com.demo;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 0;
        for (int i = 0; i < 1000; i++) {
            int ran = random.nextInt(31);
            System.out.println(ran);
            if (ran >24) {
                n++;
            }
        }
        System.out.println("n:"+n);
    }
}
