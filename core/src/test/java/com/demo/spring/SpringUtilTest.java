package com.demo.spring;

import com.wxc.Application;
import com.wxc.ApplicationTest;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jmx.export.SpringModelMBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@SpringBootConfiguration
public class SpringUtilTest {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void getBean() {
        JdbcTemplate bean = (JdbcTemplate)applicationContext.getBean("jdbcTemplate");
        System.out.println(bean.equals(jdbcTemplate));
    }
}