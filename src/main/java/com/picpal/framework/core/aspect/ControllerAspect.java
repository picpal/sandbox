package com.picpal.framework.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    // Controller 레이어의 모든 메서드를 포인트컷으로 정의
    @Pointcut("execution(* com.picpal.framework.sample.controller..*(..))")
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void beforeControllerMethod(JoinPoint joinPoint) {
        logger.info("Entering Controller: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @After("controllerPointcut()")
    public void afterControllerMethod(JoinPoint joinPoint) {
        logger.info("Exiting Controller: {}", joinPoint.getSignature());
    }

    @AfterThrowing(pointcut = "controllerPointcut()", throwing = "exception")
    public void afterControllerException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in Controller: {} with cause: {}", joinPoint.getSignature(), exception.getMessage());
    }
}
