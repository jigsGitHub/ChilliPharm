package com.chillipharm.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.nio.file.Path;
import java.nio.file.Paths;
import static com.chillipharm.utilities.DriverProvider.driver;

public class CommonUtility {

    public static String absolutePath = rootPath();

    public static void maximizeScreen() {
        ((JavascriptExecutor) driver).executeScript("alert('Test')");
        driver.switchTo().alert().accept();
        driver.manage().window().fullscreen();
    }

    public static final String rootPath() {
        Path currentPath = Paths.get("");
        return currentPath.toAbsolutePath().toString();
    }

    public static Boolean isElementVisible(WebElement element) {
        try {
            hoverMouseOnWebelement(element);
            element.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static WebElement hoverMouseOnWebelement(WebElement webElement) {
        Actions action = new Actions(driver);

        action.moveToElement(webElement).moveToElement(webElement).build().perform();
        return webElement;
    }

    public static void deleteCookies() {
        driver.manage().deleteAllCookies();
    }

    public static boolean clickIfElementPresent(WebElement element) {
        try {
            element.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
