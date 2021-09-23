package com.yestae.bee;

import com.yestae.bee.boot.BeeStarter;
import com.yestae.bee.boot.Application;
import com.yestae.bee.test.CustomInitializerEnable;

@Application("bee")
@CustomInitializerEnable(enable = false)
public class BeeApplication extends BeeStarter {

    public static void main(String[] args) {
        run(BeeApplication.class, args);
    }

}
