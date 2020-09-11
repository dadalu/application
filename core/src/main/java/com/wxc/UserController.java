package com.wxc;

import com.wxc.entity.User;
import com.wxc.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
@RestController
@RequestMapping("/")
public class UserController {
    @Resource
    private UserMapper userMapper;

    @RequestMapping("/insert")
    public ModelAndView index() {
        User user = new User();
        user.setAge(18);
        user.setName("Adam");
        user.setPwd("123456");
        userMapper.insert(user);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("count", userMapper.findAll().size());
        return modelAndView;
    }
    @RequestMapping("/getBoolean")
    public String getBoolean() {

        boolean b0 = userMapper.getBoolean0();
        boolean b1 = userMapper.getBoolean1();
        int i0 = userMapper.getInt0();
        int i1 = userMapper.getInt1();
        System.out.println(b0);
        System.out.println(b1);
        System.out.println(i0);
        System.out.println(i1);
        return "b0="+b0+",b1="+b1+",i0="+i0+",i1="+i1;
    }
}
