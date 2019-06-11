package com.lixiangshequ.exception;

import org.apache.shiro.authc.AccountException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    // 捕捉 CustomRealm 抛出的异常
    @ExceptionHandler(AccountException.class)
    public String handleShiroException(Exception ex, Model model) {
        model.addAttribute("mess",ex.getMessage());
        return "../static/html/user/login";
    }


}
