package com.test.main.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
public class CustomUserDetails implements UserDetails {
    private String userId;
    private String pw;
    private String userRole;
    private boolean enable;
    private String profileImage;
    private List<GrantedAuthority> authList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authList;
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
