package com.demo;

import java.util.regex.Pattern;

public class Mex {
    public static void main(String[] args) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        System.out.println(Pattern.matches(regex, "1234563423"));
        System.out.println(Pattern.matches(regex, "A123b4"));
        System.out.println(Pattern.matches(regex, "123456a423424"));
        System.out.println(Pattern.matches(regex, "12.34563423"));
        System.out.println(Pattern.matches(regex, "abc.dvsdsfv"));
    }
}
