package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This page is a page object example.
 */
public class WilliamHillSportPage extends BasePage {

    private static final String PAGE_URL = "http://sports.williamhill.com/sr-admin-set-white-list-cookie.html";

    @FindBy(className = "leftPanel")
    private WebElement leftPanel;

    @FindBy(id = "accountTabButton")
    private WebElement loginButton;

    @FindBys(@FindBy(className = "betbutton__odds"))
    private List<WebElement> betButtonOdds;

    @FindBy(className = "link-viewall")
    private WebElement viewAllTodaysMatchesLink;


    @FindBy(css = "#betslip-tab > a > span > span")
    private WebElement betCount;

    @FindBy(css = "input[id^='stake-input__'")
    private WebElement inputElement;

    @FindBy(id = "total-to-return-price")
    private WebElement toReturn;

    @FindBy(id = "total-stake-price")
    private WebElement totalStake;

    @FindBy(id = "place-bet-button")
    private WebElement placeBetButton;

    @FindBy(css = "#accountTabButton > span.account-tab__text.-account")
    private WebElement profileBalanceElem;

    public WilliamHillSportPage() {
        super(false);
    }

    @Override
    protected void openPage() {
        //nothing to do, as browser loads page in base page call
    }

    @Override
    public boolean isPageOpened() {
        try {
            return leftPanel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public double selectFirstActiveBet() throws Exception {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        Thread.sleep(5000);
        WebElement firstActiveBet = betButtonOdds.stream().filter(WebElement::isEnabled).collect(Collectors.toList()).get(0);
        firstActiveBet.click();
        wait.until(ExpectedConditions.visibilityOf(betCount));
        String[] nums = firstActiveBet.getText().split("/");
        return Double.parseDouble(nums[0]) / Double.parseDouble(nums[1]);
    }

    public void placeBet(String bet) {
        inputElement.sendKeys(bet);
    }

    public double getTotalToReturn() {
        return Double.valueOf(toReturn.getText());
    }

    public double getTotalStake() {
        return Double.valueOf(totalStake.getText());
    }

    public void clickOnButton() {
        placeBetButton.click();
    }

    public double getProfileBalance() {
        return Double.parseDouble(profileBalanceElem.getText().substring(1));
    }

}
