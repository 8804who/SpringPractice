package com.test.main;

import com.test.main.interceptor.BannedIpInterceptor;
import com.test.main.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private IpService ipService;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new BannedIpInterceptor(ipService))
            .excludePathPatterns("/banned");
    }
}
