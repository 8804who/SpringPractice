package com.test.main.repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class BlockedUserRepository {
    private final SqlSessionTemplate sql;

    public boolean isBlocked(String userId) {
        return sql.selectOne("BlockedUser.isBlocked", userId);
    }

    public void blockUser(String userId) {
        sql.insert("BlockedUser.blockUser", userId);
    }
}
