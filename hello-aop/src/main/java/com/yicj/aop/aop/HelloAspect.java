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
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("==========> aroundAdvice before");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        log.info("==========> aroundAdvice after");
    }

}
