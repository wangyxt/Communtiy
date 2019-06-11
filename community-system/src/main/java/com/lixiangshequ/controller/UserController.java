package com.lixiangshequ.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lixiangshequ.entity.User;
import com.lixiangshequ.model.ResultMap;
import com.lixiangshequ.service.UserService;
import com.lixiangshequ.service.dto.ReturnStatus;
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
import java.util.ArrayList;
import java.util.List;

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
     * 设置界面
     */
    @GetMapping("/set")
    public String setPage(){
        return "../static/html/user/set";
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

    @ResponseBody
    @RequestMapping("/searchUser")
    public ReturnStatus searchUser(String mess){
        mess = mess.replaceAll("\\s*", "");
        List list = new ArrayList();
        User user = userService.selectByName(mess);
        if (null==user){
            User user1 = userService.findByTel(mess);
            if (null==user1){
                return new ReturnStatus(0,"没有此人",0,"很抱歉，未查询到!");
            }
            list.add(user1);
        }else {
            list.add(user);
        }
        return new ReturnStatus(0,"",1,list);
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

    @RequestMapping("/getAllUser")
    @ResponseBody
    public ReturnStatus getAllUser(int page,int limit){
        //开始条数=页数*每页条数
        int begin = (page-1)*limit;
        //结束条数=开始条数+每页条数
        int end = begin+limit;
        List list = userService.selectAll(begin,end);
        return new ReturnStatus(0,"",list.size(),list);
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


    @PostMapping("/updatePassword")
    public void updatePassword(String pass){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        user.setPassword(pass);
        userService.update(user);
    }

    @PostMapping("/validatePass")
    @ResponseBody
    public String validatePass(String pass){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int id = user.getUid();
        String result = userService.validatePass(id,pass);
        return result;
    }

    @RequestMapping("/list")
    public String toList(){
        return "../static/html/user/list";
    }
}
