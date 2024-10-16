package com.test.main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfoDto {
    private int pageNum;
    private String pageName;
    private String pageUrl;
    private String pageDc;
}
