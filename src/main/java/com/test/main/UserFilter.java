package com.test.main;

import javax.servlet.*;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("함수 실행 전 필터 호출"+servletRequest+servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("함수 실행 후 필터 호출"+servletRequest.getAttributeNames());
    }
}
