package com.wxc;

import com.alibaba.fastjson.JSON;
import com.wxc.dao.UserMapper;
import com.wxc.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.wxc"})
@MapperScan("com.wxc.dao")
@RestController
@EnableEurekaClient
public class SeckillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class,args);
    }
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/")
    @ResponseBody
    public Object getUserById(){
        User user = userMapper.selectByPrimaryKey(1);
        return JSON.toJSONString(user);
    }
}
