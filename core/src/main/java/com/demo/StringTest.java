package com.demo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class StringTest {
    public static void main(String[] args) {
        /*String code = "410911";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(code, 0, 4);
        stringBuffer.append(0);
        stringBuffer.append(0);
        System.out.println(stringBuffer);
        stringBuffer.replace(2, 4, "00");
        System.out.println(stringBuffer);*/
        Integer g3 = 0, g4 = 0, g5 = 0, g6 = 0;
        Random random = new Random();
        for (int i = 0;  i< 3500000; i++) {
            int num = random.nextInt(345);
            if (num < 3) {
                g6++;
            } else if (num >= 3 && num < 8) {
                g5++;
            } else if (num >= 8 && num < 183) {
                g4++;
            } else if (num >= 183 && num < 345) {
                g3++;
            }
        }
        System.out.println(g3/10000000.00);
        System.out.println(g4/10000000.00);
        System.out.println(g5/10000000.00);
        System.out.println(g6/10000000.00);
    }
}
