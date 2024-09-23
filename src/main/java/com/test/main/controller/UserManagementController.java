package com.test.main.controller;

import com.test.main.security.CustomUserDetails;
import com.test.main.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserManagementController {
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/userManagement")
    public String userManagement(Model model, Principal principal) { // 회원 관리 페이지
        model.addAttribute("principal", principal);
        model.addAttribute("userList", customUserDetailsService.getUserList());
        return "userManagement";
    }

    @PostMapping("/userEjection")
    public String userEjection(CustomUserDetails customUserDetails) {
        customUserDetailsService.ejection(customUserDetails);
        return "redirect:/userManagement";
    }
}
