package com.yicj.aop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class HelloAspect {


    @Pointcut("execution (* com.yicj.aop.service.*.*(..))")
    public void testPointcut() {

    }

    @Before("testPointcut()")
    public void beforeAdvice() {
        log.info("==========> beforeAdvice...");
    }

    @After("testPointcut()")
    public void afterAdvice() {
        log.info("==========> afterAdvice...");
    }

    @Around("testPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("==========> aroundAdvice before");
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            log.error("error : ", t);
        }
        log.info("==========> aroundAdvice after");
        return null ;
    }

}
