package com.alasdoo.developercourseassignment.integrationtests.students;

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

public class StudentPage extends PageObject {

    static final String baseUrl = "http://localhost:3000/student";

    private WebDriverWait wait;

    @FindBy(tagName = "h6")
    private WebElement header;

    @FindBy(name = "name")
    private WebElement name;

    @FindBy(name = "surname")
    private WebElement surname;

    @FindBy(name = "accountName")
    private WebElement accountName;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "bankCardNumber")
    private WebElement bankCardNumber;

    @FindBy(xpath = "/html/body/div/div/main/div[2]/button")
    private WebElement add;

    @FindBy(css = "button[data-test-id = 'save']")
    private WebElement save;

    @FindBy(css = "button[data-test-id = 'delete']")
    private WebElement delete;

    @FindBy(css = ".makeStyles-mainContent-4 div[role = 'grid']")
    private WebElement table;

    @FindBy(css = "div[role = 'row']")
    private List<WebElement> tableRows;

    public StudentPage(WebDriver webDriver) {
        super(webDriver);
        wait = new WebDriverWait(webDriver, 10);
    }

    public String getHeaderText() {
        return header.getText();
    }

    public void addStudent() {
        Student student = FakerGenerator.newStudent();
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(add));
        addButton.click();
        name.sendKeys(student.getName());
        surname.sendKeys(student.getSurname());
        email.sendKeys(student.getEmail());
        accountName.sendKeys(student.getAccountName());
        bankCardNumber.sendKeys(student.getBankCardNumber().toString());
        save.click();
    }

    public void updateStudent(int index) {
        if (numberOfElements() > 0 && index < numberOfElements()) {
            WebElement row = tableRows.get(index);
            wait.until(ExpectedConditions.visibilityOf(row));
            wait.until(ExpectedConditions.elementToBeClickable(row));
            row.click();
            name.sendKeys(" II");
            surname.sendKeys(" II");
            save.click();
        }
    }

    public void deleteStudent(int index) {
        if (numberOfElements() > 0) {
            WebElement student = tableRows.get(index);
            wait.until(ExpectedConditions.visibilityOf(student));
            wait.until(ExpectedConditions.elementToBeClickable(student));
            student.click();
            delete.click();
        }
    }

    public Integer numberOfElements() {
        return Integer.parseInt(table.getAttribute("aria-rowcount"));
    }

    public Integer oldNameLength(int index) {
        if (numberOfElements() > 0) {
            WebElement student = tableRows.get(index).findElement(By.cssSelector("div[data-field = 'name']"));
            wait.until(ExpectedConditions.visibilityOf(student));
            String oldName = student.getText();
            return oldName.length();
        }
        return 0;
    }

    public Integer updatedNameLength() {
        return name.getAttribute("value").length();
    }
}
