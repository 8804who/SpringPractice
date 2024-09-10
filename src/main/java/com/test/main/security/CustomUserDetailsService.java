package com.test.main.security;

import com.test.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        CustomUserDetails users = userRepository.getUserById(userId);
        if(users == null){
            throw new UsernameNotFoundException("존재하지 않는 계정입니다.");
        }
        return users;
    }

    public void register(CustomUserDetails customUserDetails) {
        userRepository.register(customUserDetails);
    }
}
