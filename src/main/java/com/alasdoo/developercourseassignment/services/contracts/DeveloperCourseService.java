package com.alasdoo.developercourseassignment.services.contracts;

import com.alasdoo.developercourseassignment.dtos.DeveloperCourseDTO;

import java.util.List;

public interface DeveloperCourseService extends CrudService<DeveloperCourseDTO> {

    List<DeveloperCourseDTO> findByDeveloperCourseName(String developerCourseName);

    List<DeveloperCourseDTO> findByDeveloperCourseByStudentId(Integer studentId);

    List<DeveloperCourseDTO> findByDeveloperCourseByTeacherId(Integer teacherId);
}
