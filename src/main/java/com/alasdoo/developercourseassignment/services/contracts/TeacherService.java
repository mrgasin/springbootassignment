package com.alasdoo.developercourseassignment.services.contracts;

import com.alasdoo.developercourseassignment.dtos.TeacherDTO;

public interface TeacherService extends CrudService<TeacherDTO> {

    TeacherDTO findByTeacherNameAndTeacherSurname(String name, String surname);

    TeacherDTO findByTeacherEmail(String email);
}
