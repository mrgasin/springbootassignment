package com.alasdoo.developercourseassignment.services.impl;

import com.alasdoo.developercourseassignment.dtos.TeacherDTO;
import com.alasdoo.developercourseassignment.entities.Teacher;
import com.alasdoo.developercourseassignment.mappers.TeacherMapper;
import com.alasdoo.developercourseassignment.repositories.TeacherRepository;
import com.alasdoo.developercourseassignment.services.contracts.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherDTO findOne(Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with following id= " + id + " is not found."));
        return teacherMapper.transformToDTO(teacher);
    }

    @Override
    public List<TeacherDTO> findAll() {
        return teacherRepository.findAll().stream().map(teacherMapper::transformToDTO).collect(Collectors.toList());
    }

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.transformToEntity(teacherDTO);
        return teacherMapper.transformToDTO(teacherRepository.save(teacher));
    }

    @Override
    public void remove(Integer id) {
        teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with following id = " + id + " is not found."));
        teacherRepository.deleteById(id);
    }

    @Override
    public TeacherDTO update(Integer id, TeacherDTO teacherDTO) {
        Teacher oldTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with the following id = " + id + " is not found."));

        oldTeacher.setTeacherName(teacherDTO.getTeacherName());
        oldTeacher.setTeacherEmail(teacherDTO.getTeacherEmail());
        oldTeacher.setTeacherSurname(teacherDTO.getTeacherSurname());
        Teacher updatedTeacher = teacherRepository.save(oldTeacher);
        return teacherMapper.transformToDTO(updatedTeacher);
    }

    @Override
    public TeacherDTO findByTeacherNameAndTeacherSurname(String name, String surname) {
        Teacher teacher = teacherRepository.findByTeacherNameAndTeacherSurname(name, surname)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with the following name = " + name + " and surname= " + surname + " combination is not found."));
        return teacherMapper.transformToDTO(teacher);
    }

    @Override
    public TeacherDTO findByTeacherEmail(String email) {
        Teacher teacher = teacherRepository.findByTeacherEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with the following email = " + email + " is not found."));
        return teacherMapper.transformToDTO(teacher);
    }
}
