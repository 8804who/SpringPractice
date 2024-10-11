package com.test.main.service;

import com.test.main.dto.CommentDto;
import com.test.main.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void upload(CommentDto commentDto){
        commentRepository.upload(commentDto);
    }

    public void update(CommentDto commentDto){
        commentRepository.update(commentDto);
    }

    public void delete(CommentDto commentDto){
        commentRepository.delete(commentDto);
    }

    public void reset(){
        commentRepository.reset();
    }

    public List<CommentDto> list(int postId){
        return commentRepository.list(postId);
    }
}
