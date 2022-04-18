package com.yestae.bee.tools.retry;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionRetry {

    /**
     * 重试次数
     *
     * @return
     */
    int count() default 3;

    /**
     * 重试的间隔时间  ms
     *
     * @return
     */
    long sleep() default 3000;

    /**
     * 要重试的异常
     *
     * @return
     */
    Class<? extends Throwable>[] value() default {Throwable.class};
}