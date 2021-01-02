package com.alasdoo.developercourseassignment.integrationtests.teachercourses;

import com.alasdoo.developercourseassignment.integrationtests.FunctionalTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class TeacherCoursesTest extends FunctionalTest {

    private TeacherCoursesPage teacherCoursesPage;

    @BeforeEach
    public  void init(){
        teacherCoursesPage = PageFactory.initElements(webDriver, TeacherCoursesPage.class);
    }

    @Test
    public void  assignCourse(){
        log.info("Course assigning started");

        log.info("Course assigned");
    }
}
