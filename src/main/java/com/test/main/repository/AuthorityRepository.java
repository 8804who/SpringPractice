package com.test.main.repository;

import com.test.main.dto.AuthorityDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class AuthorityRepository {
    private final SqlSessionTemplate sql;

    public List<AuthorityDto> getUserAuthorities(String userRole) {
        return sql.selectList("Authority.getUserAuthorities", userRole);
    }
}
