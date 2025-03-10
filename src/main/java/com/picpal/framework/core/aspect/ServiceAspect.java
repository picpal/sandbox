package com.picpal.framework.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    // Service 레이어의 모든 메서드를 포인트컷으로 정의
    @Pointcut("execution(* com.picpal.framework.sample.service..*(..))")
    public void servicePointcut() {
    }

    @Around("servicePointcut()")
    public Object aroundServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        logger.info("Service Method Start: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());

        Object result;
        try {
            result = joinPoint.proceed(); // 서비스 메서드 실행
        } catch (Exception e) {
            logger.error("Exception in Service Method: {} with cause: {}", joinPoint.getSignature(), e.getMessage());
            throw e;
        }

        long duration = System.currentTimeMillis() - startTime;
        logger.info("Service Method End: {} with result: {} - Duration: {} ms", joinPoint.getSignature(), result, duration);
        return result;
    }
}
