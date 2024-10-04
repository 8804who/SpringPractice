package com.test.main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/post/read",
                        "/loginForm",
                        "/loginCheck",
                        "/registerForm",
                        "/register",
                        "/duplicateCheck",
                        "/errorPage",
                        "/banned",
                        "/resources/css/board.css",
                        "/resources/js/jquery-3.7.1.min.js"
                ).permitAll() // 해당 페이지는 인증 없이 허용
                .antMatchers(
                        "/userManagement",
                        "/userDelete",
                        "/userActivate",
                        "/userDeactivate"
                ).hasAuthority("user_management")
                .anyRequest().authenticated() // 이외 페이지는 모두 인증 필요
        ;

        http
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/loginCheck")
                .usernameParameter("userId")
                .passwordParameter("pw")
        ;

        http
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
        ;

        http
                .rememberMe()
                .key("내가 만든 쿠키")
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(86400 * 7)
                .userDetailsService(customUserDetailsService)
        ;
    }
}