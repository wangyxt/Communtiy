package com.lixiangshequ.controller;

import com.lixiangshequ.entity.SpecialCare;
import com.lixiangshequ.model.ResultMap;
import com.lixiangshequ.service.SpecialCareService;
import com.lixiangshequ.service.dto.ReturnStatus;
import org.apache.shiro.authc.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SpecialCareController {
    private final SpecialCareService specialCareService;

    @Autowired
    public SpecialCareController(SpecialCareService specialCareService) {
        this.specialCareService = specialCareService;
    }

    /**
     * 查询现有优抚对象
     * @return
     */
    @ResponseBody
    @RequestMapping("/clerk/selectAllSpecial")
    public ReturnStatus selectAllSpecial(int page,int limit){
        System.out.println("==================="+page+"***********"+limit+"=====================");
        //开始条数=页数*每页条数
        int begin = (page-1)*limit;
        //结束条数=开始条数+每页条数
        int end = begin+limit;
        List list = specialCareService.selectAll(begin,end);
        return new ReturnStatus(0,"",list.size(),list);
    }

    @ResponseBody
    @RequestMapping("/clerk/selectAllSpecialByCondition")
    public ReturnStatus selectAllSpecialByCondition(int page,int limit,String condition){
        //开始条数=页数*每页条数
        int begin = (page-1)*limit;
        //结束条数=开始条数+每页条数
        int end = begin+limit;
        List list = specialCareService.selectAll(begin,end,condition);
        return new ReturnStatus(0,"",list.size(),list);
    }

    @RequestMapping("/admin/toAuditSpecial")
    public String toAuditSpecial(Model model){
        model.addAttribute("ratify","toAuditSpecial");
        return "../static/html/special/index.html";
    }

    @RequestMapping("/clerk/getAllVeteran")
    public String getVeteranPage(Model model){
        model.addAttribute("ratify","退役军人");
        return "../static/html/special/index.html";
    }

    @RequestMapping("/clerk/getAllOldMan")
    public String getOldManPage(Model model){
        model.addAttribute("ratify","60岁以上老人");
        return "../static/html/special/index.html";
    }

    @RequestMapping("/clerk/getRelation")
    public String getRelation(Model model){
        model.addAttribute("ratify","军人家属");
        return "../static/html/special/index.html";
    }

    /**
     * 查看所有待审核
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/selectAllNotPass")
    public ReturnStatus selectAllNotPass(){
        List list = specialCareService.selectAllNotPass();
        return new ReturnStatus(0,"",list.size(),list);
    }

    @ResponseBody
    @RequestMapping("/clerk/deleteSpecial")
    public ReturnStatus deleteSpecial(int id){
//        int r = specialCareService.deleteByPrimaryKey(id);
        int r = 1;
        if (0==r){
            return new ReturnStatus(0,"删除失败!",1,r);
        }else {
            System.out.println("删除成功");
            return new ReturnStatus(0,"删除成功",1,r);
        }
    }

    /**
     * 添加优抚对象
     * @param specialCare
     * @return
     */
    @ResponseBody
    @PostMapping("/clerk/insertSpecial")
    public ResultMap insertSpecial(SpecialCare specialCare){
        if (null!=specialCare && 0==specialCare.getStatus()){
            specialCare.setStatus(1);
        }
        int r = specialCareService.insert(specialCare);
        if (r>0){
            return new ResultMap().success().message("添加成功");
        }else {
            return new ResultMap().success().message("添加失败");
        }
    }

    @ResponseBody
    @PostMapping("/clerk/updateSpecial")
    public ResultMap updateSpecial(SpecialCare specialCare){
        int r = specialCareService.updateByPrimaryKey(specialCare);
        if (r>0){
            return new ResultMap().success().message("修改成功!");
        }else {
            return new ResultMap().fail().message("修改失败");
        }
    }

    @RequestMapping("/clerk/editPage")
    public String editPage(){
        return "../static/html/special/edit";
    }
    /**
     *编辑优抚对象信息跳转页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/clerk/findUpdateSpecialById")
    public String findUpdateSpecialById(int id, Model model){
        //根据id查找优抚对象
        SpecialCare specialCare = specialCareService.selectByPrimaryKey(id);
        if (null==specialCare){
            throw new AccountException("未找到此居民");
        }
        model.addAttribute("specialCare",specialCare);
        return "../static/html/special/edit";
    }


}
