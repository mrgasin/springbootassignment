package com.alasdoo.developercourseassignment.services.implementations;

import com.alasdoo.developercourseassignment.dtos.DeveloperCourseDTO;
import com.alasdoo.developercourseassignment.entities.DeveloperCourse;
import com.alasdoo.developercourseassignment.entities.StudentDeveloperCourse;
import com.alasdoo.developercourseassignment.entities.TeacherDeveloperCourse;
import com.alasdoo.developercourseassignment.mappers.DeveloperCourseMapper;
import com.alasdoo.developercourseassignment.repositories.*;
import com.alasdoo.developercourseassignment.services.contracts.DeveloperCourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeveloperCourseServiceImpl implements DeveloperCourseService {

    private final DeveloperCourseRepository developerCourseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final DeveloperCourseMapper developerCourseMapper;
    private final StudentDeveloperCourseRepository studentDeveloperCourseRepository;
    private final TeacherDeveloperCourseRepository teacherDeveloperCourseRepository;

    public DeveloperCourseServiceImpl(DeveloperCourseRepository developerCourseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, DeveloperCourseMapper developerCourseMapper, StudentDeveloperCourseRepository studentDeveloperCourseRepository, TeacherDeveloperCourseRepository teacherDeveloperCourseRepository) {
        this.developerCourseRepository = developerCourseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.developerCourseMapper = developerCourseMapper;
        this.studentDeveloperCourseRepository = studentDeveloperCourseRepository;
        this.teacherDeveloperCourseRepository = teacherDeveloperCourseRepository;
    }


    @Override
    public DeveloperCourseDTO findOne(Integer id) {
        DeveloperCourse developerCourse = getById(id);
        return developerCourseMapper.transformToDTO(developerCourse);
    }

    @Override
    public List<DeveloperCourseDTO> findAll() {
        return developerCourseRepository.findAll().stream().map(developerCourseMapper::transformToDTO).collect(Collectors.toList());
    }

    @Override
    public DeveloperCourseDTO save(DeveloperCourseDTO developerCourseDTO) {
        DeveloperCourse developerCourse = developerCourseMapper.transformToEntity(developerCourseDTO);
        return developerCourseMapper.transformToDTO(developerCourseRepository.save(developerCourse));
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        getById(id);
        developerCourseRepository.deleteById(id);
        studentDeveloperCourseRepository.deleteAllByDeveloperCourseId(id);
        teacherDeveloperCourseRepository.deleteAllByDeveloperCourseId(id);
    }

    @Override
    public DeveloperCourseDTO update(Integer id, DeveloperCourseDTO developerCourseDTO) {
        DeveloperCourse oldDeveloperCourse = getById(id);
        DeveloperCourse course = developerCourseMapper.transformToEntity(developerCourseDTO);
        course.setId(oldDeveloperCourse.getId());
        developerCourseRepository.save(course);
        return developerCourseMapper.transformToDTO(course);
    }

    @Override
    public List<DeveloperCourseDTO> findByDeveloperCourseName(String developerCourseName) {
        List<DeveloperCourse> developerCourses = getAllByDeveloperCourseName(developerCourseName);
        return developerCourseMapper.transformToListOfDTO(developerCourses);
    }

    @Override
    public List<DeveloperCourseDTO> findDeveloperCoursesByStudentId(Integer studentId) {
        if (!studentRepository.findById(studentId).isPresent()) {
            throw new IllegalArgumentException
                    ("Student with the following id = " + studentId + " is not found.");
        }
        Optional<List<DeveloperCourse>> developerCourses = developerCourseRepository.findDevCourseByStudentId(studentId);
        if (!developerCourses.isPresent()) {
            throw new IllegalArgumentException
                    ("Courses are not present for student with the following id = " + studentId + " is not found.");
        }
        return developerCourseMapper.transformToListOfDTO(developerCourses.get());
    }

    @Override
    public List<DeveloperCourseDTO> findDeveloperCoursesByTeacherId(Integer teacherId) {
        if (!teacherRepository.findById(teacherId).isPresent()) {
            throw new IllegalArgumentException
                    ("Teacher with the following id = " + teacherId + ".");
        }
        Optional<List<DeveloperCourse>> developerCourses = developerCourseRepository.findDevCourseByTeacherId(teacherId);
        if (!developerCourses.isPresent()) {
            throw new IllegalArgumentException
                    ("Courses are not present for teacher with the following id = " + teacherId + ".");
        }
        return developerCourseMapper.transformToListOfDTO(developerCourses.get());
    }

    private DeveloperCourse getById(Integer id) {
        return developerCourseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("Courses are not present for teacher with the following id = " + id + "."));
    }

    private List<DeveloperCourse> getAllByDeveloperCourseName(String name) {
        return developerCourseRepository.findByDeveloperCourseName(name).orElseThrow(() -> new IllegalArgumentException
                ("Course with the following name = " + name + " is not found."));
    }
}
