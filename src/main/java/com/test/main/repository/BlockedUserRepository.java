package com.test.main.repository;

import com.test.main.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;


@Repository
@RequiredArgsConstructor
public class BlockedUserRepository {
    private final SqlSessionTemplate sql;

    public String whenIsReleaseDate(String userId){
        return sql.selectOne("BlockedUser.whenIsReleaseDate", userId);
    }

    public boolean isBlocked(String userId) {
        return sql.selectOne("BlockedUser.isBlocked", userId);
    }

    public void blockUser(CustomUserDetails customUserDetails) {
        sql.insert("BlockedUser.blockUser", customUserDetails);
    }
}
