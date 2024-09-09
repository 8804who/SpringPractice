package com.test.main.service;

import com.test.main.dto.UserDto;
import com.test.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(UserDto userDto){
        userRepository.register(userDto);
    }

    public void delete(UserDto userDto){
        userRepository.delete(userDto);
    }

    public void login(UserDto userDto){
        userRepository.login(userDto);
    }
}
