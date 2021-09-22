package com.xx.core.test;

import com.xx.core.boot.ApplicationInitializer;
import com.xx.core.boot.InitializeException;
import org.springframework.context.ConfigurableApplicationContext;

public class CustomInitializer implements ApplicationInitializer {

    @Override
    public void init(ConfigurableApplicationContext appContext) throws InitializeException {
        registerBean(CustomTest.class, appContext);
    }
}
