package com.test.main.repository;

import com.test.main.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


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

    public List<CustomUserDetails> getUserList()
    {
        return sql.selectList("User.getUserList");
    }

    public void userDelete(CustomUserDetails customUserDetails)
    {
        sql.delete("User.userDelete", customUserDetails);
    }

    public void userActivate(CustomUserDetails customUserDetails){
        sql.update("User.userActivate", customUserDetails);
    }

    public void userDeactivate(CustomUserDetails customUserDetails){
        sql.update("User.userDeactivate", customUserDetails);
    }
}
