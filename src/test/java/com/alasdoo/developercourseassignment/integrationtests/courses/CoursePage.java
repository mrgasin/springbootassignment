package com.alasdoo.developercourseassignment.integrationtests.courses;

import com.alasdoo.developercourseassignment.entities.DeveloperCourse;
import com.alasdoo.developercourseassignment.integrationtests.PageObject;
import com.alasdoo.developercourseassignment.integrationtests.utils.FakerGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CoursePage extends PageObject {
    public static final String baseUrl = "http://localhost:3000/course";

    private WebDriverWait wait;

    @FindBy(name = "developerCourseName")
    private WebElement courseName;

    @FindBy(name = "costPerClass")
    private WebElement costPerClass;

    @FindBy(name = "classesPerWeek")
    private WebElement classesPerWeek;

    @FindBy(css = "button[aria-label = 'add']")
    private WebElement add;

    @FindBy(css = "button[data-test-id = 'save']")
    private WebElement save;

    @FindBy(css = "button[data-test-id = 'delete']")
    private WebElement delete;

    @FindBy(css = ".makeStyles-mainContent-4 div[role = 'grid']")
    private WebElement table;

    @FindBy(css = "div[role = 'row']")
    private List<WebElement> courses;

    public CoursePage(WebDriver webDriver) {
        super(webDriver);
        wait = new WebDriverWait(webDriver, 10);
    }

    public void addCourse() {
        DeveloperCourse course = FakerGenerator.newCourse();
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(add));
        addButton.click();
        courseName.sendKeys(course.getDeveloperCourseName());
        classesPerWeek.sendKeys(course.getClassesPerWeek().toString());
        costPerClass.sendKeys(course.getCostPerClass().toString());
        save.click();
    }

    public void updateCourse(int index) {
        if (numberOfElements() > 0) {
            wait.until(ExpectedConditions.visibilityOfAllElements(courses));
            WebElement course = courses.get(index);
            wait.until(ExpectedConditions.visibilityOf(course));
            wait.until(ExpectedConditions.elementToBeClickable(course));
            course.click();
            course.getAttribute("value");
            courseName.sendKeys(" updated");
            int oldValue = oldPrice(index);
            int newValue = oldValue + 10;
            costPerClass.clear();
            costPerClass.sendKeys(Integer.toString(newValue));
            save.click();
        }
    }

    public void deleteCourse(int index) {
        if (numberOfElements() > 0) {
            wait.until(ExpectedConditions.visibilityOfAllElements(courses));
            WebElement course = courses.get(index);
            wait.until(ExpectedConditions.visibilityOf(course));
            wait.until(ExpectedConditions.elementToBeClickable(course));
            course.click();
            delete.click();
        }
    }

    public Integer numberOfElements() {
        return Integer.parseInt(table.getAttribute("aria-rowcount"));
    }

    public Integer oldPrice(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(courses));
        WebElement course = courses.get(index).findElement(By.cssSelector("div[data-field = 'costPerClass']"));
        wait.until(ExpectedConditions.visibilityOf(course));
        String value = course.getText();
        return Integer.parseInt(value.substring(0, value.indexOf(",")));
    }

    public Integer price() {
        return Integer.parseInt(costPerClass.getAttribute("value"));
    }
}
