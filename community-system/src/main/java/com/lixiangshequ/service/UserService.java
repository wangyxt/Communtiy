package com.lixiangshequ.service;

import com.lixiangshequ.domain.User;
import com.lixiangshequ.service.dto.UserDto;

public interface UserService {

    /**
     * get
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * create
     * @param resouces
     * @return
     */
    User creat(User resouces);

    /**
     * update
     * @param resouces
     */
    void update(User resouces);

    /**
     * delete
     * @param id
     */
    void delete(int id);

    /**
     * findByTel
     * @param tel
     * @return
     */
    User findByTel(String tel);

    /**
     * 修改密码
     * @param tel
     * @param encryptPassword
     */
    void updatePass(String tel, String encryptPassword);

    /**
     * 修改头像
     * @param username
     * @param url
     */
    void updateAvatar(String username, String url);

    /**
     * 修改邮箱
     * @param username
     * @param email
     */
    void updateEmail(String username, String email);
}
