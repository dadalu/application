package com.wxc.controller;

import com.alibaba.druid.util.StringUtils;
import com.wxc.controller.vo.UserVO;
import com.wxc.error.BuinessException;
import com.wxc.error.EmBuinessError;
import com.wxc.response.CommonReturnType;
import com.wxc.service.UserService;
import com.wxc.service.model.UserModel;
import com.wxc.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static com.wxc.controller.BaseController.CONTENT_TYPE_FORMED;

@Controller("User")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisUtil redisUtil;
    //用户注册接口
    @RequestMapping(value = "/register",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(HttpServletRequest httpServletRequest,@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BuinessException {
        //String inSessionOptcode = (String)request.getSession().getAttribute(telphone);
        String inSessionOptcode = (String)redisUtil.get(telphone);
        System.out.println(request.getSession().getId());
        if(!StringUtils.equals(inSessionOptcode,otpCode)){
            throw new BuinessException(EmBuinessError.PARAMETER_VALIDATION_ERROR,"验证码错误");
        }
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setTelphone(telphone);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setAge(age);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(this.encodeByMd5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64En = new BASE64Encoder();
        String newStr = base64En.encode(md5.digest(str.getBytes("UTF-8")));
        return newStr;
    }

    //用户获取otp短信接口
    @RequestMapping(value = "/getotp",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(HttpServletRequest httpServletRequest,@RequestParam(name = "telphone") String telphone){
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt+=10000;
        String otpCode = String.valueOf(randomInt);
        System.out.println(request.getSession().getId());
        redisUtil.set(telphone,otpCode);
        //request.getSession().setAttribute(telphone, otpCode);
        System.out.println("telpone:"+telphone+"&otpCode:"+otpCode);
        return CommonReturnType.create(null);
    }
    //用户登录接口
    @RequestMapping(value = "/login",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(HttpServletRequest httpServletRequest,@RequestParam("telphone") String telphone,
                                  @RequestParam("password") String password) throws BuinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(StringUtils.isEmpty(telphone)){
            throw new BuinessException(EmBuinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserModel userModel = userService.validateLogin(telphone,this.encodeByMd5(password));
        httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);
        return CommonReturnType.create(null);
    }
    //获取用户接口
    @RequestMapping(value = "/getUser",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getUser(@RequestParam("id")Integer id) throws BuinessException {
        UserModel userModel = userService.getUserById(id);
        if(userModel==null){
            throw new BuinessException(EmBuinessError.USER_NOT_EXIST);
        }
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(null);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if(userModel==null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }
}
