package com.xx.core.boot;

import java.lang.annotation.*;

/**
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableInitializers {

    EnableInitializer[] value();
    
}
