package com.alasdoo.developercourseassignment.integrationtests.students;

import com.alasdoo.developercourseassignment.integrationtests.FunctionalTest;
import com.alasdoo.developercourseassignment.integrationtests.studentcourses.StudentCoursesPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.PageFactory;

import static com.alasdoo.developercourseassignment.integrationtests.students.StudentPage.baseUrl;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(FunctionalTest.class)
public class StudentTest extends FunctionalTest {
    private StudentPage studentPage;

    @BeforeAll
    public static void start() {
        log.info("endpoint: " + baseUrl);
    }

    @BeforeEach
    void init() {
        studentPage = PageFactory.initElements(webDriver, StudentPage.class);
        webDriver.get(baseUrl);
    }

    @Test
    public void header() {
        assertEquals(studentPage.getHeaderText(), "Student");
        log.info("STUDENT HEADER " + studentPage.getHeaderText());
    }

    @Test
    public void addStudent() {
        log.info("Creation of new student started");
        int sizeBefore = studentPage.numberOfElements();
        studentPage.addStudent();
        int sizeAfter = studentPage.numberOfElements();
        assertEquals(sizeAfter, sizeBefore + 1);
        log.info("Student created");
    }

    @Test
    public void deleteStudent() {
        int oldSize = studentPage.numberOfElements();
        studentPage.deleteStudent(1);
        int newSize = studentPage.numberOfElements();
        assertEquals(oldSize - 1, newSize);
        log.info("Student deleted");
    }

    @Test
    public void updateStudent() {
        int index = 1;
        log.info("Updating of the student started");
        int oldNameLength = studentPage.oldNameLength(index);
        studentPage.updateStudent(index);
        int nameLength = studentPage.updatedNameLength();
        assertEquals(oldNameLength + 3, nameLength);
        log.info("Student updated");
    }

}