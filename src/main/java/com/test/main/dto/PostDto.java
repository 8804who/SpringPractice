package com.test.main.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDto {
    private String postTitle;
    private String userId;
    private int postId;
    private String postContents;
}
