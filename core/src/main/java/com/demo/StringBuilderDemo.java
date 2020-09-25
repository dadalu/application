package com.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class StringBuilderDemo {
    @Test
    public void test(){
        StringBuilder stringBuilder = new StringBuilder();
        String str = stringBuilder.toString();
        JSONObject jsonStr = (JSONObject) JSON.parse(str);
        System.out.println(str);
    }
}
