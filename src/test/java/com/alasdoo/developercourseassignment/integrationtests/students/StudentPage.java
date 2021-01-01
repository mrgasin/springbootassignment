package com.alasdoo.developercourseassignment.integrationtests.students;

import com.alasdoo.developercourseassignment.entities.Student;
import com.alasdoo.developercourseassignment.integrationtests.PageObject;
import com.alasdoo.developercourseassignment.integrationtests.utils.FakerGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StudentPage extends PageObject {

    static final String baseUrl = "http://localhost:3000/student";

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

    @FindBy(css = "div[role = 'row']")
    private List<WebElement> tableRows;

    @FindBy(xpath = "/html/body/div/div/main/div[2]/div/div/div[1]/div/div[3]/div/div[2]/div/p")
    private WebElement paginated;

    public StudentPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getHeaderText() {
        return header.getText();
    }

    public void addStudent() {
        Student student = FakerGenerator.newStudent();
        add.click();
        name.clear();
        name.sendKeys(student.getName());
        surname.clear();
        surname.sendKeys(student.getSurname());
        email.clear();
        email.sendKeys(student.getEmail());
        accountName.clear();
        accountName.sendKeys(student.getAccountName());
        bankCardNumber.clear();
        bankCardNumber.sendKeys(student.getBankCardNumber().toString());
        save.click();
    }

    public void updateStudent(int index) throws InterruptedException {
        if (numberOfElements() > 0) {
            WebElement row = tableRows.get(index);
            Thread.sleep(2000);
            row.click();
            String oldName = name.getAttribute("value");
            name.sendKeys(" II");
            String oldSurname = surname.getAttribute("value");
            surname.sendKeys(" II");
            save.click();
            Thread.sleep(2000);
        }
    }

    public void deleteStudent(int index) {
        try {
            if (numberOfElements() > 0) {
                WebElement row = tableRows.get(index);
                Thread.sleep(2000);
                row.click();
                Thread.sleep(2000);
                delete.click();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        String oldName = tableRows.get(index).findElement(By.cssSelector("div[data-field = 'name']")).getText();
        return oldName.length();
    }

    public Integer updatedNameLength() {
        return name.getAttribute("value").length();
    }
}
