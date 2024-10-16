package com.test.main.controller;

import com.test.main.dto.CommentDto;
import com.test.main.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/upload")
    public @ResponseBody String uploadComment(@RequestBody CommentDto commentDto){ // 댓글 업로드
        commentService.upload(commentDto);
        return null;
    }

    @PostMapping("/comment/update")
    public @ResponseBody String updateComment(@RequestBody CommentDto commentDto){ // 댓글 수정
        commentService.update(commentDto);
        return null;
    }

    @PostMapping("/comment/delete")
    public @ResponseBody String deleteComment(@RequestBody CommentDto commentDto){ // 댓글 삭제
        commentService.delete(commentDto);
        return null;
    }
}