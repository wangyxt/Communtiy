package com.lixiangshequ.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lixiangshequ.entity.User;
import com.lixiangshequ.service.UserService;
import com.lixiangshequ.vo.AuthorizationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/auth",method = RequestMethod.GET)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "../static/html/user/login";
    }

    /**
     * 登录接口
     * @param authorizationUser
     * @param modelAndView
     * @param httpSession
     * @return
     */
    @PostMapping(value = "/login")
//    @ResponseBody
    public ModelAndView login(@Validated AuthorizationUser authorizationUser, ModelAndView modelAndView, HttpSession httpSession){
        String tel = authorizationUser.getTel();
        User user = userService.findByTel(tel);
        if(!user.getPassword().equals(authorizationUser.getPassword())){
            modelAndView.addObject("mess","用户名或密码错误");
            modelAndView.setViewName("../static/html/user/login");
            return modelAndView;
        }
//        ReturnStatus returnStatus = new ReturnStatus(0,"",1,"你好");
//        String jsonString = objectMapper.writeValueAsString(returnStatus);
//        ReturnStatus returnStatus = mapper.readValue(jsonString, returnStatus.class);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("../static/html/index");
        httpSession.setAttribute("user",user);
        return modelAndView;
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

    public void updateUser(User user){

    }
}
