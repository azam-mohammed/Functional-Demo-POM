package com.selenium.test.utils;

import com.selenium.test.utils.properties.PropertiesLoader;
import com.selenium.test.utils.properties.Property;
import com.selenium.test.utils.properties.PropertyFile;

/**
 * Class for base tests properties - urls for test, browser name and version
 */
@PropertyFile("config.properties")
public class TestsConfig {

    private static TestsConfig config;

    public static TestsConfig getConfig() {
        if (config == null) {
            config = new TestsConfig();
        }
        return config;
    }

    public TestsConfig() {
        PropertiesLoader.populate(this);
    }

    @Property("browser.name")
    private String browser = "chrome";

    @Property("browser.version")
    private String version = "";

}
