package com.wxc.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestJdbcController {
    @Autowired
    Jdbcbatch jdbcbatch;
    @GetMapping("get")
    public void aopTest() {
        jdbcbatch.update();
    }

}
