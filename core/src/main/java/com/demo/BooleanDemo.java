package com.demo;

import java.util.ArrayList;
import java.util.List;

public class BooleanDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        List subList = list.subList(0,0);
        List subList1 = list.subList(0,1);
        List subList2 = list.subList(0,2);
        List subList3 = list.subList(0,3);
        List subList4 = list.subList(0,4);
        List subList5= list.subList(0,5);
        List subList6 = list.subList(0,6);
        List subList7 = list.subList(0,7);
        List subList8 = list.subList(0,8);
        List subList9 = list.subList(0,9);
        List subList10 = list.subList(0,10);
        System.out.println("////");
    }
}
