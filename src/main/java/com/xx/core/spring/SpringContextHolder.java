package com.xx.core.spring;

import org.springframework.context.ApplicationContext;

/**
 */
public class SpringContextHolder {

    private static ApplicationContext context;

    public static void set(ApplicationContext c) {
        context = c;
    }

    public static ApplicationContext get() {
        return context;
    }
}
