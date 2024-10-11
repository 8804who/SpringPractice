package com.test.main.interceptor;

import com.test.main.service.IpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class AllowedIpInterceptor implements HandlerInterceptor {
    private final IpService ipService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();

        boolean allowed = ipService.ipAllowCheck(ipAddress);

        if(!allowed){
            response.sendRedirect(request.getContextPath()+"/");
            return false;
        }
        else return true;
    }
}
