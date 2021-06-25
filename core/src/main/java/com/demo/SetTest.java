package com.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        List<Vo> list = new ArrayList<>();
        Set<Vo> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Vo vo = new Vo();
            vo.setName(String.valueOf(i));
            list.add(vo);
        }
        list.add(null);
        set.addAll(list);
        System.out.println(set.contains("111"));
        for (Vo vo :
                set) {
            System.out.println(vo.getName());
        }
    }
}
class Vo{
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
