package com.lixiangshequ.controller;

import com.lixiangshequ.entity.Recruit;
import com.lixiangshequ.entity.User;
import com.lixiangshequ.model.ResultMap;
import com.lixiangshequ.service.RecruitService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RecruitController {

    private final RecruitService service;
    private final static Logger logger = LoggerFactory.getLogger(RecruitController.class);
    private ResultMap resultMap = new ResultMap();

    @Autowired
    public RecruitController(RecruitService service) {
        this.service = service;
    }

    @RequestMapping("/normal/getAllRecruit")
    public String getAllRecruit(Model model){
        List list = service.getAllRecruit();
        model.addAttribute("recruitList",list);
        return "../static/html/recruit/index";
    }

    @GetMapping(value = "/recruit/detail/{id}")
    public ModelAndView findRecruitById(@PathVariable Integer id, ModelAndView model){
        Recruit recruit = service.selectByPrimaryKey(id);
        if (null!=recruit){
            model.addObject("recruit",recruit);
        }
        model.setViewName("../static/html/recruit/detail");
        return model;
    }

    @RequestMapping("/clerk/toUpdateRecruit")
    public ModelAndView toUpdateRecruit(Integer id,ModelAndView modelAndView){
        Recruit recruit = service.selectByPrimaryKey(id);
        if (null==recruit){
            throw new AccountException("该职位已删除");
        }
        modelAndView.addObject("recruit",recruit);
        modelAndView.setViewName("../static/html/recruit/add");
        return modelAndView;
    }

    /**
     * 填加职位
     * @param recruit
     * @return
     */
    @PostMapping("/clerk/insertRecruit")
    @ResponseBody
    public ResultMap insertPerson(Recruit recruit){
        System.out.println(recruit);
        int i = service.insert(recruit);
        if (i>0){
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if (null!=user){
                logger.info(user.getName()+"添加了一条招聘信息:"+recruit);
                return resultMap.success().message("添加成功！");
            }else {
                logger.error("没有获取到当然登录用户信息");
            }
        }
        return resultMap.fail().message("添加失败");
    }

    /**
     * 更新职位信息
     * @param recruit
     * @return
     */
    @ResponseBody
    @PostMapping("clerk/updateRecruit")
    public ResultMap updatePerson(Recruit recruit){
        int result = service.update(recruit);
        if (result>0){
            return resultMap.success().message("修改成功");
        }
        logger.error("修改职位信息失败");
        return resultMap.fail().message("修改职位信息失败");
    }
}
