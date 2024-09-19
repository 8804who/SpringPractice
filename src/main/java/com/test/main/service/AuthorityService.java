package com.test.main.service;

import com.test.main.dto.AuthorityDto;
import com.test.main.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public List<AuthorityDto> getUserAuthorities(String userRole){
        return authorityRepository.getUserAuthorities(userRole);
    }
}
