package com.yestae.bee.tools.retry;

import com.yestae.bee.boot.EnableInitializer;

import java.lang.annotation.*;

/**
 * 重试机制开关控制
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableInitializer(ExceptionRetryInitalizer.class)
public @interface ExceptionRetryEnable {
}
