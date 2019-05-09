package com.lixiangshequ.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lixiangshequ.entity.User;
import com.lixiangshequ.model.ResultMap;
import com.lixiangshequ.service.UserService;
import com.lixiangshequ.vo.AuthorizationUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/auth",method = RequestMethod.GET)
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ResultMap resultMap;

    @Autowired
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "../static/html/user/login";
    }



    /**
     * 注册接口
     * @param user
     * @param modelAndView
     * @return
     */
    @PostMapping(value = "/register")
    public ModelAndView register(@Validated User user,ModelAndView modelAndView){
        userService.creat(user);
//        user.setCreatTime(LocalTime.now());

        modelAndView.setViewName("../static/html/user/login");
        return modelAndView;
    }

    /**
     * 验证用户名是否存在
     * @param name
     * @return
     */
    @PostMapping(value = "/verificationName")
    @ResponseBody
    public boolean verificationName(String name){
        User result = userService.selectByName(name);
        if(result==null){
            return false;
        }
        return true;
    }

    /**
     * 注销接口
     * @param httpSession
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession httpSession){
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "redirect:/auth/";
    }

    /**
     * 根据手机号获取用户信息
     * @param tel
     * @return
     */
    @PostMapping("/getMessByTel")
    @ResponseBody
    public User getMessByTel(@RequestParam(required = true) String tel){
        return userService.findByTel(tel);
    }

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    @PostMapping("/getMessByName")
    @ResponseBody
    public User getMessByName(@RequestParam(required = true) String name){
        return userService.selectByName(name);
    }


    @RequestMapping("/updatePassword")
    public void updatePassword(User user){
        userService.update(user);
    }
}
