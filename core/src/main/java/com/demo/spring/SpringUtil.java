package com.demo.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author cAn
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    public ApplicationContext applicationContext;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //获取applicationContext
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}