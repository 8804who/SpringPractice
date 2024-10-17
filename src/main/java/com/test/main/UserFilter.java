package com.test.main;

import com.test.main.service.BlockedUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserFilter implements Filter {
    @Resource
    private BlockedUserService blockedUserService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if(blockedUserService==null){
            ServletContext servletContext = servletRequest.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            blockedUserService = webApplicationContext.getBean(BlockedUserService.class);
        }

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (blockedUserService.isBlocked(userId)) {
            res.sendRedirect("/blocked");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
