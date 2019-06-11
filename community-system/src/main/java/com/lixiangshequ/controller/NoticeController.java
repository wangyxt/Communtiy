package com.lixiangshequ.controller;

import com.lixiangshequ.entity.Notice;
import com.lixiangshequ.model.ResultMap;
import com.lixiangshequ.service.NoticeService;
import com.lixiangshequ.utils.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Controller
public class NoticeController {

    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
    private ResultMap resultMap = new ResultMap();
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/clerk/addNoticePage")
    public String addNoticePage(){
        return "../static/html/notice/add";
    }

    @GetMapping("/getAllNoticePage")
    public String getAllNoticePage(Model model){
        String url = "/getAllNotice";
        model.addAttribute("url",url);
        return "../static/html/notice/index";
    }

    @GetMapping("/admin/getWaitNoticePage")
    public String getWaitNoticePage(Model model){
        String url = "/admin/getWaitNotice";
        model.addAttribute("url",url);
        return "../static/html/notice/index";
    }

    @GetMapping("/clerk/updateNoticePage")
    public String updateNoticePage(int id,Model model){
        Notice notice = null;
        if (id>0){
            notice = noticeService.selectByPrimaryKey(id);
        }
        if (null!=notice){
            model.addAttribute("notice",notice);
        }
        return "../static/html/notice/add";
    }

    @PostMapping("/getAllNotice")
    @ResponseBody
    public ResultMap getAllNotice(){
        List list = noticeService.selectAll();
        return resultMap.success().message(list);
    }

    @PostMapping("/admin/getWaitNotice")
    @ResponseBody
    public ResultMap getWaitNotice(){
        List list = noticeService.selectAllWaitNotice();
        return resultMap.success().message(list);
    }

    @PostMapping("/clerk/addNotice")
    @ResponseBody
    public ResultMap addNotice(@Validated Notice notice){
        if (null!=notice){
            notice.setState(0);
        }
        Date l = new Date();
        notice.setPubTime(l);
        int result = noticeService.insertSelective(notice);
        if (result>0){
            return resultMap.success().message("添加成功");
        }else {
            return resultMap.fail().message("添加失败");
        }
    }

    @PostMapping("/admin/updateState")
    @ResponseBody
    public ResultMap updateState(int id){
        int result = 0;
        if (0!=id){
            Notice notice = noticeService.selectByPrimaryKey(id);
            if (null==notice){
                return resultMap.fail().message("改公告已被删除");
            }
            notice.setState(1);
            result = noticeService.updateByPrimaryKey(notice);
        }
        if (result>0){
            return resultMap.success().message("审核成功");
        }else {
            return resultMap.fail().message("审核失败");
        }
    }

    @PostMapping("/clerk/updateNotice")
    @ResponseBody
    public ResultMap updateNotice(@Validated Notice notice){
        notice.setState(0);
        int r = noticeService.updateByPrimaryKeySelective(notice);
        if (r>0){
            return resultMap.success().message("提交成功");
        }else {
            return resultMap.fail().message("提交失败");
        }
    }

    @GetMapping("/noticeDetail/{id}")
    public String noticaDetail(@PathVariable int id,Model model){
        Notice notice = noticeService.selectByPrimaryKey(id);
        if (null==notice){
            return "../static/html/notice/detail";
        }else {
            model.addAttribute("notice",notice);
            return "../static/html/notice/detail";
        }
    }

    @PostMapping("/admin/deleteNotice")
    @ResponseBody
    public ResultMap deleteNotice(@Validated int id){
        int r = noticeService.deleteByPrimaryKey(id);
        if (r>0){
            return resultMap.success().message("删除成功");
        }else {
            return resultMap.fail().message("删除失败");
        }
    }
}
