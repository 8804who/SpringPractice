package com.test.main.security;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final SqlSessionTemplate sql;

    public CustomUserDetails getUserById(String userId) {
        return sql.selectOne("User.getUserById", userId);
    }
}
