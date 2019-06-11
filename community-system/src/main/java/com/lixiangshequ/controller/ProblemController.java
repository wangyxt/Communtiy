package com.lixiangshequ.controller;

import com.lixiangshequ.entity.Problem;
import com.lixiangshequ.entity.User;
import com.lixiangshequ.service.ProblemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProblemController {

    private final ProblemService service;
    private final static Logger logger = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    public ProblemController(ProblemService service) {
        this.service = service;
    }

    @GetMapping("/normal/selectProblemByUserId")
    public ModelAndView selectProblemByUserId(int uid, ModelAndView modelAndView){
        List list = service.getAllProblemByUserId(uid);
        modelAndView.addObject("length",list.size());
        modelAndView.addObject("problemList",list);
        modelAndView.setViewName("../static/html/problem/index");
        return modelAndView;
    }

    @GetMapping("/clerk/selectAllUntreatedProblem")
    public ModelAndView selectAllUntreatedProblem(ModelAndView modelAndView){
        List list = service.getAllUntreatedProblem();
        modelAndView.addObject("length",list.size());
        modelAndView.addObject("problemList",list);
        modelAndView.setViewName("../static/html/problem/index");
        return modelAndView;
    }

    @GetMapping("/clerk/selectAllProblem")
    public ModelAndView selectAllProblem(ModelAndView modelAndView){
        List list = service.selectAllProblem();
        modelAndView.addObject("length",list.size());
        modelAndView.addObject("problemList",list);
        modelAndView.setViewName("../static/html/problem/index");
        return modelAndView;
    }

    @GetMapping(value = "/problem/detail/{problemId}")
    public ModelAndView detail(@PathVariable int problemId, ModelAndView modelAndView){
        Problem problem = service.selectById(problemId);
        modelAndView.addObject("problem",problem);
        modelAndView.setViewName("../static/html/problem/detail");
        return modelAndView;
    }

    @RequestMapping("/normal/insertProblemPage")
    public String insertProblemPage(){
        return "../static/html/problem/add";
    }

    @PostMapping("/normal/insertProblem")
    @ResponseBody
    public String insertProblem(Problem problem){
        int r = service.insert(problem);
        if (r>0){
            return "上报成功";
        }else{
            return "上报失败";
        }
    }

    @RequestMapping("/clerk/updateProblem")
    @ResponseBody
    public String updateProblem(int id,String bak, HttpSession session){
        User user = (User)session.getAttribute("user");
        Problem problem = service.selectById(id);
        problem.setHandlerId(user.getUid());
        problem.setStatus(1);
        problem.setBak(bak);
        int r = service.update(problem);
        if (r>0){
            return "回复成功";
        }else {
            return "回复失败";
        }
    }
}
