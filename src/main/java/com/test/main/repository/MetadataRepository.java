package com.test.main.repository;

import com.test.main.dto.MetadataDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MetadataRepository {
    private final SqlSessionTemplate sql;

    public void upload() {
        sql.update("Metadata.upload");
    }

    public void delete() {
        sql.update("Metadata.delete");
    }

    public void userDelete(int postCount) {
        sql.update("Metadata.userDelete", postCount);
    }

    public void reset(){
        sql.update("Metadata.reset");
    }

    public MetadataDto postCount() {
        return sql.selectOne("Metadata.postCount");
    }
}
