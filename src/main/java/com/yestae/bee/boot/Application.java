/**
 *
 */
package com.yestae.bee.boot;

import java.lang.annotation.*;

/**
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Application {

    String value();

    String[] scanPackage() default {};
}
