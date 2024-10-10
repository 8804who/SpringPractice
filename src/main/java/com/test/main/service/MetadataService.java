package com.test.main.service;

import com.test.main.dto.MetadataDto;
import com.test.main.repository.MetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetadataService {
    private final MetadataRepository metadataRepository;

    public void upload(){
        metadataRepository.upload();
    }

    public void delete(){
        metadataRepository.delete();
    }

    public void userDelete(int postCount) {
        metadataRepository.userDelete(postCount);
    }

    public MetadataDto postCount(){
        return metadataRepository.postCount();
    }
}
