package com.test.main.repository;

import com.test.main.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final SqlSessionTemplate sql;

    public void upload(CommentDto commentDto) {
        sql.insert("Comment.upload", commentDto);
    }

    public void delete(CommentDto commentDto) {
        sql.delete("Comment.delete", commentDto);
    }

    public void update(CommentDto commentDto) { sql.update("Comment.edit", commentDto); }

    public List<CommentDto> list(int postId) {
        return sql.selectList("Comment.list", postId);
    }
}
