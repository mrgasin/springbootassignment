package com.alasdoo.developercourseassignment.integrationtests.courses;

import com.alasdoo.developercourseassignment.integrationtests.FunctionalTest;
import com.alasdoo.developercourseassignment.integrationtests.studentcourses.StudentCoursesPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.PageFactory;

import static com.alasdoo.developercourseassignment.integrationtests.courses.CoursePage.baseUrl;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(FunctionalTest.class)
public class CourseTest extends FunctionalTest {
    CoursePage coursePage;

    @BeforeAll
    public static void start() {
        log.info("endpoint: " + baseUrl);
    }

    @BeforeEach
    public void init() {
        coursePage = PageFactory.initElements(webDriver, CoursePage.class);
        webDriver.get(baseUrl);
    }

    @Test
    public void addCourse() {
        log.info("Creation of new course started");
        int oldSize = coursePage.numberOfElements();
        coursePage.addCourse();
        int newSize = coursePage.numberOfElements();
        assertEquals(oldSize + 1, newSize);
        log.info("Course created");
    }

    @Test
    public void updateCourse() {
        log.info("Updating of the course started");
        int index = 1;
        int oldPrice = coursePage.oldPrice(index);
        coursePage.updateCourse(index);
        int newPrice = coursePage.price();
        assertEquals(oldPrice + 10, newPrice);
        log.info("Course updated");
    }

    @Test
    public void deleteCourse() {
        int oldSize = coursePage.numberOfElements();
        coursePage.deleteCourse(1);
        int newSize = coursePage.numberOfElements();
        assertEquals(oldSize - 1, newSize);
        log.info("Course deleted.");
    }
}
