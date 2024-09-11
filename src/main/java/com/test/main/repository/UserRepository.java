package com.test.main.repository;

import com.test.main.security.CustomUserDetails;
import com.test.main.security.CustomUserDetailsService;
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

    public void register(CustomUserDetails customUserDetails) {
        sql.insert("User.register", customUserDetails);
    }

    public int duplicateCheck(String userId) {
        return sql.selectOne("User.duplicateCheck", userId);
    }
}
