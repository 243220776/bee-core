package com.yestae.bee.test;

import com.yestae.bee.boot.InitializeException;
import com.yestae.bee.boot.ModuleInitializer;
import org.springframework.context.ConfigurableApplicationContext;


public class CustomInitializer implements ModuleInitializer<CustomInitializerEnable> {

    @Override
    public void init(CustomInitializerEnable enableAnno, ConfigurableApplicationContext appContext) throws InitializeException {
        System.out.println(enableAnno.value());
        registerBean(CustomTest.class, appContext);
    }

    @Override
    public void init(ConfigurableApplicationContext appContext) throws InitializeException {
    }
}
