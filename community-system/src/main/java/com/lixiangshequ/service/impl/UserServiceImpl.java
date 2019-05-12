package com.lixiangshequ.service.impl;

import com.lixiangshequ.entity.User;
import com.lixiangshequ.repository.UserMapper;
import com.lixiangshequ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired(required = true)
    private UserMapper userMapper;

    public User findById(int id) {
       return userMapper.selectById(id);
    }

    public void creat(User resouces) {
        if(resouces.getRole()==0){
            resouces.setRole(2);
        }
        int result = userMapper.insertOne(resouces);
        System.out.println("========================================================>"+result);
    }

    public User selectByName(String name) {
        return userMapper.selectByName(name);
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
