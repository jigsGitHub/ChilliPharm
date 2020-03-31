package com.chillipharm.utilities;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;

import static com.chillipharm.utilities.CommonUtility.absolutePath;

public class DriverProvider {

    public static WebDriver driver;
    private static String TEST_ENV;
    public static String TEST_URL;
    public static String BROWSER;
    public static final String DRIVER_PATH = "/drivers/osx/";

    @Before
    public void setDriver() throws IOException {

        TEST_ENV = ReadConfig.readConfigProperty().getProperty("TEST_ENVIRONMENT");
        TEST_URL = ReadConfig.readConfigProperty().getProperty(TEST_ENV);
        BROWSER = BROWSER == null ? "chrome" : BROWSER;

        switch (BROWSER) {

            case "chrome": {
                loadChromeDriver(absolutePath + DRIVER_PATH + "chromedriver");
                break;
            }
        }
    }

    private void loadChromeDriver(String driverPath) {
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver();
        CommonUtility.maximizeScreen();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
            driver.close();
            driver.quit();
        } catch (Exception e) {

        }
    }
}
