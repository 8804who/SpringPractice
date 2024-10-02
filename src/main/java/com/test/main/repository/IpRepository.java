package com.test.main.repository;

import com.test.main.dto.AuthorityDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class IpRepository {
    private final SqlSessionTemplate sql;

    public void banIp(String ipAddress) {
        sql.insert("Ip.banIp", ipAddress);
    }

    public boolean ipBanCheck(String ipAddress) {
        return sql.selectOne("Ip.ipBanCheck", ipAddress);
    }
}
