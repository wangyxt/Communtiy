package com.lixiangshequ.service.impl;

import com.lixiangshequ.entity.User;
import com.lixiangshequ.repository.UserMapper;
import com.lixiangshequ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public int update(User resouces) {
        return userMapper.update(resouces);
    }

    public void delete(int id) {
        userMapper.deleteUser(id);
    }

    public User findByTel(String tel) {
        return userMapper.selectByTel(tel);
    }

    @Override
    public String validatePass(int id, String pass) {
        User user = userMapper.selectById(id);
        if (null!=user){
            if (pass.equals(user.getPassword())){
                return "ok";
            }
            return "密码错误";
        }
        return "该用户已被删除";
    }

    public void updatePass(String tel, String encryptPassword) {

    }

    public void updateAvatar(String username, String url) {

    }

    public void updateEmail(String username, String email) {

    }

    @Override
    public List selectAll(int begin, int end) {
        return userMapper.selectAll(begin,end);
    }

}
