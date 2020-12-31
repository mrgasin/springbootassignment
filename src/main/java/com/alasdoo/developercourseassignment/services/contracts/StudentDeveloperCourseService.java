package com.alasdoo.developercourseassignment.services.contracts;

import com.alasdoo.developercourseassignment.dtos.StudentDeveloperCourseDTO;

import java.util.List;

public interface StudentDeveloperCourseService extends CrudService<StudentDeveloperCourseDTO> {

    StudentDeveloperCourseDTO findByStudentId(Integer studentId);

    List<StudentDeveloperCourseDTO> findByDeveloperCourseId(Integer developerCourseId);
}
