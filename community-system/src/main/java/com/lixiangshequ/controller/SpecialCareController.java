package com.lixiangshequ.controller;

import com.lixiangshequ.service.SpecialCareService;
import com.lixiangshequ.service.dto.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SpecialCareController {
    private final SpecialCareService specialCareService;

    @Autowired
    public SpecialCareController(SpecialCareService specialCareService) {
        this.specialCareService = specialCareService;
    }

    @ResponseBody
    @RequestMapping("/clerk/selectAllSpecial")
    public ReturnStatus selectAllSpecial(){
        List list = specialCareService.selectAll();
        return new ReturnStatus(0,"",list.size(),list);
    }
}
