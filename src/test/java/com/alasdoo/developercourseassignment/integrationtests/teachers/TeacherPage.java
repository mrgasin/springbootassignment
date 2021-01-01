package com.alasdoo.developercourseassignment.integrationtests.teachers;

import com.alasdoo.developercourseassignment.entities.Student;
import com.alasdoo.developercourseassignment.integrationtests.PageObject;
import com.alasdoo.developercourseassignment.integrationtests.utils.FakerGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TeacherPage extends PageObject {
    public static final String baseUrl = "http://localhost:3000/teacher";
    private WebDriverWait wait;
    @FindBy(name = "teacherName")
    private WebElement name;

    @FindBy(name = "teacherSurname")
    private WebElement surname;

    @FindBy(name = "teacherEmail")
    private WebElement email;


    @FindBy(xpath = "/html/body/div/div/main/div[2]/button")
    private WebElement add;

    @FindBy(css = "button[data-test-id = 'save']")
    private WebElement save;

    @FindBy(css = "button[data-test-id = 'delete']")
    private WebElement delete;


    @FindBy(css = "div[role = 'row']")
    private List<WebElement> teachers;

    @FindBy(xpath = "/html/body/div/div/main/div[2]/div/div/div[1]/div/div[3]/div/div[2]/div/p")
    private WebElement paginated;

    public TeacherPage(WebDriver webDriver) {
        super(webDriver);
        wait = new WebDriverWait(webDriver, 10);
    }

    public void addTeacher() {
        Student student = FakerGenerator.newStudent();
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(add));
        addButton.click();
        name.clear();
        name.sendKeys(student.getName());
        surname.clear();
        surname.sendKeys(student.getSurname());
        email.clear();
        email.sendKeys(student.getEmail());
        save.click();
    }

    public void updateTeacher(int index) {
        if (numberOfElements() > 0) {
            WebElement row = teachers.get(index);
            wait.until(ExpectedConditions.elementToBeClickable(row));
            wait.until(ExpectedConditions.visibilityOf(row));
            row.click();
            name.sendKeys(" II");
            surname.sendKeys(" II");
            save.click();
        }
    }

    public void deleteTeacher(int index) {
        if (numberOfElements() > 0) {
            WebElement row = teachers.get(index);
            WebElement teacher = wait.until(ExpectedConditions.elementToBeClickable(row));
            teacher.click();
            delete.click();
        }
    }

    public Integer numberOfElements() {
        if (paginated != null) {
            String text = paginated.getText();
            return Integer.parseInt(text.substring(text.lastIndexOf("f") + 1).trim());
        }
        return 0;
    }

    public Integer oldNameLength(int index) {
        WebElement teacher = teachers.get(index).findElement(By.cssSelector("div[data-field = 'teacherName']"));
        wait.until(ExpectedConditions.visibilityOf(teacher));
        wait.until(ExpectedConditions.elementToBeClickable(teacher));
        String oldName = teacher.getText();
        return oldName.length();
    }

    public Integer updatedNameLength() {
        return name.getAttribute("value").length();
    }
}
