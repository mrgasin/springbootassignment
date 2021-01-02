package com.alasdoo.developercourseassignment.integrationtests.teachercourses;

import com.alasdoo.developercourseassignment.integrationtests.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeacherCoursesPage extends PageObject {

    private final WebDriverWait wait;

    public TeacherCoursesPage(WebDriver webDriver) {
        super(webDriver);
        wait = new WebDriverWait(webDriver, 10);
    }
}
