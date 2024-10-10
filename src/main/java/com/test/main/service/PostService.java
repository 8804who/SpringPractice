package com.test.main.service;

import com.test.main.dto.PostDto;
import com.test.main.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void upload(PostDto postDto){
        postRepository.upload(postDto);
    }

    public void delete(PostDto postDto){
        postRepository.delete(postDto);
    }

    public void update(PostDto postDto){
        postRepository.update(postDto);
    }

    public int countPost(String userId)
    {
        return postRepository.countPost(userId);
    }

    public PostDto read(PostDto postDto){
        return postRepository.read(postDto);
    }

    public List<PostDto> list(int startNumber){
        return postRepository.list(startNumber);
    }
}
