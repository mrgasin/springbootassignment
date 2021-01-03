package com.alasdoo.developercourseassignment.integrationtests.config;

import com.alasdoo.developercourseassignment.integrationtests.utils.FileUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

@Slf4j
public class WebDriverFactory {
    public static final String BROWSER = "selenium.browser";

    private WebDriverFactory() {
    }

    public static WebDriver driver() {
        log.error(FileUtils.getValue(BROWSER));
        switch (FileUtils.getValue(BROWSER)) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "ie":
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "opera":
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            case "safari":
                return new SafariDriver();
            default:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
        }
    }
}
