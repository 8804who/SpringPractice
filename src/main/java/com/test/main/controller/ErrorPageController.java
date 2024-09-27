package com.test.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {
    @GetMapping("/errorPage")
    public String errorPage(){ // 에러 페이지
        return "errorPage";
    }
}
