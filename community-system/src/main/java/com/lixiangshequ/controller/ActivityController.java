package com.lixiangshequ.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lixiangshequ.entity.Activity;
import com.lixiangshequ.entity.ActivityList;
import com.lixiangshequ.entity.User;
import com.lixiangshequ.service.ActivityService;
import com.lixiangshequ.service.dto.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/all")
//    @ResponseBody
    public ModelAndView selectAllActivity(ModelAndView modelAndView){
        List list = activityService.selectAll();
//        try {
//            String jsonString = objectMapper.writeValueAsString(list);
//            return jsonString;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return "shibai";
        modelAndView.addObject("activityList",list);
        modelAndView.setViewName("../static/html/jie/index");
        ReturnStatus returnStatus = new ReturnStatus(0,"",list.size(),list);
        return modelAndView;
    }

    /**
     * 根据id查看活动详细信息
     * @param act_id
     * @param modelAndView
     * @return
     */
    @GetMapping(value = "/detail/{act_id}")
//    @ResponseBody
    public ModelAndView detail(@PathVariable int act_id,ModelAndView modelAndView){
        Activity activity = activityService.selectByActId(act_id);
        List list = activityService.selectNumByActId(act_id);
        modelAndView.addObject("activity",activity);
        modelAndView.addObject("num",list.size());
        modelAndView.setViewName("../static/html/jie/detail");
        return modelAndView;
    }

    @PostMapping("/insert")
    public void insertActivity(Activity activity){
        activityService.insert(activity);
    }

    @PostMapping("/apply")
    @ResponseBody
    public String applyActivity(@RequestParam(required = true) String act_id, ModelAndView modelAndView, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "登录后重试!";
        }
        ActivityList activityList = new ActivityList(Integer.parseInt(act_id),user.getUid());
        ActivityList isApply = activityService.selectIsApply(activityList);
        if(isApply!=null){
            return "已报名，请勿重复报名！";
        }
        try {
            activityService.insertActivityNum(activityList);
        }catch (Exception e){
            return "报名失败！";
        }
        return "报名成功！";
    }

    @DeleteMapping("/delete")
    public String deleteActivity(@RequestParam(required = true) String act_id,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user!=null && user.getRole()==0){
            activityService.delete(Integer.parseInt(act_id));
            return "删除成功";
        }else {
            return "错误!无权限";
        }
    }
}
