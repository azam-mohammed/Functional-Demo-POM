package com.selenium.test.stepDefs;


import com.selenium.test.pages.WilliamHillHomePage;
import com.selenium.test.pages.WilliamHillPlaceBetPage;
import com.selenium.test.pages.WilliamHillSportPage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class WilliamHillStepDefs {

    private WilliamHillHomePage homePage;
    private WilliamHillPlaceBetPage placeBetPage;
    private WilliamHillSportPage sportPage;
    private double initialBalance;
    private double finalBalance;
    private double totalStake;
    private double totalStakeActual;
    private double toReturn;
    private double rate;

    @Given("^User is on \"([^\"]*)\" page$")
    public void user_is_on_page(String pageName) throws Throwable {
        WebDriverFactory.startBrowser(true);
        switch (pageName) {
            case "home": {
                homePage = new WilliamHillHomePage();
            }
            break;
        }
    }

    @When("^User clicks login button$")
    public void user_clicks_login_button() throws Throwable {
        homePage.clickOnLogin();
    }

    @When("^Input correct username and password and submit$")
    public void input_correct_username_and_password_and_submit() throws Throwable {
        placeBetPage = homePage.loginToWebsite();
    }

    @Then("^User is redirected to bets page$")
    public void user_is_redirected_to_bets_page() throws Throwable {
        assertTrue("Bets page was not opened", placeBetPage.profileIconPresent());
        initialBalance = placeBetPage.getProfileBalance();
    }

    @Then("^User navigates to \"([^\"]*)\" event$")
    public void user_navigates_to_event(String sport) throws Throwable {
        sportPage = placeBetPage.clickOnAPopularSport(sport);
    }

    @When("^User adds first active selection to bet slip$")
    public void user_adds_first_active_selection_to_bet_slip() throws Throwable {
        rate = sportPage.selectFirstActiveBet();
    }

    @When("^User places a bet of \"([^\"]*)\"$")
    public void user_places_a_bet_of(String amount) throws Throwable {
        sportPage.placeBet(amount);
        totalStake = Double.valueOf(amount);
        totalStakeActual = sportPage.getTotalStake();
        toReturn = sportPage.getTotalToReturn();
        sportPage.clickOnButton();
    }

    @Then("^Total stake and Total return is validated$")
    public void total_stake_and_Total_return_is_validated() throws Throwable {
        assertTrue("Total Stake is not same as input", totalStake == totalStakeActual);
        String calculated = ((1 + rate) * totalStake) + "";
        System.out.println(calculated + " == " + toReturn);
        assertTrue("To Return is not as expected", calculated.contains(toReturn + ""));
    }

    @Then("^User balance is reduced by \"([^\"]*)\"$")
    public void user_balance_is_reduced_by(String arg1) throws Throwable {
        Thread.sleep(3000);
        finalBalance = sportPage.getProfileBalance();
        System.out.println(totalStake);
        String expected = (initialBalance - totalStake) + "";
        System.out.println(expected);
        assertTrue("Final balance - " + finalBalance + " not equal to expected", expected.contains(finalBalance + ""));
    }


}
