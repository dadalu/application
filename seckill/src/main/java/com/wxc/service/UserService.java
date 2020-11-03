package com.wxc.service;

import com.wxc.error.BuinessException;
import com.wxc.service.model.UserModel;

public interface UserService {
    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BuinessException;
    UserModel validateLogin(String telphone,String encryptPassword) throws BuinessException;
}
