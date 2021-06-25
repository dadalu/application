package com.demo;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {

        String[] strs0 = new String[]{"310000","320000","330000","340000"};
        String[] strs1 = new String[]{"320000","330000","340000","310000"};
        String[] strs2 = new String[]{"330000","340000","310000","320000"};
        String[] strs3 = new String[]{"340000","310000","320000","330000"};
        Set<String> set0 = new TreeSet<>();
        Set<String> set1 = new TreeSet<>();
        Set<String> set2 = new TreeSet<>();
        Set<String> set3 = new TreeSet<>();
        set0.addAll(Arrays.asList(strs0));
        set1.addAll(Arrays.asList(strs1));
        set2.addAll(Arrays.asList(strs2));
        set3.addAll(Arrays.asList(strs3));
        for (String str:set0) {
            System.out.print(str);
        }
        System.out.println();
        for (String str:set1) {
            System.out.print(str);
        }
        System.out.println();
        for (String str:set2) {
            System.out.print(str);
        }
        System.out.println();
        for (String str:set3) {
            System.out.print(str);
        }
        System.out.println();
    }

}
