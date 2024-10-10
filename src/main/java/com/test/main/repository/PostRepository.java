package com.test.main.repository;

import com.test.main.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final SqlSessionTemplate sql;

    public void upload(PostDto postDto) {
        sql.insert("Post.upload", postDto);
    }

    public void delete(PostDto postDto) {
        sql.delete("Post.delete", postDto);
    }

    public void update(PostDto postDto) { sql.update("Post.edit", postDto); }

    public int countPost(String userId)
    {
        return sql.selectOne("Post.countPost", userId);
    }

    public PostDto read(PostDto postDto) { return sql.selectOne("Post.read", postDto);}

    public List<PostDto> list(int startNumber) {
        return sql.selectList("Post.list", startNumber);
    }
}
