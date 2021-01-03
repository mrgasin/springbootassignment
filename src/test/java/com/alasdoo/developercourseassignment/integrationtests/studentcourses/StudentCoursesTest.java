package com.alasdoo.developercourseassignment.integrationtests.studentcourses;

import com.alasdoo.developercourseassignment.integrationtests.FunctionalTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.PageFactory;

import static com.alasdoo.developercourseassignment.integrationtests.studentcourses.StudentCoursesPage.baseUrl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ExtendWith(FunctionalTest.class)
public class StudentCoursesTest extends FunctionalTest {

    private StudentCoursesPage studentCoursesPage;

    @BeforeAll
    public static void start() {
        log.info("endpoint: " + baseUrl);
    }

    @BeforeEach
    public void init() {
        studentCoursesPage = PageFactory.initElements(webDriver, StudentCoursesPage.class);
        webDriver.get(baseUrl);
    }

    @Test
    public void dummyTest() {
        boolean t = false;
        assertTrue(t);
    }

    @Test
    public void buyCourse() {
        log.info("Student buying course started");
        int index = 1;
        int oldCount = studentCoursesPage.numberOfCourses(index);
        studentCoursesPage.buyCourse(1);
        int newCount = studentCoursesPage.numberOfCourses(index);
        assertEquals(oldCount + 1, newCount);
        log.info("Student buying course finished");
    }

    @Test
    public void cancelCourse() {
        log.info("Student canceling course started");
        int index = 1;
        int oldCount = studentCoursesPage.numberOfCourses(index);
        studentCoursesPage.cancelCourse(index);
        int newCount = studentCoursesPage.numberOfCourses(index);
        assertEquals(oldCount - 1, newCount);
        log.info("Student canceling course finished");
    }

    @Test
    public void updateCourse() {
        log.info("Student updating course started");
        int index = 1;
        int oldCount = studentCoursesPage.numberOfCoursesBought(index);
        studentCoursesPage.updateStudentCourse(index);
        int newCount = studentCoursesPage.numberOfCoursesBought(index);
        assertEquals(oldCount + 5, newCount);
        log.info("Student updating course finished");
    }
}
