package com.test.main.repository;

import com.test.main.dto.LogDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class LogRepository {
    private final SqlSessionTemplate sql;

    public void saveLog(LogDto logDto) {
        sql.insert("Log.saveLog", logDto);
    }
}
