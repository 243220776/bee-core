package com.xx.core.boot;

import java.lang.annotation.*;

/**
 * @author zcy 2019年4月15日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableInitializers {

    EnableInitializer[] value();
    
}
