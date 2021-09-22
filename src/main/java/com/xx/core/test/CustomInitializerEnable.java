package com.xx.core.test;

import com.xx.core.boot.EnableInitializer;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@EnableInitializer(CustomInitializer.class)
public @interface CustomInitializerEnable {
}
