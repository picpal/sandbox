package com.picpal.framework.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MapperAspect {

    private static final Logger logger = LoggerFactory.getLogger(MapperAspect.class);

    // MyBatis Mapper 패키지의 모든 메서드를 포인트컷으로 정의
    @Around("execution(* com.picpal.framework.sample.mapper..*(..))")
    public Object logMapperMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();

        logger.info("Start Mapper Method: {}.{} with arguments: {}", className, methodName, joinPoint.getArgs());
        long startTime = System.currentTimeMillis();

        Object result;
        try {
            // 메서드 실행
            result = joinPoint.proceed();
        } catch (Exception e) {
            logger.error("Exception in Mapper Method: {}.{} - {}", className, methodName, e.getMessage());
            throw e;
        }

        long duration = System.currentTimeMillis() - startTime;
        logger.info("End Mapper Method: {}.{} - Duration: {} ms", className, methodName, duration);
        return result;
    }
}