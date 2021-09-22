package com.yestae.bee.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.yestae", "${bee.config.scanPackage}" })
public class BeeStarter {

    public static ConfigurableApplicationContext run(final Class<?> clas, String args[]) {
        return SpringApplication.run(clas, args);
    }
}
