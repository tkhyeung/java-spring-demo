package com.example.java.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAdvice {
    /**
     JoinPoint for @Before, @After, @AfterReturning, @AfterThrowing
     ProceedingJoinPoint for @Around
     execution(Modifier ReturnType DeclaringType.Method(Parameter)
     (*) = one argument of any type
     (..) = 0 or more argument of any type
     */

    //pointcut designator (PCD)
    //first * = any return type
    @Around("execution(* com.example.java.service.*.*(..))")
    public Object aroundService(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        log.info("Aspect @Around: Before executing method: {}", method);
        Object[] args = proceedingJoinPoint.getArgs();
        for(Object e: args){
            log.info("Arg: {}", e);
        }
        Object o = proceedingJoinPoint.proceed();
        log.info("Aspect @Around: After executing method: {}", method);
        return o ;
    }
}
