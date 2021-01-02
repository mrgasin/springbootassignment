package com.alasdoo.developercourseassignment.integrationtests.studentcourses;

import com.alasdoo.developercourseassignment.integrationtests.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StudentCoursesPage extends PageObject {

    public static final String baseUrl = "http://localhost:3000/student";

    private final WebDriverWait wait;

    @FindBy(name = "classesBought")
    private WebElement classesBought;

    @FindBy(css = "div[aria-haspopup = 'listbox']")
    private WebElement courseInput;

    @FindBy(css = "button[data-test-id = 'courses']")
    private WebElement toggleCourses;

    @FindBy(css = ".makeStyles-actions-16 > button:nth-child(1)")
    private WebElement save;

    @FindBy(css = " .makeStyles-actions-16 > button:nth-child(3)")
    private WebElement delete;

    @FindBy(css = "button[data-test-id = 'add-courses']")
    private WebElement add;

    @FindBy(css = "div[role = 'row']")
    List<WebElement> students;

    @FindBy(xpath = "/html/body/div/div/main/div[2]/div[3]/div[2]/div[1]/div")
    private WebElement studentCoursesTable;

    @FindBy(css = "ul[role = 'listbox'] li[role = 'option']")
    List<WebElement> coursesList;

    @FindBy(css = ".makeStyles-mainContent-4 div[role = 'grid']")
    private WebElement table;

    @FindBy(xpath = "/html/body/div/div/main/div[2]/div[2]/div/button/span[1]")
    private WebElement close;

    @FindBy(css = ".makeStyles-mainContent-4 > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div[role = 'row']")
    private List<WebElement> tableCourses;

    public StudentCoursesPage(WebDriver webDriver) {
        super(webDriver);
        wait = new WebDriverWait(webDriver, 10);
    }

    public void buyCourse(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(students));
        WebElement student = students.get(index);
        wait.until(ExpectedConditions.visibilityOf(student));
        wait.until(ExpectedConditions.elementToBeClickable(student));
        student.click();
        wait.until(ExpectedConditions.visibilityOf(toggleCourses));
        wait.until(ExpectedConditions.elementToBeClickable(toggleCourses));
        toggleCourses.click();
        wait.until(ExpectedConditions.elementToBeClickable(add));
        add.click();
        int numberOfClasses = (int) (Math.random() * 25 + 1);
        classesBought.sendKeys(Integer.toString(numberOfClasses));
        wait.until(ExpectedConditions.visibilityOf(courseInput));
        wait.until(ExpectedConditions.elementToBeClickable(courseInput));
        courseInput.click();
        WebElement course = coursesList.get((int) (Math.random() * coursesList.size() - 1));
        wait.until(ExpectedConditions.visibilityOf(course));
        wait.until(ExpectedConditions.elementToBeClickable(course));
        course.click();
        wait.until(ExpectedConditions.invisibilityOf(course));
        wait.until(ExpectedConditions.visibilityOf(save));
        wait.until(ExpectedConditions.elementToBeClickable(save));

        save.click();
    }

    public void cancelCourse(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(students));
        WebElement student = students.get(index);
        wait.until(ExpectedConditions.elementToBeClickable(student));
        student.click();
        wait.until(ExpectedConditions.visibilityOf(toggleCourses));
        wait.until(ExpectedConditions.elementToBeClickable(toggleCourses));
        toggleCourses.click();
        wait.until(ExpectedConditions.visibilityOf(studentCoursesTable));
        int size = Integer.parseInt(studentCoursesTable.getAttribute("aria-rowcount"));
        WebElement course = tableCourses.get((int) (Math.random() * size));
        wait.until(ExpectedConditions.elementToBeClickable(course));
        course.click();
        wait.until(ExpectedConditions.elementToBeClickable(delete));
        delete.click();
    }

    public void updateStudentCourse(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(students));
        WebElement student = students.get(index);
        wait.until(ExpectedConditions.elementToBeClickable(student));
        student.click();
        wait.until(ExpectedConditions.visibilityOf(toggleCourses));
        wait.until(ExpectedConditions.elementToBeClickable(toggleCourses));
        toggleCourses.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(studentCoursesTable));
        WebElement course = tableCourses.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(course));
        String numberOfCourses = course.findElement(By.cssSelector("div[data-field = 'classesBought']")).getText();
        course.click();
        wait.until(ExpectedConditions.elementToBeClickable(classesBought));
        int newNumber = Integer.parseInt(numberOfCourses) + 5;
        classesBought.clear();
        classesBought.sendKeys(Integer.toString(newNumber));
        wait.until(ExpectedConditions.elementToBeClickable(save));
        save.click();
    }

    public Integer numberOfStudents() {
        return Integer.parseInt(table.getAttribute("aria-rowcount"));
    }

    private String studentId(int index) {
        WebElement idColumn = students.get(index).findElement(By.cssSelector("div[data-field = 'id']"));
        return idColumn.getText();
    }

    private int courseId(int index) {
        WebElement course = coursesList.get(index);
        return Integer.parseInt(course.getAttribute("data-value"));
    }

    public int numberOfCourses(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(students));
        WebElement student = students.get(index);
        wait.until(ExpectedConditions.visibilityOf(student));
        wait.until(ExpectedConditions.elementToBeClickable(student));
        student.click();
        wait.until(ExpectedConditions.visibilityOf(toggleCourses));
        wait.until(ExpectedConditions.elementToBeClickable(toggleCourses));
        toggleCourses.click();
        wait.until(ExpectedConditions.visibilityOf(studentCoursesTable));
        int size = Integer.parseInt(studentCoursesTable.getAttribute("aria-rowcount"));
        wait.until(ExpectedConditions.visibilityOf(close));
        wait.until(ExpectedConditions.elementToBeClickable(close));
        close.click();
        return size;
    }

    public int numberOfCoursesBought(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(students));
        WebElement student = students.get(index);
        wait.until(ExpectedConditions.visibilityOf(student));
        wait.until(ExpectedConditions.elementToBeClickable(student));
        student.click();
        wait.until(ExpectedConditions.visibilityOf(toggleCourses));
        wait.until(ExpectedConditions.elementToBeClickable(toggleCourses));
        toggleCourses.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(tableCourses));
        WebElement course = tableCourses.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(course));
        int numberOfCourses = Integer.parseInt(course.findElement(By.cssSelector("div[data-field = 'classesBought']")).getText());
        wait.until(ExpectedConditions.elementToBeClickable(close));
        close.click();
        return numberOfCourses;
    }
}
