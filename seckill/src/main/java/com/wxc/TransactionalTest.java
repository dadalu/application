package com.wxc;

import com.wxc.dao.UserMapper;
import com.wxc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tran")
public class TransactionalTest {
    @Autowired
    private UserMapper mapper;
    @Transactional
    @RequestMapping("test")
    public Object test(){

        User user = new User();
        user.setName("张三");
        user.setGender(Byte.decode("1"));
        user.setRegisterMode("214352");
        user.setTelphone("2163437461");
        user.setThirdPartyId("222");
        user.setAge(22);
        mapper.insert(user);
        user.setName("李四");
        user.setTelphone("234355");
        mapper.insert(user);
        user.setName("王五");
        user.setTelphone("4354263");
        mapper.insert(user);
        user.setName("陈六");
        user.setTelphone("35652566");
        mapper.insert(user);
        int age = 1/0;
        return null;
    }
}
