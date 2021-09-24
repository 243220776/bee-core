/**
 *
 */
package com.yestae.bee.config;

import org.apache.commons.lang3.StringUtils;

/**
 */
public class BeeClientConfiguration {

    private static BeeClientConfiguration INSTANCE = new BeeClientConfiguration();

    private BeeClientLocalProperties localProperies;

    private BeeClientConfiguration() {
        localProperies = new BeeClientLocalProperties();
        localProperies.load();
        String p2 = (String) localProperies.get(Constants.CONFIG_HTTP_PORT);
        if (!StringUtils.isBlank(p2)) {
            localProperies.put(Constants.CONFIG_HTTP_PORT, Integer.parseInt(p2));
            localProperies.put("server.port", Integer.parseInt(p2));
        }
        localProperies.put(Constants.CONFIG_APPNAME_KEY, localProperies.getAppName());
        localProperies.put("spring.application.name", localProperies.getAppName());
    }

    public static BeeClientConfiguration get() {
        return INSTANCE;
    }

    public static BeeClientConfiguration reload() {
        INSTANCE = new BeeClientConfiguration();
        return get();
    }

    public static BeeClientLocalProperties getLocalProperies() {
        return INSTANCE.localProperies;
    }

}
