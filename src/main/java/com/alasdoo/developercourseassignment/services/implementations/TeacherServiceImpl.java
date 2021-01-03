package com.alasdoo.developercourseassignment.services.implementations;

import com.alasdoo.developercourseassignment.dtos.TeacherDTO;
import com.alasdoo.developercourseassignment.entities.Teacher;
import com.alasdoo.developercourseassignment.mappers.TeacherMapper;
import com.alasdoo.developercourseassignment.repositories.TeacherDeveloperCourseRepository;
import com.alasdoo.developercourseassignment.repositories.TeacherRepository;
import com.alasdoo.developercourseassignment.services.contracts.TeacherService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherDeveloperCourseRepository teacherDeveloperCourseRepository;
    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherDeveloperCourseRepository teacherDeveloperCourseRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherDeveloperCourseRepository = teacherDeveloperCourseRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherDTO findOne(Integer id) {
        Teacher teacher = getTeacherById(id);
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
    @Transactional
    public void remove(Integer id) {
        getTeacherById(id);
        teacherRepository.deleteById(id);
        teacherDeveloperCourseRepository.deleteAllByTeacherId(id);
    }

    @Override
    public TeacherDTO update(Integer id, TeacherDTO teacherDTO) {
        Teacher oldTeacher = getTeacherById(id);
        Teacher teacher = teacherMapper.transformToEntity(teacherDTO);
        teacher.setId(oldTeacher.getId());
        return teacherMapper.transformToDTO(teacherRepository.save(teacher));
    }

    @Override
    public TeacherDTO findByTeacherNameAndTeacherSurname(String name, String surname) {
        Teacher teacher = getTeacherByNameAndSurname(name, surname);
        return teacherMapper.transformToDTO(teacher);
    }

    @Override
    public TeacherDTO findByTeacherEmail(String email) {
        Teacher teacher = getTeacherByEmail(email);
        return teacherMapper.transformToDTO(teacher);
    }

    private Teacher getTeacherById(Integer id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with the following id = " + id + " is not found."));
    }

    private Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByTeacherEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with the following email = " + email + " is not found."));
    }

    private Teacher getTeacherByNameAndSurname(String name, String surname) {
        return teacherRepository.findByTeacherNameAndTeacherSurname(name, surname)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with the following name = " + name + " and surname= " + surname + " combination is not found."));
    }
}
