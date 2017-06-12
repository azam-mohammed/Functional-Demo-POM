package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This page is a page object example.
 */
public class WilliamHillHomePage extends BasePage {

    private static final String PAGE_URL = "http://sports.williamhill.com/sr-admin-set-white-list-cookie.html";

    @FindBy(className = "leftPanel")
    private WebElement leftPanel;

    @FindBy(id = "accountTabButton")
    private WebElement loginButton;

    @FindBy(id = "loginUsernameInput")
    private WebElement usernameInput;
    @FindBy(id = "loginPasswordInput")
    private WebElement passwordInput;
    @FindBy(id = "loginButton")
    private WebElement loginSubmitButton;

    public WilliamHillHomePage() {
        super(true);
    }

    public WilliamHillHomePage(String pageUrl) {
        super(true);
    }

    @Override
    protected void openPage() {
        getDriver().get(PAGE_URL);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return leftPanel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickOnLogin() {
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOf(loginSubmitButton));
    }

    public WilliamHillPlaceBetPage loginToWebsite() {
        usernameInput.sendKeys("WHATA_FOG2");
        passwordInput.sendKeys("F0gUaTtest");
        loginSubmitButton.click();
        return new WilliamHillPlaceBetPage();
    }

}
