package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * This page is a page object example.
 */
public class WilliamHillPlaceBetPage extends BasePage {

    @FindBy(className = "leftPanel")
    private WebElement leftPanel;

    @FindBy(className = "icon-accountLI")
    private WebElement profileIcon;

    @FindBy(css = "#accountTabButton > span.account-tab__text.-account")
    private WebElement profileBalanceElem;

    @FindBy(id = "nav-football")
    private WebElement football;
    @FindBy(id = "nav-tennis") //TODO not yet implemented
    private WebElement tennis;
    @FindBy(id = "nav-horse-racing") //TODO not yet implemented
    private WebElement horseRacing;
    @FindBy(id = "nav-cricket")   //TODO not yet implemented
    private WebElement cricket;
    @FindBy(id = "nav-rugby-union") //TODO not yet implemented
    private WebElement rugbyUnion;


    public WilliamHillPlaceBetPage() {
        super(false);
    }

    @Override
    protected void openPage() {
        //do nothing, as this is automatically loaded, if you login
    }

    @Override
    public boolean isPageOpened() {
        try {
            return leftPanel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean profileIconPresent() {
        try {
            return profileIcon.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public double getProfileBalance() {
        System.out.println(profileBalanceElem.getText());
        return Double.parseDouble(profileBalanceElem.getText().substring(1));
    }

    public WilliamHillSportPage clickOnAPopularSport(String sport) {
        getSportLink(sport).click();
        return new WilliamHillSportPage();
    }

    private WebElement getSportLink(String sport) {
        switch (sport.toLowerCase()) {
            case "football":
                return football;
            case "tennis":
                return tennis;
            case "horses":
                return horseRacing;
            case "cricket":
                return cricket;
            case "rugby u":
                return rugbyUnion;
            default:
                throw new UnsupportedOperationException("provided sport not available - " + sport);
        }
    }

}
