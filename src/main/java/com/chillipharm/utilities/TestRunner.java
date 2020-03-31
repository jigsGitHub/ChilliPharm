package com.chillipharm.utilities;

import com.cucumber.listener.ExtentCucumberFormatter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
@RunWith(Cucumber.class)
@CucumberOptions(glue = {"com.chillipharm"},
        features = {"src/test/resources/features/LoginPage.feature"},
        tags = {"@SmokeTest"},
        format = {"pretty", "json:report/report.json"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter"})

public class TestRunner {

    @BeforeClass
    public static void setup() {
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_hh:mm");
        String dateString = format.format(curDate);

        String reportFilePath = "report" + File.separator + "Test Results_" + dateString + "_report.html";
        ExtentCucumberFormatter.initiateExtentCucumberFormatter(new File(reportFilePath));
    }
}
