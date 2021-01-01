package com.alasdoo.developercourseassignment.integrationtests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
    protected WebDriver webDriver;

    public PageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void navigateTo(String url) {
        webDriver.navigate().to(url);
    }
}
