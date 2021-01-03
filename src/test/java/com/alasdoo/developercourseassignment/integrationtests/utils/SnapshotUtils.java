package com.alasdoo.developercourseassignment.integrationtests.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.alasdoo.developercourseassignment.integrationtests.utils.WebDriverFactory.BROWSER;

@Slf4j
public class SnapshotUtils {

    private SnapshotUtils() {
    }

    public static void captureScreenshot(String fileName, WebDriver webDriver) {
        FileOutputStream out = null;
        try {
            Date date = new Date();
            String dateString = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(date);
            new File("src/test/resources/screenshots").mkdirs(); // Insure directory is there
            out = new FileOutputStream("src/test/resources/screenshots/screenshot_" + FileUtils.getValue(BROWSER) + "_" + CaseFormat.toLowerUnderscore(fileName) + "_" + dateString + ".png");
            out.write(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            log.info("Taking screenshot failed!");
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException ignored) {
            }
        }
    }
}
