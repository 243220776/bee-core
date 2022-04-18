package com.yestae.bee.boot;

import com.yestae.bee.config.BeeClientConfiguration;
import com.yestae.bee.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.yestae", "${bee.config.scanPackage}"})
public class BeeStarter {
    private static final Logger logger = LoggerFactory.getLogger(BeeStarter.class);

    public static void run(final Class<?> resrouce, String args[]) {
        setDefaultProperty();
        runSpring(resrouce, args);
    }

    private static void runSpring(final Class<?> resrouce, String args[]) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(resrouce);
        SpringApplication app = builder.build();
        final ConfigurableApplicationContext appContext = app.run(args);
        //jvm关闭时 优雅推出spring
        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {
                synchronized (resrouce) {
                    SpringApplication.exit(appContext);
                    logger.info("SpringApplication exited...");
                    resrouce.notify();
                }
            }
        });
        synchronized (resrouce) {
            while (true) {
                try {
                    resrouce.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private static void setDefaultProperty() {
//        System.setProperty("spring.cloud.config.enabled", "false");
        System.setProperty(Constants.CONFIG_APPNAME_KEY, BeeClientConfiguration.getLocalProperies().getAppName());
    }
}
