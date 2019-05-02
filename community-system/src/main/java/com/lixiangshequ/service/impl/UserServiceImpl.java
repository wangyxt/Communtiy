package com.lixiangshequ.service.impl;

import com.lixiangshequ.domain.User;
import com.lixiangshequ.repository.UserMapper;
import com.lixiangshequ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = true)
    private UserMapper userMapper;

    public User findById(int id) {
       return userMapper.selectById(id);
    }

    public User creat(User resouces) {
        return null;
    }

    public void update(User resouces) {

    }

    public void delete(int id) {

    }

    public User findByTel(String tel) {
        return userMapper.selectByTel(tel);
    }

    public void updatePass(String tel, String encryptPassword) {

    }

    public void updateAvatar(String username, String url) {

    }

    public void updateEmail(String username, String email) {

    }

}
