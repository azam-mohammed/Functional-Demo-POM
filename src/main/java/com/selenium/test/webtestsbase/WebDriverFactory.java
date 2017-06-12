package com.selenium.test.webtestsbase;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

/**
 * Communicating with driver
 */
public class WebDriverFactory {
    private static final long IMPLICIT_WAIT_TIMEOUT = 5;
    private static WebDriver driver;

    /**
     * @return webdriver object, or throw IllegalStateException, if driver has not been initialized
     */
    public static WebDriver getDriver() {
        if (driver != null) {
            return driver;
        } else {
            throw new IllegalStateException("Driver has not been initialized. " +
                    "Please call WebDriverFactory.startBrowser() before use this method");
        }
    }

    /**
     * Main method of class - it initialize driver and starts browser.
     *
     * @param isLocal - is tests will be started local or not // TODO can make modification in Tech DEBT
     */
    public static void startBrowser(boolean isLocal) {
        if (driver == null) {
            Browser browser = Browser.CHROME;
            System.out.println(browser);
            if (!isLocal) {
                driver = new RemoteWebDriver(CapabilitiesGenerator.getDefaultCapabilities(browser));
            } else {
                switch (browser) {
                    case CHROME:
                        driver = new ChromeDriver(CapabilitiesGenerator.getDefaultCapabilities(Browser.CHROME));
                        break;
                    case ANDROID_CHROME:
                        driver = new ChromeDriver(CapabilitiesGenerator.getDefaultCapabilities(Browser.ANDROID_CHROME));
                        break;

                    //TODO non functional
                    case FIREFOX:
                        driver = new FirefoxDriver(CapabilitiesGenerator.getDefaultCapabilities(Browser.FIREFOX));
                        break;
                    //TODO non functional
                    case IE10:
                        driver = new InternetExplorerDriver(CapabilitiesGenerator.getDefaultCapabilities(Browser.IE10));
                        break;
                    //TODO non functional
                    case SAFARI:
                        driver = new SafariDriver(CapabilitiesGenerator.getDefaultCapabilities(Browser.SAFARI));
                        break;
                    default:
                        throw new IllegalStateException("Unsupported browser type");
                }
            }
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        } else {
            throw new IllegalStateException("Driver has already been initialized. Quit it before using this method");
        }
    }

    /**
     * Finishes browser not yet called
     */
    public static void finishBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Method for screenshot taking, need to decided which screenshot.
     */
    @After
    public static void takeScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }

}
