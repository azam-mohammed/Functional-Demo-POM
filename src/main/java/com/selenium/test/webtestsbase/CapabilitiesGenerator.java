package com.selenium.test.webtestsbase;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/**
 * need for some browsers start // TODO can be refactored in Tech DEBT
 */
public class CapabilitiesGenerator {

    public static DesiredCapabilities getDefaultCapabilities(Browser browser) {
        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_mac64");
                if (System.getProperty("webdriver.chrome.driver") == null) {
                    throw new IllegalStateException("System variable 'webdriver.chrome.driver' should be set to path for executable driver");
                }
                return DesiredCapabilities.chrome();
            case ANDROID_CHROME:
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "Google Nexus 5");

                Map<String, Object> chromeOptions = new HashMap<String, Object>();
                chromeOptions.put("mobileEmulation", mobileEmulation);
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                return capabilities;

            // TODO below are non functional
            case FIREFOX:
                return DesiredCapabilities.firefox();
            case IE10:
                DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                caps.setVersion("10");
                return caps;
            case SAFARI:
                return new DesiredCapabilities();
            default:
                throw new IllegalStateException("Browser is not supported");
        }
    }

}
