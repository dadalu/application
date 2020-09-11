package com.wxc.mapper;

import com.wxc.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
    User findById(Long id);
    void insert(User user);
    void update(User user);
    void delete(Long id);
    boolean getBoolean1();
    boolean getBoolean0();
    int getInt0();
    int getInt1();

}
