package com.lixiangshequ.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lixiangshequ.service.ActivityService;
import com.lixiangshequ.service.dto.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
}
