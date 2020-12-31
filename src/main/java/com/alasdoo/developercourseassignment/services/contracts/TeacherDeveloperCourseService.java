package com.alasdoo.developercourseassignment.services.contracts;

import com.alasdoo.developercourseassignment.dtos.TeacherDeveloperCourseDTO;

public interface TeacherDeveloperCourseService extends CrudService<TeacherDeveloperCourseDTO> {

    TeacherDeveloperCourseDTO findByTeacherId(Integer teacherId);
}
