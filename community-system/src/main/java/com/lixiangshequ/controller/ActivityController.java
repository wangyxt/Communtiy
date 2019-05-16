package com.lixiangshequ.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lixiangshequ.entity.Activity;
import com.lixiangshequ.entity.ActivityList;
import com.lixiangshequ.entity.User;
import com.lixiangshequ.model.ResultMap;
import com.lixiangshequ.service.ActivityService;
import com.lixiangshequ.service.dto.ReturnStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ActivityController {
    private final static Logger logger = LoggerFactory.getLogger(ActivityController.class);
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 查询所有已审核通过活动
     * @param modelAndView
     * @return
     */
    @GetMapping("/activity/all")
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
     * 根据发布人查询活动
     * @param modelAndView
     * @return
     */
    @GetMapping("/activity/selectAllByPublishId")
//    @ResponseBody
    public ModelAndView selectAllByPublishId(int id,ModelAndView modelAndView){
        List list = activityService.selectAllByPublishId(id);
        modelAndView.addObject("activityList",list);
        modelAndView.setViewName("../static/html/jie/index");
        ReturnStatus returnStatus = new ReturnStatus(0,"",list.size(),list);
        return modelAndView;
    }
    /**
     * 查询所有已审核通过活动
     * @param modelAndView
     * @return
     */
    @GetMapping("/activity/selectAllToAudit")
//    @ResponseBody
    public ModelAndView selectAllToAudit(ModelAndView modelAndView){
        List list = activityService.selectAllToAudit();
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
    @GetMapping(value = "/activity/detail/{act_id}")
//    @ResponseBody
    public ModelAndView detail(@PathVariable int act_id, ModelAndView modelAndView, HttpSession httpSession, UsernamePasswordToken token){
        /**
        当前登录用户
         */
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Activity activity = activityService.selectByActId(act_id);
        if (null != user){
            ActivityList activityList = new ActivityList(act_id,user.getUid());
            //获取当前用户报名此活动状态
            ActivityList isApply = activityService.selectIsApply(activityList);
            /**
             * 判断是否已报名。如果已经未报名返回信息中添加“点击报名”
             * 反之添加“取消报名”
             */
            if(isApply!=null){
                modelAndView.addObject("isApply","取消报名!");
            }else {
                modelAndView.addObject("isApply","点击报名!");
            }
        }
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

    /**
     * 报名活动
     * @param act_id
     * @param modelAndView
     * @param httpSession
     * @return
     */
    @PostMapping("/activity/apply")
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
            logger.error(e.toString());
            return "报名失败！";
        }
        return "报名成功！";
    }

    /**
     * 取消报名接口
     * @param act_id
     * @param modelAndView
     * @param httpSession
     * @return
     */
    @ResponseBody
    @PostMapping("/activity/cancelActivity")
    public String cancelActivity(@RequestParam(required = true) String act_id, ModelAndView modelAndView, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "登录后重试!";
        }
        ActivityList activityList = new ActivityList(Integer.parseInt(act_id),user.getUid());
        ActivityList isApply = activityService.selectIsApply(activityList);
        if(isApply!=null){
            try {
                activityService.cancelActivityNum(activityList);
            }catch (Exception e){
                logger.error(e.toString());
                return "取消失败，请重试！";
            }
        }
        return "已取消报名！";
    }


    @DeleteMapping("/admin/delete")
    public String deleteActivity(@RequestParam(required = true) String act_id,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user!=null && user.getRole()==0){
            activityService.delete(Integer.parseInt(act_id));
            return "删除成功";
        }else {
            return "错误!无权限";
        }
    }

    /**
     * 更新活动页
     * @param act_id
     * @param modelAndView
     * @param httpSession
     * @return
     */
    @RequiresRoles(value = {"clerk"},logical = Logical.OR)
    @RequiresPermissions(value = "clerk",logical = Logical.OR)
    @RequestMapping("/clerk/updateOrAddPage")
    public ModelAndView updateOrAddPage(String act_id,ModelAndView modelAndView,HttpSession httpSession){
        if(act_id!=null){
            Activity activity = activityService.selectByActId(Integer.parseInt(act_id));
            modelAndView.addObject("activity",activity);
        }
        modelAndView.setViewName("../static/html/jie/add.html");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/clerk/insertActivity")
    public String insertActivity(@Validated Activity activity,HttpSession session){
        User user = (User) session.getAttribute("user");
        activity.setPublish_id(user);
        if(user!=null){
            if (user.getRole()==0){
                activity.setState(0);
            }else {
                activity.setState(1);
            }
            int result = activityService.insert(activity);
            if (result>0){
                return "添加成功!";
            }else {
                return "添加失败!";
            }
        }else {
            return "操作失败！请您先登录。";
        }
    }

    @PostMapping("/clerk/updateActivity")
    @ResponseBody
    public ResultMap updateActivity(@Validated Activity activity){
        System.out.println(activity);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        activity.setPublish_id(user);
        int i = activityService.update(activity);
        if (i!=0){
            return new ResultMap().success().message("更新成功");
        }
        return new ResultMap().fail().message("更新失败");
    }
}
