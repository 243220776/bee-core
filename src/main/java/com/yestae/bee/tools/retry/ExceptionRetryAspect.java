package com.yestae.bee.tools.retry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Aspect
public class ExceptionRetryAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int defaultCount = 3;
    private static final long defaultSleep = 3000;

    public ExceptionRetryAspect() {
        System.out.println("ExceptionRetryAspect init...");
    }

    @Pointcut("@annotation(com.yestae.bee.tools.retry.ExceptionRetry)")
    public void retryPointCut() {
    }

    @Around("retryPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ExceptionRetry retry = method.getAnnotation(ExceptionRetry.class);
        String name = method.getName();
        int count = retry.count();
        long sleep = retry.sleep();

        Class[] excaptions = retry.value();

        if (count <= 0) {
            count = defaultCount;
        }

        if (sleep <= 0) {
            sleep = defaultSleep;
        }

        for (int i = 1; i <= count; i++) {
            try {
                Object obj = joinPoint.proceed();
                logger.info("第" + i + "次执行方法【" + name + "】成功！");
                return obj;
            } catch (Throwable e) {
                for (Class<?> ss : excaptions) {
                    if (e.getClass().equals(ss)) {
                        logger.error("第" + i + "次执行方法【" + name + "】失败！");
                        if (i == count) {
                            throw e;
                        }
                        Thread.sleep(sleep);
                    } else {
                        throw e;
                    }
                }
            }
        }
        return null;
    }
}
