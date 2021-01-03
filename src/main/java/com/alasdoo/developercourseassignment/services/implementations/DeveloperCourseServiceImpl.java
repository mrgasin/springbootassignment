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
        Optional<DeveloperCourse> developerCourse = developerCourseRepository.findById(id);
        if (!developerCourse.isPresent()) {
            throw new IllegalArgumentException
                    ("Course with the following id = " + id + " is not found.");
        }
        return developerCourseMapper.transformToDTO(developerCourse.get());
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
        Optional<DeveloperCourse> developerCourse = developerCourseRepository.findById(id);
        if (!developerCourse.isPresent()) {
            throw new IllegalArgumentException
                    ("Course with the following id = " + id + " is not found.");
        }
        List<StudentDeveloperCourse> studentCourses = studentDeveloperCourseRepository.findByDeveloperCourseId(id).orElseThrow(() -> new IllegalArgumentException(" Not found"));
        List<TeacherDeveloperCourse> teacherCourses = teacherDeveloperCourseRepository.findByDeveloperCourseId(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
        boolean isAssignedToStudents = studentCourses.stream().anyMatch(course -> course.getDeveloperCourseId().equals(id));
        boolean isAssignedToTeachers = teacherCourses.stream().anyMatch(course -> course.getDeveloperCourseId().equals(id));

        if (isAssignedToTeachers){
            teacherDeveloperCourseRepository.deleteAllByDeveloperCourseId(id);
        }

        if (isAssignedToStudents){
            studentDeveloperCourseRepository.deleteAllByDeveloperCourseId(id);
        }
        developerCourseRepository.deleteById(id);
    }

    @Override
    public DeveloperCourseDTO update(Integer id, DeveloperCourseDTO developerCourseDTO) {
        Optional<DeveloperCourse> oldDeveloperCourse = developerCourseRepository.findById(id);
        if (!oldDeveloperCourse.isPresent()) {
            throw new IllegalArgumentException
                    ("Course with the following id = " + id + " is not found.");
        }
        oldDeveloperCourse.get().setDeveloperCourseName(developerCourseDTO.getDeveloperCourseName());
        oldDeveloperCourse.get().setClassesPerWeek(developerCourseDTO.getClassesPerWeek());
        oldDeveloperCourse.get().setCostPerClass(developerCourseDTO.getCostPerClass());
        developerCourseRepository.save(oldDeveloperCourse.get());
        return developerCourseMapper.transformToDTO(oldDeveloperCourse.get());
    }

    @Override
    public List<DeveloperCourseDTO> findByDeveloperCourseName(String developerCourseName) {
        Optional<List<DeveloperCourse>> developerCourses = developerCourseRepository.findByDeveloperCourseName(developerCourseName);
        if (!developerCourses.isPresent()) {
            throw new IllegalArgumentException
                    ("Course with the following name = " + developerCourseName + " is not found.");
        }
        return developerCourseMapper.transformToListOfDTO(developerCourses.get());
    }

    @Override
    public List<DeveloperCourseDTO> findByDeveloperCourseByStudentId(Integer studentId) {
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
    public List<DeveloperCourseDTO> findByDeveloperCourseByTeacherId(Integer teacherId) {
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
}
