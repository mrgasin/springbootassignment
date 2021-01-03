package com.alasdoo.developercourseassignment.integrationtests;

import com.alasdoo.developercourseassignment.integrationtests.config.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.alasdoo.developercourseassignment.integrationtests.utils.SnapshotUtils.captureScreenshot;

@Slf4j
public class FunctionalTest implements AfterEachCallback, AfterAllCallback, BeforeAllCallback, TestWatcher {

    protected static WebDriver webDriver;

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        webDriver.manage().deleteAllCookies();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        webDriver.close();
        log.info("Web driver closed.");
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        log.info("Web driver instantiation started");
        webDriver = WebDriverFactory.driver();
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
            String fileName = className + "_" + methodName;
            captureScreenshot(fileName, webDriver);
        }

        try {
            throw cause;
        } catch (Throwable ignored) {
        }
    }
}