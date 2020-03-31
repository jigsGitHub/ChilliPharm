package com.chillipharm.pages;

import com.chillipharm.utilities.CommonUtility;
import com.chillipharm.utilities.DriverProvider;
import com.chillipharm.utilities.ReadProperties;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonUtility {

    private final WebDriver driver;
    private final String url;
    public static ReadProperties properties;
    private String email, password;

    @FindBy(xpath = "//img[contains(@alt,'ChilliPharm | Medical images securely stored')]")
    public WebElement chilliPharmLogo;

    @FindBy(xpath = "//a[contains(.,'Login')]")
    public WebElement loginButton;

    @FindBy(xpath = "//input[@id = 'user_email']")
    public WebElement emailTextField;

    @FindBy(xpath = "//input[@id = 'user_password']")
    public WebElement passwordTextField;

    @FindBy(xpath = "//input[@value = 'Sign in']")
    public WebElement signInButton;

    @FindBy(xpath = "//ul/li[1]")
    public WebElement emptyEmailFieldErrorMsg;

    @FindBy(xpath = "//ul/li")
    public WebElement emptyFieldErrorMsg;

    @FindBy(xpath = "//ul/li[2]")
    public WebElement emptyPasswordFieldErrorMsg;

    @FindBy(xpath = "//div[@class='inline-message error']")
    public WebElement invalidCredentialsErrorMsg;

    @FindBy(xpath = "//div[@class='popupbox-wrapper']")
    public WebElement warningPopUpWindow;

    @FindBy(xpath = "//div[@class='popupbox-titleBar']/span")
    public WebElement accountLockoutWarningTitle;

    @FindBy(xpath = "//div[@class='popupbox-content']")
    public WebElement warningPopUpContent;

    @FindBy(xpath = "//button[contains(.,'I understand')]")
    public WebElement BtnIUnderstand;

    @FindBy(xpath = "//div[@class='recaptcha-checkbox-border']")
    public WebElement recaptchaCheckbox;


    public LoginPage() {
        driver = DriverProvider.driver;
        url = DriverProvider.TEST_URL;
        properties = new ReadProperties();
        PageFactory.initElements(driver, this);
    }

    public void load() {
        driver.get(url);
        Assert.assertTrue("ChilliPharm Home Page loading issue", isElementVisible(chilliPharmLogo));
    }

    public void clickOnLoginBtn() {
        loginButton.click();
    }

    public void loginAs(String username, String password) throws Throwable {
        emailTextField.sendKeys(username);
        passwordTextField.sendKeys(password);
        signInButton.click();
    }

    public boolean verifyEmailEmptyFieldErrorMsg() {
        boolean emailOption = false;
        if(emptyEmailFieldErrorMsg.getText().equalsIgnoreCase("Email is required"))
        emailOption = true;
        return emailOption;
    }

    public boolean verifyPasswordEmptyFieldErrorMsg(){
        boolean passwordOption = false;
        if(emptyPasswordFieldErrorMsg.getText().equalsIgnoreCase("Password is required"))
        passwordOption = true;
        return passwordOption;
    }

    public boolean verifyOnlyEmailEmptyFieldErrorMsgIsDisplayed() {
        boolean errorOption = false;
        if(emptyFieldErrorMsg.getText().equalsIgnoreCase("Email is required"))
            errorOption = true;
        return errorOption;
    }

    public boolean verifyOnlyPasswordEmptyFieldErrorMsgIsDisplayed() {
        boolean errorOption = false;
        if(emptyFieldErrorMsg.getText().equalsIgnoreCase("Password is required"))
            errorOption = true;
        return errorOption;
    }

    public boolean verifyInvalidLoginCredentialsErrorMsgIsDisplaye() {
        boolean credentialsOption = false;
        if(invalidCredentialsErrorMsg.getText().equalsIgnoreCase("Incorrect email or password"))
            credentialsOption = true;
        return credentialsOption;
    }

    public void enterInvalidCredentialsThreeTimes(String email, String password) throws Throwable {
        for (int i=0; i<3;i++) {
            loginAs(email, password);
            signInButton.click();
        }
    }

    public void enterInvalidCredentialsThreeTimes() {
        email = properties.getEmail();
        emailTextField.sendKeys(email);
        for (int i=0; i<3; i++) {
            passwordTextField.sendKeys(properties.getInvalidPassword());
            signInButton.click();
        }
    }

    public boolean verifyAccountLockoutWarningMessageOnThirdAttempt() {
        boolean popUpWindowOption = false;
        boolean warningTitleOption = false;
        boolean warningContentOption = false;
        if(warningPopUpWindow.isDisplayed())
            popUpWindowOption = true;
        if(accountLockoutWarningTitle.getText().equalsIgnoreCase("Account Lockout: Warning"))
            warningTitleOption = true;
        if(warningPopUpContent.getText().contains("This is the third consecutive failed attempt to login with email address "+email+"."))
            warningContentOption = true;
        return popUpWindowOption && warningTitleOption && warningContentOption;
    }

    public void enterInvalidCredentialsFiveTimes() {
        email = properties.getEmail();
        emailTextField.sendKeys(email);
        for (int i=0; i<5; i++) {
            passwordTextField.sendKeys(properties.getInvalidPassword());
            clickIfElementPresent(BtnIUnderstand);
            clickIfElementPresent(recaptchaCheckbox);
            signInButton.click();
        }
    }

    public boolean verifyAccountLockoutWarningMessageOnFifthAttempt() {
        boolean popUpWindowOption = false;
        boolean warningTitleOption = false;
        boolean warningContentOption = false;
        if(warningPopUpWindow.isDisplayed())
            popUpWindowOption = true;
        System.out.println("title : "+accountLockoutWarningTitle.getText());
        if(accountLockoutWarningTitle.getText().equalsIgnoreCase("Account Lockout"))
            warningTitleOption = true;
        System.out.println("content : "+warningPopUpContent.getText());
        if(warningPopUpContent.getText().contains("Too many consecutive attempts to log in with email address: "+email+".\n" +
                "If an account exists with this email address, it has been locked."))
            warningContentOption = true;
        return popUpWindowOption && warningTitleOption && warningContentOption;
    }
}
