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

    @PostMapping("/userDelete")
    public String userDelete(CustomUserDetails customUserDetails) { // 회원 탈퇴
        customUserDetailsService.userDelete(customUserDetails);
        return "redirect:/userManagement";
    }

    @PostMapping("/userActivate")
    public String userActivate(CustomUserDetails customUserDetails) { // 계정 활성화
        customUserDetailsService.userActivate(customUserDetails);
        return "redirect:/userManagement";
    }

    @PostMapping("/userDeactivate")
    public String userDeactivate(CustomUserDetails customUserDetails) { // 계정 활성화
        customUserDetailsService.userDeactivate(customUserDetails);
        return "redirect:/userManagement";
    }
}
