package com.yestae.bee.boot;

import java.lang.annotation.*;

/**
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableInitializers {

    EnableInitializer[] value();

}
