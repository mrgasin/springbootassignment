package com.alasdoo.developercourseassignment.services.impl;

import com.alasdoo.developercourseassignment.dtos.TeacherDTO;
import com.alasdoo.developercourseassignment.services.contracts.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    //TODO implementation missing
    @Override
    public TeacherDTO findOne(Integer id) {
        return null;
    }

    @Override
    public List<TeacherDTO> findAll() {
        return null;
    }

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public TeacherDTO update(Integer id, TeacherDTO teacherDTO) {
        return null;
    }

    @Override
    public TeacherDTO findByTeacherNameAndTeacherSurname(String name, String surname) {
        return null;
    }

    @Override
    public TeacherDTO findByTeacherEmail(String email) {
        return null;
    }
}
