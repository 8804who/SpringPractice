package com.test.main;

import com.test.main.interceptor.AllowedIpInterceptor;
import com.test.main.interceptor.BannedIpInterceptor;
import com.test.main.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private IpService ipService;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new BannedIpInterceptor(ipService))
                .excludePathPatterns("/banned");
        registry.addInterceptor(new AllowedIpInterceptor(ipService))
                .addPathPatterns("/reset"
                ,"/resetExecute");
    }

    @Bean
    public FilterRegistrationBean UserFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new UserFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
