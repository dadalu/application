package com.demo;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setName("1");
        list.add(user);
        user.setName("2");
        list.add(user);
        System.out.println(list.toString());
    }
    static class User {
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
