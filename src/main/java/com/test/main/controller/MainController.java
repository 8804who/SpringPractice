package com.test.main.controller;

import com.test.main.dto.ImageDto;
import com.test.main.dto.PostDto;
import com.test.main.security.CustomUserDetails;
import com.test.main.security.CustomUserDetailsService;
import com.test.main.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final CommentService commentService;
    private final PostService postService;
    private final MetadataService metadataService;
    private final CustomUserDetailsService customUserDetailsService;
    private final ImageService imageService;
    private final IpService ipService;

    @GetMapping("/")
    public String readList(Model model, @RequestParam(required = false, defaultValue = "1") int page, @AuthenticationPrincipal CustomUserDetails customUserDetails){ // 게시글 목록 페이지
        model.addAttribute("principal", customUserDetails);
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

    @GetMapping("/reset")
    public String reset(){
        return "reset";
    }

    @GetMapping("/resetExecute")
    public String resetExecute(HttpServletRequest request){
        commentService.reset();
        postService.reset();
        metadataService.reset();
        customUserDetailsService.reset();
        imageService.reset();

        request.setAttribute("msg", "모두 다 사라졌습니다");
        request.setAttribute("url", "/");
        return "alert";
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
        customUserDetailsService.register(customUserDetails, imageService.imageUpload(image));
        return "redirect:/loginForm";
    }

    @PostMapping("/duplicateCheck")
    public @ResponseBody int duplicateCheck(@RequestParam String userId) { // ID 중복 검색
        return customUserDetailsService.duplicateCheck(userId);
    }

    @PostMapping("/passwordCheck")
    public @ResponseBody boolean passwordCheck(@RequestBody CustomUserDetails customUserDetails) { // 비밀번호 조회
        return customUserDetailsService.passwordCheck(customUserDetails);
    }
}