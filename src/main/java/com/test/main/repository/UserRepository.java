package com.test.main.repository;

import com.test.main.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final SqlSessionTemplate sql;

    public void register(UserDto userDto) {
        sql.insert("User.register", userDto);
    }

    public void delete(UserDto userDto) {
        sql.delete("User.delete", userDto);
    }

    public void login(UserDto userDto) { sql.selectOne("User.login", userDto); }
}
