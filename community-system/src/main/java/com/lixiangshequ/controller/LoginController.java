package com.lixiangshequ.controller;

import com.lixiangshequ.entity.User;
import com.lixiangshequ.model.ResultMap;
import com.lixiangshequ.repository.UserMapper;
import com.lixiangshequ.service.UserService;
import com.lixiangshequ.vo.AuthorizationUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private ResultMap resultMap;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    @ResponseBody
    public ResultMap notLogin() {
        return resultMap.success().message("您尚未登陆！");
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    @ResponseBody
    public ResultMap notRole() {
        return resultMap.success().message("您没有权限！");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResultMap logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return resultMap.success().message("成功注销！");
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
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(tel, authorizationUser.getPassword());

        /*token.setRememberMe(true);*/
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        User user = userService.findByTel(tel);
        String role = userMapper.getRole(tel);
        //获取用户可访问资源并返回
        List list = userMapper.selectResourceByRoleId(user.getRole());
        if ("normal".equals(role)){
            modelAndView.addObject("user",user);
            modelAndView.setViewName("../static/html/index");
            httpSession.setAttribute("user",user);
            return modelAndView;
        }
        if ("clerk".equals(role)) {
            modelAndView.addObject("user",user);
            modelAndView.setViewName("../static/html/index");
            httpSession.setAttribute("user",user);
            return modelAndView;
        }
        if ("admin".equals(role)) {
            modelAndView.addObject("user",user);
            modelAndView.setViewName("../static/html/index");
            httpSession.setAttribute("user",user);
            return modelAndView;
        }
        modelAndView.addObject("mess","权限错误");
        modelAndView.setViewName("../static/html/user/login");
        logger.info("权限错误");
        return modelAndView;
    }
}
