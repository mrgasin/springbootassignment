package com.alasdoo.developercourseassignment.services.contracts;

import com.alasdoo.developercourseassignment.dtos.TeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {
    TeacherDTO findOne(Integer id);
    List<TeacherDTO> findAll();
    TeacherDTO save(TeacherDTO teacherDTO);
    void remove(Integer id);
    TeacherDTO update(Integer id, TeacherDTO teacherDTO);
    TeacherDTO findByTeacherNameAndTeacherSurname(String name, String surname);
    TeacherDTO findByTeacherEmail(String email);
}
