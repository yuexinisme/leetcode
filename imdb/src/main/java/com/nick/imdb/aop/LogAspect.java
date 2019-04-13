package com.nick.imdb.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Nick Yuan
 * @date 2019/4/11
 * @mood shitty
 */
@Component
@Aspect
public class LogAspect {
    private static final Logger LOGGER= LoggerFactory.getLogger(LogAspect.class);
    @Pointcut("@annotation(com.nick.imdb.annotation.Log)")
    public void pointcut(){

    }
    @Around("pointcut()")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String requestURI = request.getRequestURI();
        LOGGER.error("Your mother shops at AX! {}",requestURI);
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }
}
