package com.alasdoo.developercourseassignment.services.contracts;

import com.alasdoo.developercourseassignment.dtos.StudentDeveloperCourseDTO;

import java.util.List;

public interface StudentDeveloperCourseService extends CrudService<StudentDeveloperCourseDTO> {

    List<StudentDeveloperCourseDTO> findAllByStudentId(Integer studentId);

    List<StudentDeveloperCourseDTO> findAllByDeveloperCourseId(Integer developerCourseId);
}
