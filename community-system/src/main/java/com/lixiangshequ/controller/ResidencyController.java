package com.lixiangshequ.controller;

import com.lixiangshequ.model.ResultMap;
import com.lixiangshequ.service.ResidencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ResidencyController {
    private static final Logger logger = LoggerFactory.getLogger(ResidencyController.class);

    private final ResidencyService residencyService;
    private final ResultMap resultMap;

    @Autowired
    public ResidencyController(ResidencyService residencyService, ResultMap resultMap) {
        this.residencyService = residencyService;
        this.resultMap = resultMap;
    }

    @ResponseBody
    @RequestMapping("/clerk/getALlResidency")
    public ResultMap getAllResidency(){
        List list = residencyService.selectAll();
        return resultMap.success().message(list);
    }
}
