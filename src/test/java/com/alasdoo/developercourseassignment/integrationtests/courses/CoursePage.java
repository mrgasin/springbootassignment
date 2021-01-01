package com.alasdoo.developercourseassignment.integrationtests.courses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoursePage {

    @FindBy(css = "button[data-test-id = 'courses']")
    private WebElement courses;

    @FindBy(css = "button[data-test-id = 'add-courses']")
    private WebElement addCourse;
}
