package com.lixiangshequ.controller;

import com.lixiangshequ.entity.User;
import com.lixiangshequ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {

    private final UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(){
        return "../static/html/index";
    }

    @RequestMapping("/clerk/getManagementMenu")
    public String managementMenu(){
        return "../static/html/management/index";
    }

    @RequestMapping("/admin/UpdatePageByUid")
    public String updateUserPage(int uid, Model model){
        User user = userService.findById(uid);
        model.addAttribute("u",user);
        return "../static/html/user/detail";
    }

    @RequestMapping("/admin/deleteUser")
    @ResponseBody
    public String deleteUser(int uid){
        userService.delete(uid);
        return "删除成功";
    }

    @ResponseBody
    @PostMapping("/admin/updateUser")
    public String updateUser(@Validated User user){
        int i = userService.update(user);
        if (i>0){
            return "修改成功";
        }else{
            return "修改失败";
        }
    }
}
