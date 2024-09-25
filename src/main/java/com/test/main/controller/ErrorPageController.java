package com.test.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {
    @GetMapping("/403")
    public String loginForm(){ // 로그인 페이지
        return "403";
    }
}
