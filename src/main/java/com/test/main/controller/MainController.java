package com.test.main.controller;

import com.test.main.dto.PostDto;
import com.test.main.service.MetadataService;
import com.test.main.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final PostService postService;
    private final MetadataService metadataService;

    @GetMapping("/")
    public String readList(Model model, @RequestParam(required = false, defaultValue = "1") int page){ // 게시글 목록 페이지
        List<PostDto> postDtoList = postService.list((page-1)*10);
        model.addAttribute("postList", postDtoList);
        model.addAttribute("postCount", metadataService.postCount().getPostCount());
        model.addAttribute("page", page);
        return "list";
    }
}