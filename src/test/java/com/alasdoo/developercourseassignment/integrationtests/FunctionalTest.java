package com.alasdoo.developercourseassignment.integrationtests;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

@Slf4j
public class FunctionalTest {

    protected static WebDriver webDriver;

    @BeforeAll
    static void setup() {
        log.info("Web driver instantiation started");
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void cleanup() {
        webDriver.manage().deleteAllCookies();
    }

    @AfterAll
    static void teardown() {
        webDriver.close();
        log.info("Web driver closed.");
    }
}