package com.test.main.repository;

import com.test.main.dto.PageInfoDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class PageInfoRepository {
    private final SqlSessionTemplate sql;

    public PageInfoDto readPageInfo(String pageUrl) {
        return sql.selectOne("PageInfo.readPageInfo", pageUrl);
    }
}
