package com.yestae.bee.tools.retry;

import com.yestae.bee.boot.InitializeException;
import com.yestae.bee.boot.ModuleInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ExceptionRetryInitalizer implements ModuleInitializer<ExceptionRetryEnable> {

    @Override
    public void init(ConfigurableApplicationContext appContext) throws InitializeException {
        registerBean(ExceptionRetryAspect.class, appContext);
    }

    @Override
    public void init(ExceptionRetryEnable enableAnno, ConfigurableApplicationContext appContext) throws InitializeException {

    }
}
