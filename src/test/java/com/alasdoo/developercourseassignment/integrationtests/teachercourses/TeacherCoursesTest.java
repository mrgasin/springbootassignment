package com.alasdoo.developercourseassignment.integrationtests.teachercourses;

import com.alasdoo.developercourseassignment.integrationtests.FunctionalTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.PageFactory;

import static com.alasdoo.developercourseassignment.integrationtests.teachercourses.TeacherCoursesPage.baseUrl;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(FunctionalTest.class)
public class TeacherCoursesTest extends FunctionalTest {

    private TeacherCoursesPage teacherCoursesPage;

    @BeforeEach
    public void init() {
        teacherCoursesPage = PageFactory.initElements(webDriver, TeacherCoursesPage.class);
        webDriver.get(baseUrl);
    }

    @Test
    public void assignCourse() {
        log.info("Course assigning started");
        int index = 1;
        int oldCount = teacherCoursesPage.numberOfCourses(index);
        teacherCoursesPage.assignCourse(index);
        int newCount = teacherCoursesPage.numberOfCourses(index);
        assertEquals(oldCount + 1, newCount);
        log.info("Course assigned");
    }

    @Test
    public void removeCourse() {
        log.info("Course removing started");
        int index = 1;
        int oldCount = teacherCoursesPage.numberOfCourses(index);
        teacherCoursesPage.removeCourse(index);
        int newCount = teacherCoursesPage.numberOfCourses(index);
        assertEquals(oldCount - 1, newCount);
        log.info("Course removed");
    }
}
