package com.test.main.controller;

import com.test.main.dto.ImageDto;
import com.test.main.dto.PostDto;
import com.test.main.security.CustomUserDetails;
import com.test.main.security.CustomUserDetailsService;
import com.test.main.service.ImageService;
import com.test.main.service.MetadataService;
import com.test.main.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final PostService postService;
    private final MetadataService metadataService;
    private final CustomUserDetailsService customUserDetailsService;
    private final ImageService imageService;

    @GetMapping("/")
    public String readList(Model model, @RequestParam(required = false, defaultValue = "1") int page, Principal principal){ // 게시글 목록 페이지
        model.addAttribute("principal", principal);
        List<PostDto> postDtoList = postService.list((page-1)*10);
        model.addAttribute("postList", postDtoList);
        model.addAttribute("postCount", metadataService.postCount().getPostCount());
        model.addAttribute("page", page);
        return "list";
    }

    @GetMapping("/banned")
    public String banned(){ // 차단된 IP 접속 시 차단 알림 페이지
        return "banned";
    }

    @GetMapping("/loginForm")
    public String loginForm(){ // 로그인 페이지
        return "loginForm";
    }

    @GetMapping("/registerForm")
    public String registerForm(){ // 회원 가입 페이지
        return "registerForm";
    }

    @PostMapping("/register")
    public String register(CustomUserDetails customUserDetails, ImageDto image){ //회원 등록
        customUserDetails.setPw(new BCryptPasswordEncoder().encode(customUserDetails.getPassword()));
        customUserDetails.setProfileImage(imageService.imageUpload(image));
        customUserDetailsService.register(customUserDetails);
        return "redirect:/loginForm";
    }

    @PostMapping("/duplicateCheck")
    public @ResponseBody int duplicateCheck(@RequestParam String userId) { // ID 중복 검색
        return customUserDetailsService.duplicateCheck(userId);
    }

    @PostMapping("/passwordCheck")
    public @ResponseBody boolean passwordCheck(@RequestBody CustomUserDetails customUserDetails) { // 비밀번호 조회
        return new BCryptPasswordEncoder().matches(customUserDetails.getPassword(), customUserDetailsService.passwordCheck(customUserDetails));
    }
}