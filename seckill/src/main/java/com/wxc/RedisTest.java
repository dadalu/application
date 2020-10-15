package com.wxc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void test()  {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        /*redisTemplate.opsForCluster();
        redisTemplate.opsForGeo();*/
        redisTemplate.opsForHash().put("hash","name",new HashMap());
        //redisTemplate.opsForHyperLogLog();
        redisTemplate.opsForList().leftPop(new HashMap());
        redisTemplate.opsForSet().add("set",new HashSet());
        redisTemplate.opsForValue().set("value","value");
        redisTemplate.opsForZSet().add(new TreeSet(),new TreeSet());
    }
}
