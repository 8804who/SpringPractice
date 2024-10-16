package com.test.main.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class LogDto {
    private int logNum;
    private Timestamp logDt;
    private String userId;
    private String userIp;
    private int pageNum;
    private String pageName;
    private String pageUrl;
    private String pageDc;
}
