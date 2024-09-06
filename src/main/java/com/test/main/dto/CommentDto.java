package com.test.main.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDto {
    private int commentId;
    private String userId;
    private int postId;
    private String commentContents;
}
