package com.xx.core.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.xx.core", "${xx.config.scanPackage}" })
public class XXStartter {

    public static ConfigurableApplicationContext run(final Class<?> clas, String args[]) {
        return SpringApplication.run(clas, args);
    }
}
