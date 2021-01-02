package com.alasdoo.developercourseassignment.integrationtests.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
@Slf4j
public class SeleniumUtils {
    static void waitUntilReady(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void takeSnapshot(ITestResult result, WebDriver driver) {
//        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                java.io.File src = screenshot.getScreenshotAs(OutputType.FILE);
                log.error("result" , result);
                FileUtils.copyFile(src, new File("./screenshots" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
    }
}
