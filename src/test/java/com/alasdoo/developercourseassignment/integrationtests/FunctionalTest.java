package com.alasdoo.developercourseassignment.integrationtests;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FunctionalTest implements AfterEachCallback, AfterAllCallback, BeforeAllCallback, TestWatcher {

    protected static WebDriver webDriver;

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        webDriver.manage().deleteAllCookies();
    }

    private void captureScreenshot(String fileName) {
        FileOutputStream out = null;
        try {
            Date date = new Date();
            String dateString = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(date);
            new File("src/test/resources/screenshots").mkdirs(); // Insure directory is there
             out = new FileOutputStream("src/test/resources/screenshots/screenshot_" + fileName.toLowerCase() + "_" + dateString + ".png");
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

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        webDriver.close();
        log.info("Web driver closed.");
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        log.info("Web driver instantiation started");
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Optional<Method> method = context.getTestMethod();
        Optional<Class<?>> testClass = context.getTestClass();
        String methodName = "";
        String className = "";
        if (testClass.isPresent()) {
            className = testClass.get().getSimpleName();
        }
        if (method.isPresent()) {
            methodName = method.get().getName();
        }

        if (context.getRequiredTestInstance() instanceof FunctionalTest) {
            captureScreenshot(className + "_" + methodName);
        }

        try {
            throw cause;
        } catch (Throwable throwable) {
//            throwable.printStackTrace();
        }
    }
}