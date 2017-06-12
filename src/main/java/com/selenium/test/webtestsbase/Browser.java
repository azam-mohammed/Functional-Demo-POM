package com.selenium.test.webtestsbase;

/**
 * represents browsers.
 */
public enum  Browser {
    FIREFOX("firefox"),
    CHROME("chrome"),
    ANDROID_CHROME("android_chrome"),
    IE10("ie10"),
    SAFARI("safari");
    private String browserName;

    private Browser(String browserName) {
        this.browserName = browserName;
    }
}
