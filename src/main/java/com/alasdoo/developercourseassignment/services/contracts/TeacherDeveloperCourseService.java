package com.alasdoo.developercourseassignment.services.contracts;

import com.alasdoo.developercourseassignment.dtos.TeacherDeveloperCourseDTO;

import java.util.List;

public interface TeacherDeveloperCourseService extends CrudService<TeacherDeveloperCourseDTO> {

    List<TeacherDeveloperCourseDTO> findAllByTeacherId(Integer teacherId);
}
