package com.wxc.entity;

import lombok.Data;

@Data
public class User {
        private Long id;
        private String name;
        private int age;
        private String pwd;
        //省略set/get方法

}
