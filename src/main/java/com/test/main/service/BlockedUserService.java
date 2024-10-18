package com.test.main.service;

import com.test.main.repository.BlockedUserRepository;
import com.test.main.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class BlockedUserService {
    private final BlockedUserRepository blockedUserRepository;

    public String whenIsReleaseDate(String userId){
        return blockedUserRepository.whenIsReleaseDate(userId);
    }

    public boolean isBlocked(String userId){
        return blockedUserRepository.isBlocked(userId);
    }

    public void blockUser(CustomUserDetails customUserDetails) {
        blockedUserRepository.blockUser(customUserDetails);
    }
}
