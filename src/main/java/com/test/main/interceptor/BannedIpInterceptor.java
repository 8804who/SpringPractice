package com.test.main.interceptor;

import com.test.main.repository.IpRepository;
import com.test.main.service.IpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;

@RequiredArgsConstructor
public class BannedIpInterceptor implements HandlerInterceptor {
    private final IpService ipService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();

        boolean banned = ipService.ipBanCheck(ipAddress);

        if(banned){
            response.sendRedirect(request.getContextPath()+"/banned");
            return false;
        }
        else return true;
    }
}
