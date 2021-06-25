package com.demo;

import java.util.ArrayList;
import java.util.List;

public class SubListTest {

    public static void main(String[] args) {
        int batchSize = 100;
        List<Integer> batchList = new ArrayList<Integer>();
        for (int i = 0; i < 650; i++) {
            batchList.add(i);
        }
        int fromIndex = 0;
        int toIndex = 0;
        List subList = null;
        while (toIndex < batchList.size()) {
            toIndex += batchSize;
            System.out.println("fromIndex:"+fromIndex+",toIndex:"+toIndex);
            if (toIndex > batchList.size()) {
                subList = batchList.subList(fromIndex, batchList.size());
            } else {
                subList = batchList.subList(fromIndex, toIndex);
            }
            fromIndex += batchSize;
        }
        System.out.println(subList.get(subList.size()-1));
    }
}
