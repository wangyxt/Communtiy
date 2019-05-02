package com.lixiangshequ.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lixiangshequ.domain.User;
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
}
