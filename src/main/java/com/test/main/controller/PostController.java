package com.test.main.controller;

import com.test.main.dto.PostDto;
import com.test.main.service.CommentService;
import com.test.main.service.MetadataService;
import com.test.main.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/post")
public class PostController {
    private final CommentService commentService;
    private final PostService postService;
    private final MetadataService metadataService;

    @GetMapping("/read")
    public String readPost(PostDto postDto, Model model) { // 게시글 페이지
        model.addAttribute("post", postService.read(postDto));
        model.addAttribute("commentList", commentService.list(postDto.getPostId()));
        return "read";
    }
    
    @GetMapping("/write")
    public String writePost(){ // 글 작성 페이지
        return "write";
    }

    @GetMapping("/edit")
    public String editPost(PostDto postDto, Model model){ // 글 수정 페이지
        model.addAttribute("post", postService.read(postDto));
        return "edit";
    }

    @PostMapping("/upload")
    public String uploadPost(PostDto postDto){ // 글 업로드
        postService.upload(postDto);
        metadataService.upload();
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updatePost(PostDto postDto){ // 글 수정
        postService.update(postDto);
        return "redirect:/post/read?postId="+postDto.getPostId();
    }

    @PostMapping("/delete")
    public String deletePost(PostDto postDto){ // 글 삭제
        postService.delete(postDto);
        metadataService.delete();
        return "redirect:/";
    }
}