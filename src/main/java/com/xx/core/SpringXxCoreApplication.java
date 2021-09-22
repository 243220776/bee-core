package com.xx.core;

import com.xx.core.boot.Application;
import com.xx.core.boot.XXStartter;
import com.xx.core.test.CustomInitializerEnable;

@Application("xx")
@CustomInitializerEnable
public class SpringXxCoreApplication extends XXStartter {

    public static void main(String[] args) {
        run(SpringXxCoreApplication.class, args);
    }

}
