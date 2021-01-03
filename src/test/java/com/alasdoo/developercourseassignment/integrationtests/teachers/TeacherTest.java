package com.alasdoo.developercourseassignment.integrationtests.teachers;

import com.alasdoo.developercourseassignment.integrationtests.FunctionalTest;
import com.alasdoo.developercourseassignment.integrationtests.teachercourses.TeacherCoursesPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.PageFactory;

import static com.alasdoo.developercourseassignment.integrationtests.teachers.TeacherPage.baseUrl;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(FunctionalTest.class)
public class TeacherTest extends FunctionalTest {

    private TeacherPage teacherPage;

    @BeforeAll
    public static void start() {
        log.info("endpoint: " + baseUrl);
    }

    @BeforeEach
    public void init() {
        teacherPage = PageFactory.initElements(webDriver, TeacherPage.class);
        webDriver.get(baseUrl);
    }

    @Test
    public void addTeacher() {
        log.info("Creation of new teacher started");
        int sizeBefore = teacherPage.numberOfElements();
        teacherPage.addTeacher();
        int sizeAfter = teacherPage.numberOfElements();
        assertEquals(sizeAfter, sizeBefore + 1);
        log.info("teacher created");
    }

    @Test
    public void deleteTeacher() {
        int oldSize = teacherPage.numberOfElements();
        teacherPage.deleteTeacher(1);
        int newSize = teacherPage.numberOfElements();
        assertEquals(oldSize - 1, newSize);
    }

    @Test
    public void updateTeacher() {
        int index = 1;
        log.info("Updating of the teacher started");
        int oldNameLength = teacherPage.oldNameLength(index);
        teacherPage.updateTeacher(index);
        int nameLength = teacherPage.updatedNameLength();
        assertEquals(oldNameLength + 3, nameLength);
        log.info("teacher updated");
    }
}
