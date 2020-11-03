package com.wxc.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wxc.dao.UserMapper;
import com.wxc.dao.UserPasswordMapper;
import com.wxc.entity.User;
import com.wxc.entity.UserPassword;
import com.wxc.error.BuinessException;
import com.wxc.error.EmBuinessError;
import com.wxc.service.UserService;
import com.wxc.service.model.UserModel;
import com.wxc.validator.ValidationResult;
import com.wxc.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private ValidatorImpl validator;
    @Override
    public UserModel getUserById(Integer id) {
        User user = userMapper.selectByUserId(id);
        if(user==null){
            return null;
        }
        UserPassword userPassword = userPasswordMapper.selectByUserId(user.getId());
        return convertFromDataObject(user,userPassword);
    }



    private UserModel convertFromDataObject(User user, UserPassword userPassword) {
        if(user==null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user,userModel);
        if(userPassword!=null){
            userModel.setEncrptPassword(userPassword.getEncrptPassword());
        }
        return userModel;
    }

    public void register(UserModel userModel) throws BuinessException {
        if(userModel==null){
            throw new BuinessException(EmBuinessError.PARAMETER_VALIDATION_ERROR);
        }
        ValidationResult result = validator.validate(userModel);
        if(result.isHasErrors()) {
            throw new BuinessException(EmBuinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }
        User user = convertFromModel(userModel);
        try {
            userMapper.insertSelective(user);
        }catch (DuplicateKeyException e){
            throw new BuinessException(EmBuinessError.PARAMETER_VALIDATION_ERROR,"手机号已经被注册过了！");
        }
        userModel.setId(user.getId());
        UserPassword userPassword = convertPasswordFromModel(userModel);
        userPasswordMapper.insertSelective(userPassword);
    }

    public UserModel validateLogin(String telphone,String encryptPassword) throws BuinessException {
        User user = userMapper.selectByTelphone(telphone);
        if(user==null){
            throw new BuinessException(EmBuinessError.USER_LOGIN_FAIL);
        }
        UserPassword userPassword = userPasswordMapper.selectByUserId(user.getId());
        UserModel userModel = convertFromDataObject(user,userPassword);
        if(!StringUtils.equals(encryptPassword,userModel.getEncrptPassword())){
            throw new BuinessException(EmBuinessError.USER_LOGIN_FAIL);
        }
        return userModel;

    }

    private UserPassword convertPasswordFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserPassword userPasswordDO = new UserPassword();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }

    private User convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        User userDO = new User();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

}
