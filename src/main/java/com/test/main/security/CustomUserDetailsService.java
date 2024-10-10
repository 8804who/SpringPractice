package com.test.main.security;

import com.test.main.dto.AuthorityDto;
import com.test.main.repository.AuthorityRepository;
import com.test.main.repository.UserRepository;
import com.test.main.service.MetadataService;
import com.test.main.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PostService postService;
    private final MetadataService metadataService;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        CustomUserDetails user = userRepository.getUserById(userId);
        if(user == null){
            throw new UsernameNotFoundException("존재하지 않는 계정입니다.");
        }
        user.setAuthList(getUserAuthorities(user.getUserRole()));
        return user;
    }

    public List<GrantedAuthority> getUserAuthorities(String userRole)
    {
        List<AuthorityDto> authorities = authorityRepository.getUserAuthorities(userRole);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (AuthorityDto authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
        }
        return grantedAuthorities;
    }

    public void register(CustomUserDetails customUserDetails, String imagePath) {
        customUserDetails.setPw(new BCryptPasswordEncoder().encode(customUserDetails.getPassword()));
        customUserDetails.setProfileImage(imagePath);
        userRepository.register(customUserDetails);
    }

    public int duplicateCheck(String userId) {
        return userRepository.duplicateCheck(userId);
    }

    public List<CustomUserDetails> getUserList()
    {
        return userRepository.getUserList();
    }

    public void userDelete(CustomUserDetails customUserDetails)
    {
        String folderPath = "C:\\Users\\user\\IdeaProjects\\board\\src\\main\\webapp\\resources\\img\\profile\\";
        String imagePath = userRepository.getProfileImage(customUserDetails);
        File file = new File(folderPath+imagePath);
        if(file.exists() ){
            if(file.delete()){
                log.debug("파일삭제 성공");
            }else{
                log.debug("파일삭제 성공");
            }
        }else{
            log.debug("파일삭제 성공");
        }
        int countPost = postService.countPost(customUserDetails.getUserId());
        metadataService.userDelete(countPost);
        userRepository.userDelete(customUserDetails);
    }

    public void userActivate(CustomUserDetails customUserDetails)
    {
        userRepository.userActivate(customUserDetails);
    }

    public void userDeactivate(CustomUserDetails customUserDetails)
    {
        userRepository.userDeactivate(customUserDetails);
    }

    public boolean passwordCheck(CustomUserDetails customUserDetails)
    {
        return new BCryptPasswordEncoder().matches(customUserDetails.getPassword(), userRepository.passwordCheck(customUserDetails));
    }
}
