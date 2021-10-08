/**
 * 
 */
package com.yestae.bee.config;

import com.yestae.bee.boot.Application;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Properties;

/**
 */
public class BeeClientLocalProperties extends Properties {

    /**
	 * 
	 */
    private static final long serialVersionUID = -827905326646091100L;


    private String scanPackage = "com.yestae";

    private String basePackage = "com.yestae";

    private String appName = "bee";

    private String rootProperties = "bee.properties";

    private Class<?> applicationClasss;

    public void load() {
        loadDefaultFromClass();
//        loadDefaultFromBeeProperties();
//        loadDefaultFromAppProperties();
        loadDefaultFromSystem();
    }

    public void loadFromXML(InputStream inputStream) {
        loadDefaultFromClass();
        try {
            super.loadFromXML(inputStream);
        } catch (IOException e) {
        }
    }

    public void load(Reader reader) {
        loadDefaultFromClass();
        try {
            super.load(reader);
        } catch (IOException e) {
        }
    }

    public void load(InputStream inputStream) {
        try {
            super.load(inputStream);
        } catch (IOException e) {
            // logger.warn("load property fail.");
        }
        resetDefault();
    }

    private void resetDefault() {
        String appNamePro = getProperty(Constants.CONFIG_APPNAME_KEY);
        String scanPackageNamePro = getProperty(Constants.CONFIG_SCANPACKAGE_KEY);
        if (!StringUtils.isBlank(scanPackageNamePro)) {
            this.scanPackage = scanPackageNamePro;
        }
        if (StringUtils.isBlank(this.appName)) {
            this.appName = appNamePro;
        }
        if (StringUtils.isBlank(this.appName)) {
            throw new IllegalArgumentException("the application not found.");
        }
        setProperty(Constants.CONFIG_APPNAME_KEY, this.appName);
    }

    protected void loadDefaultFromBeeProperties() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(rootProperties);
        if (inputStream != null) {
            load(inputStream);
        }
    }

    protected void loadDefaultFromAppProperties() {
        if (!StringUtils.isBlank(this.appName)) {
            InputStream appSelfProperties = this.getClass().getClassLoader().getResourceAsStream(this.appName + ".properties");
            if (appSelfProperties != null) {
                load(appSelfProperties);
            }
        }
        // 先找找默认的 configs/appname.properties
        InputStream coustemProperties = null;
        String coustemFile = "configs/" + this.appName + ".properties";
        try {
            coustemProperties = new FileInputStream(coustemFile);
        } catch (FileNotFoundException e) {
        }
        if (coustemProperties == null) {
            coustemFile = System.getProperty("bee.config.location");
            if (StringUtils.isBlank(coustemFile)) {
                coustemFile = System.getProperty("spring.config.location");
            }
            if (!StringUtils.isBlank(coustemFile)) {
                coustemProperties = this.getClass().getClassLoader().getResourceAsStream(coustemFile);
                if (coustemProperties == null) {
                    try {
                        coustemProperties = new FileInputStream(coustemFile);
                    } catch (FileNotFoundException e) {
                    }
                }
            }
        }
        if (coustemProperties != null) {
            load(coustemProperties);
        }
    }

    protected void loadDefaultFromSystem() {
        Properties p = System.getProperties();
        this.putAll(p);
        this.putAll(System.getenv());
    }

    @SuppressWarnings({ "restriction", "deprecation" })
    protected void loadDefaultFromClass() {
        Class<?> parentClass = null;
        Application startClass = null;
        int i = 1;
        while ((parentClass = sun.reflect.Reflection.getCallerClass(i)) != null) {
            if (parentClass != null) {
                startClass = parentClass.getAnnotation(Application.class);
                if (startClass != null) {
                    break;
                }
            }
            i++;
        }
        if(startClass != null) {
            this.applicationClasss = parentClass;
            this.appName = startClass.value();
            if(startClass.scanPackage() != null && startClass.scanPackage().length > 0) {
                this.scanPackage = StringUtils.join(startClass.scanPackage(), ",");
            }
        }
    }

    public String getAppName() {
        return appName;
    }

    public String getScanPackage() {
        return scanPackage;
    }

    public String getBasePackage() {return basePackage; }

    public Class<?> getApplicationClasss() {
        return applicationClasss;
    }
}
