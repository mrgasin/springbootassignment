package com.alasdoo.developercourseassignment.integrationtests.teachercourses;

import com.alasdoo.developercourseassignment.integrationtests.PageObject;
import com.alasdoo.developercourseassignment.integrationtests.courses.CoursePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TeacherCoursesPage extends PageObject {

    public static final String baseUrl = "http://localhost:3000/teacher";

    private final WebDriverWait wait;

    @FindBy(css = "button[data-test-id = 'courses']")
    private WebElement toggleCourses;

    @FindBy(xpath = "/html/body/div/div/main/div[2]/div[3]/div[1]/form/div[2]/button[1]")
    private WebElement save;

    @FindBy(css = "div[aria-haspopup = 'listbox']")
    private WebElement courseInput;

    @FindBy(xpath = "/html/body/div/div/main/div[2]/div[3]/div[1]/form/div[2]/button[1]")
    private WebElement delete;

    @FindBy(css = "button[data-test-id = 'add-courses']")
    private WebElement add;

    @FindBy(css = "div[role = 'row']")
    List<WebElement> teachers;

    @FindBy(xpath = "/html/body/div/div/main/div[2]/div[3]/div[2]/div[1]/div")
    private WebElement teacherCoursesTable;

    @FindBy(css = "ul[role = 'listbox'] li[role = 'option']")
    List<WebElement> coursesList;

    @FindBy(css = ".makeStyles-mainContent-4 > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div[role = 'row']")
    private List<WebElement> tableCourses;

    @FindBy(xpath = "/html/body/div/div/main/div[2]/div[2]/div/button/span[1]")
    private WebElement close;

    public TeacherCoursesPage(WebDriver webDriver) {
        super(webDriver);
        wait = new WebDriverWait(webDriver, 10);
    }

    public void assignCourse(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(teachers));
        WebElement teacher = teachers.get(index);
        wait.until(ExpectedConditions.visibilityOf(teacher));
        wait.until(ExpectedConditions.elementToBeClickable(teacher));
        teacher.click();
        wait.until(ExpectedConditions.visibilityOf(toggleCourses));
        wait.until(ExpectedConditions.elementToBeClickable(toggleCourses));
        toggleCourses.click();
        wait.until(ExpectedConditions.elementToBeClickable(add));
        add.click();

        wait.until(ExpectedConditions.visibilityOf(courseInput));
        wait.until(ExpectedConditions.elementToBeClickable(courseInput));
        courseInput.click();
        int randomIndex = (int) (Math.random() * (coursesList.size() - 1));
        WebElement course = coursesList.get(randomIndex);
        scrollTo(course);
        wait.until(ExpectedConditions.visibilityOf(course));
        wait.until(ExpectedConditions.elementToBeClickable(course));
        course.click();

        wait.until(ExpectedConditions.invisibilityOf(course));
        wait.until(ExpectedConditions.visibilityOf(save));
        wait.until(ExpectedConditions.elementToBeClickable(save));
        save.click();
    }

    public void removeCourse(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(teachers));
        WebElement teacher = teachers.get(index);
        wait.until(ExpectedConditions.visibilityOf(teacher));
        wait.until(ExpectedConditions.elementToBeClickable(teacher));
        teacher.click();
        wait.until(ExpectedConditions.visibilityOf(toggleCourses));
        wait.until(ExpectedConditions.elementToBeClickable(toggleCourses));
        toggleCourses.click();
        wait.until(ExpectedConditions.visibilityOf(teacherCoursesTable));
        int size = Integer.parseInt(teacherCoursesTable.getAttribute("aria-rowcount"));
        WebElement course = tableCourses.get((int) (Math.random() * size));
        wait.until(ExpectedConditions.elementToBeClickable(course));
        wait.until(ExpectedConditions.visibilityOf(course));
        course.click();
        wait.until(ExpectedConditions.visibilityOf(delete));
        wait.until(ExpectedConditions.elementToBeClickable(delete));
        delete.click();
    }

    public Integer numberOfCourses(int index) {
        wait.until(webDriver -> webDriver.findElements(By.cssSelector("div[role = 'row']")));
        WebElement teacher = webDriver.findElements(By.cssSelector("div[role = 'row']")).get(index);
        wait.until(ExpectedConditions.visibilityOf(teacher));
        wait.until(ExpectedConditions.elementToBeClickable(teacher));
        teacher.click();
        wait.until(ExpectedConditions.visibilityOf(toggleCourses));
        wait.until(ExpectedConditions.elementToBeClickable(toggleCourses));
        toggleCourses.click();
        wait.until(ExpectedConditions.visibilityOf(teacherCoursesTable));
        int size = Integer.parseInt(teacherCoursesTable.getAttribute("aria-rowcount"));
        wait.until(ExpectedConditions.visibilityOf(close));
        wait.until(ExpectedConditions.elementToBeClickable(close));
        close.click();
        return size;
    }

    private List<Integer> getCourseIds() {
        webDriver.navigate().to(CoursePage.baseUrl);
        List<WebElement> courses = webDriver.findElements(By.cssSelector("div[data-field = 'id']"));
        wait.until(ExpectedConditions.visibilityOfAllElements(courses));
        List<Integer> ids = new ArrayList<>();
        courses.forEach(course -> ids.add(Integer.parseInt(course.getText())));
        webDriver.navigate().to(baseUrl);
        return ids;
    }
    private void scrollTo(WebElement element) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element);
        actions.perform();
    }
}
