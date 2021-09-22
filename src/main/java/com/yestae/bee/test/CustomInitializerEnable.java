package com.yestae.bee.test;

import com.yestae.bee.boot.EnableInitializer;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@EnableInitializer(CustomInitializer.class)
public @interface CustomInitializerEnable {

    String value();
}
