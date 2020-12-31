package com.alasdoo.developercourseassignment.services.impl;

import com.alasdoo.developercourseassignment.dtos.TeacherDeveloperCourseDTO;
import com.alasdoo.developercourseassignment.entities.DeveloperCourse;
import com.alasdoo.developercourseassignment.entities.Teacher;
import com.alasdoo.developercourseassignment.entities.TeacherDeveloperCourse;
import com.alasdoo.developercourseassignment.mappers.TeacherDeveloperCourseMapper;
import com.alasdoo.developercourseassignment.repositories.DeveloperCourseRepository;
import com.alasdoo.developercourseassignment.repositories.TeacherDeveloperCourseRepository;
import com.alasdoo.developercourseassignment.repositories.TeacherRepository;
import com.alasdoo.developercourseassignment.services.contracts.TeacherDeveloperCourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherDeveloperCourseServiceImpl implements TeacherDeveloperCourseService {

    private final TeacherDeveloperCourseRepository teacherDeveloperCourseRepository;
    private final TeacherRepository teacherRepository;
    private final DeveloperCourseRepository developerCourseRepository;
    private final TeacherDeveloperCourseMapper teacherDeveloperCourseMapper;

    public TeacherDeveloperCourseServiceImpl(TeacherDeveloperCourseRepository teacherDeveloperCourseRepository, TeacherRepository teacherRepository, DeveloperCourseRepository developerCourseRepository, TeacherDeveloperCourseMapper teacherDeveloperCourseMapper) {
        this.teacherDeveloperCourseRepository = teacherDeveloperCourseRepository;
        this.teacherRepository = teacherRepository;
        this.developerCourseRepository = developerCourseRepository;
        this.teacherDeveloperCourseMapper = teacherDeveloperCourseMapper;
    }

    @Override
    public TeacherDeveloperCourseDTO findOne(Integer id) {
        Optional<TeacherDeveloperCourse> teacherDeveloperCourse = teacherDeveloperCourseRepository.findById(id);
        if (!teacherDeveloperCourse.isPresent()) {
            throw new IllegalArgumentException
                    ("Teacher Developer Course with the following id = " + id + " is not found.");
        }
        return teacherDeveloperCourseMapper.transformToDTO(teacherDeveloperCourse.get());
    }

    @Override
    public List<TeacherDeveloperCourseDTO> findAll() {
        return teacherDeveloperCourseRepository.findAll().stream().map(teacherDeveloperCourseMapper::transformToDTO).collect(Collectors.toList());
    }

    @Override
    public TeacherDeveloperCourseDTO save(TeacherDeveloperCourseDTO teacherDeveloperCourseDTO) {
        TeacherDeveloperCourse teacherDeveloperCourse = teacherDeveloperCourseMapper.transformToEntity(teacherDeveloperCourseDTO);
        Integer teacherId = teacherDeveloperCourseDTO.getTeacherId();
        Integer courseId = teacherDeveloperCourseDTO.getDeveloperCourseId();
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        Optional<DeveloperCourse> developerCourse = developerCourseRepository.findById(courseId);
        if (!teacher.isPresent()) {
            throw new IllegalArgumentException
                    ("Teacher with the following id = " + teacherId + " is not found.");
        }
        if (!developerCourse.isPresent()) {
            throw new IllegalArgumentException
                    ("Course with the following id = " + courseId + " is not found.");
        }
        if (teacherDeveloperCourseRepository.findByDeveloperCourseIdAndTeacherId(courseId, teacherId).isPresent()) {
            throw new IllegalArgumentException
                    ("Teacher course combination is already present.");
        }
        return teacherDeveloperCourseMapper.transformToDTO(teacherDeveloperCourseRepository.save(teacherDeveloperCourse));
    }

    @Override
    public void remove(Integer id) throws IllegalArgumentException {
        Optional<TeacherDeveloperCourse> teacherDeveloperCourse = teacherDeveloperCourseRepository.findById(id);
        if (!teacherDeveloperCourse.isPresent()) {
            throw new IllegalArgumentException
                    ("Teacher Developer Course with the following id = " + id + " is not found.");
        }
        teacherDeveloperCourseRepository.deleteById(id);
    }

    @Override
    public TeacherDeveloperCourseDTO update(Integer id, TeacherDeveloperCourseDTO teacherDeveloperCourseDTO) {
        Optional<TeacherDeveloperCourse> oldTeacherDeveloperCourse = teacherDeveloperCourseRepository.findById(id);
        if (!oldTeacherDeveloperCourse.isPresent()) {
            throw new IllegalArgumentException
                    ("Teacher course with the following id = " + id + " is not found.");
        }
        oldTeacherDeveloperCourse.get().setDeveloperCourseId(teacherDeveloperCourseDTO.getDeveloperCourseId());
        oldTeacherDeveloperCourse.get().setTeacherId(teacherDeveloperCourseDTO.getTeacherId());
        teacherDeveloperCourseRepository.save(oldTeacherDeveloperCourse.get());
        return teacherDeveloperCourseMapper.transformToDTO(oldTeacherDeveloperCourse.get());
    }

    @Override
    public TeacherDeveloperCourseDTO findByTeacherId(Integer teacherId) {
        Optional<TeacherDeveloperCourse> teacherDeveloperCourse = teacherDeveloperCourseRepository.findByTeacherId(teacherId);
        if (!teacherDeveloperCourse.isPresent()) {
            throw new IllegalArgumentException
                    ("Teacher with the following id = " + teacherId + " is not found.");
        }
        return teacherDeveloperCourseMapper.transformToDTO(teacherDeveloperCourse.get());
    }
}
