package com.test.main;


import java.sql.Timestamp;
import java.util.Date;

import com.test.main.dto.LogDto;
import com.test.main.dto.PageInfoDto;
import com.test.main.service.LogService;
import com.test.main.service.PageInfoService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;

@Slf4j
@Aspect
@Component
public class AccessLogger {
    @Resource
    private PageInfoService pageInfoService;

    @Resource
    private LogService logService;

    @Around("execution(@org.springframework.web.bind.annotation.GetMapping * *(..)) && execution(public * com.test.main.controller.*Controller.*(..)) && @annotation(getMapping)")
    public Object getController(ProceedingJoinPoint joinPoint, GetMapping getMapping) throws Throwable {
        Object object;
        try{
            String path = getMapping.value()[0];
            PageInfoDto pageInfoDto = pageInfoService.readPageInfo(path);
            Timestamp ts = new Timestamp(new Date().getTime());

            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            String userIp = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();

            LogDto logDto = new LogDto();

            logDto.setLogDt(ts);
            logDto.setUserId(userId);
            logDto.setUserIp(userIp);
            logDto.setPageNum(pageInfoDto.getPageNum());
            logDto.setPageName(pageInfoDto.getPageName());
            logDto.setPageUrl(pageInfoDto.getPageUrl());
            logDto.setPageDc(pageInfoDto.getPageDc());

            logService.saveLog(logDto);
        }
        catch(NullPointerException e)
        {
            log.info("로그 기록 과정에서 에러가 발생하였습니다.");
        }
        finally {
            object = (Object)joinPoint.proceed();
        }
        return object;
    }

    @Around("execution(@org.springframework.web.bind.annotation.PostMapping * *(..)) && execution(public * com.test.main.controller.*Controller.*(..)) && @annotation(postMapping)")
    public Object postController(ProceedingJoinPoint joinPoint, PostMapping postMapping) throws Throwable {
        Object object;
        try{
            String path = postMapping.value()[0];
            PageInfoDto pageInfoDto = pageInfoService.readPageInfo(path);
            Timestamp ts = new Timestamp(new Date().getTime());

            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            String userIp = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();

            LogDto logDto = new LogDto();

            logDto.setLogDt(ts);
            logDto.setUserId(userId);
            logDto.setUserIp(userIp);
            logDto.setPageNum(pageInfoDto.getPageNum());
            logDto.setPageName(pageInfoDto.getPageName());
            logDto.setPageUrl(pageInfoDto.getPageUrl());
            logDto.setPageDc(pageInfoDto.getPageDc());

            logService.saveLog(logDto);
        }
        catch(NullPointerException e)
        {
            log.info("로그 기록 과정에서 에러가 발생하였습니다.");
        }
        finally {
            object = (Object)joinPoint.proceed();
        }
        return object;
    }
}
