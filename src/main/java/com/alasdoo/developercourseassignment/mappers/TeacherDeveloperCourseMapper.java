package com.alasdoo.developercourseassignment.mappers;

import com.alasdoo.developercourseassignment.dtos.TeacherDeveloperCourseDTO;
import com.alasdoo.developercourseassignment.entities.TeacherDeveloperCourse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TeacherDeveloperCourseMapper {

    public TeacherDeveloperCourseDTO transformToDTO(TeacherDeveloperCourse teacherDeveloperCourseSrc) {
        TeacherDeveloperCourseDTO teacherDeveloperCourseDTO = new TeacherDeveloperCourseDTO();
        BeanUtils.copyProperties(teacherDeveloperCourseSrc, teacherDeveloperCourseDTO);
        return teacherDeveloperCourseDTO;
    }

    public TeacherDeveloperCourse transformToEntity(TeacherDeveloperCourseDTO teacherDeveloperCourseDTOSrc) {
        TeacherDeveloperCourse teacherDeveloperCourse = new TeacherDeveloperCourse();
        BeanUtils.copyProperties(teacherDeveloperCourseDTOSrc, teacherDeveloperCourse);
        return teacherDeveloperCourse;
    }
}
