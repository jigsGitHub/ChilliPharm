package com.chillipharm;

import com.chillipharm.pages.LoginPage;
import com.chillipharm.utilities.CommonUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Map;

import static com.chillipharm.utilities.CommonUtility.deleteCookies;

public class LoginPageStepDef {

    private final LoginPage loginPage;

    public LoginPageStepDef() {
        this.loginPage = new LoginPage();
    }

    @Given("^I am on ChilliPharm home page$")
    public void iAmOnChilliPharmHomePage() throws Throwable {
        try {
            deleteCookies();
            loginPage.load();
        } catch (Exception e) {
            Assert.fail("Unable to open the ChilliPharm Home Page");
        }
    }

    @And("^I navigate to Login page$")
    public void iNavigateToLoginPage() throws Throwable {
        loginPage.clickOnLoginBtn();

    }

    @When("^I enter email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iEnterUsernameAndPassword(String userName, String userPassword) throws Throwable {
        loginPage.loginAs(userName, userPassword);

    }

    @Then("^I should see the error message for \"([^\"]*)\" and password \"([^\"]*)\" on Login screen$")
    public void iShouldSeeTheErrorMessageForAndPasswordOnLoginScreen(String email, String password) throws Throwable {
        if (email.isEmpty() && password.isEmpty()) {
            Assert.assertTrue("Error message not displayed for empty Email field", loginPage.verifyEmailEmptyFieldErrorMsg());
            Assert.assertTrue("Error message not displayed for empty Password field", loginPage.verifyPasswordEmptyFieldErrorMsg());
        }else if(email.isEmpty() && !password.isEmpty()) {
            Assert.assertTrue("Error message not displayed for empty Email field", loginPage.verifyOnlyEmailEmptyFieldErrorMsgIsDisplayed());
        }else if(!email.isEmpty() && password.isEmpty()) {
            Assert.assertTrue("Error message not displayed for empty Password field", loginPage.verifyOnlyPasswordEmptyFieldErrorMsgIsDisplayed());
        }
    }

    @Then("^I should see the invalid credentials error message$")
    public void iShouldSeeTheErrorMessage() throws Throwable {
        Assert.assertTrue("Invalid credentials error message not displayed",loginPage.verifyInvalidLoginCredentialsErrorMsgIsDisplaye());
    }

    @When("^I enter invalid credentials three time consecutive as \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iEnterInvalidCredentialsThreeTimeConsecutiveAsAndPassword(String email, String password) throws Throwable {
        loginPage.enterInvalidCredentialsThreeTimes(email, password);
    }

    @When("^I enter invalid credentials three time consecutive with same email$")
    public void iEnterInvalidCredentialsThreeTimeConsecutiveWithSameEmail() throws Throwable {
        loginPage.enterInvalidCredentialsThreeTimes();
    }

    @Then("^I should see the account lockout warning after third consecutive failed attempt$")
    public void iShouldSeeTheAccountLockoutWarningPopupWindowWithErrorMessage() throws Throwable {
        Assert.assertTrue("Warning message not displayed on third consecutive failed attempt", loginPage.verifyAccountLockoutWarningMessageOnThirdAttempt());
    }

    @When("^I enter invalid credentials five time consecutive with same email$")
    public void iEnterInvalidCredentialsFiveTimeConsecutiveWithSameEmail() throws Throwable {
        loginPage.enterInvalidCredentialsFiveTimes();
    }

    @Then("^I should see the account lockout warning after fifth consecutive failed attempt$")
    public void iShouldSeeTheAccountLockoutWarningAfterFifthConsecutiveFailedAttempt() throws Throwable {
        Assert.assertTrue("Warning message not displayed on fifth consecutive failed attempt", loginPage.verifyAccountLockoutWarningMessageOnFifthAttempt());
    }
}
