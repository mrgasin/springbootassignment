package com.alasdoo.developercourseassignment.services.implementations;

import com.alasdoo.developercourseassignment.dtos.StudentDeveloperCourseDTO;
import com.alasdoo.developercourseassignment.entities.DeveloperCourse;
import com.alasdoo.developercourseassignment.entities.Student;
import com.alasdoo.developercourseassignment.entities.StudentDeveloperCourse;
import com.alasdoo.developercourseassignment.mappers.StudentDeveloperCourseMapper;
import com.alasdoo.developercourseassignment.repositories.DeveloperCourseRepository;
import com.alasdoo.developercourseassignment.repositories.StudentDeveloperCourseRepository;
import com.alasdoo.developercourseassignment.repositories.StudentRepository;
import com.alasdoo.developercourseassignment.services.contracts.StudentDeveloperCourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentDeveloperCourseServiceImpl implements StudentDeveloperCourseService {

    private final StudentDeveloperCourseRepository studentDeveloperCourseRepository;
    private final StudentRepository studentRepository;
    private final DeveloperCourseRepository developerCourseRepository;
    private final StudentDeveloperCourseMapper studentDeveloperCourseMapper;

    public StudentDeveloperCourseServiceImpl(StudentDeveloperCourseRepository studentDeveloperCourseRepository, StudentRepository studentRepository, DeveloperCourseRepository developerCourseRepository, StudentDeveloperCourseMapper studentDeveloperCourseMapper) {
        this.studentDeveloperCourseRepository = studentDeveloperCourseRepository;
        this.studentRepository = studentRepository;
        this.developerCourseRepository = developerCourseRepository;
        this.studentDeveloperCourseMapper = studentDeveloperCourseMapper;
    }

    @Override
    public StudentDeveloperCourseDTO findOne(Integer id) {
        StudentDeveloperCourse studentDeveloperCourse = getStudentDeveloperCourseById(id);
        return studentDeveloperCourseMapper.transformToDTO(studentDeveloperCourse);
    }

    @Override
    public List<StudentDeveloperCourseDTO> findAll() {
        return studentDeveloperCourseRepository.findAll().stream().map(studentDeveloperCourseMapper::transformToDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDeveloperCourseDTO save(StudentDeveloperCourseDTO studentDeveloperCourseDTO) {
        StudentDeveloperCourse studentDeveloperCourse = studentDeveloperCourseMapper.transformToEntity(studentDeveloperCourseDTO);
        Integer studentId = studentDeveloperCourseDTO.getStudentId();
        Integer courseId = studentDeveloperCourseDTO.getDeveloperCourseId();
        checkIsAvailable(studentId, courseId);
        return studentDeveloperCourseMapper.transformToDTO(studentDeveloperCourseRepository.save(studentDeveloperCourse));
    }

    @Override
    public void remove(Integer id) {
        getStudentDeveloperCourseById(id);
        studentDeveloperCourseRepository.deleteById(id);
    }

    @Override
    public StudentDeveloperCourseDTO update(Integer id, StudentDeveloperCourseDTO studentDeveloperCourseDTO) {
        StudentDeveloperCourse oldStudentDeveloperCourse = getStudentDeveloperCourseById(id);
        StudentDeveloperCourse course = studentDeveloperCourseMapper.transformToEntity(studentDeveloperCourseDTO);
        course.setId(oldStudentDeveloperCourse.getId());
        return studentDeveloperCourseMapper.transformToDTO(studentDeveloperCourseRepository.save(course));
    }

    @Override
    public List<StudentDeveloperCourseDTO> findAllByStudentId(Integer studentId) {
        Optional<List<StudentDeveloperCourse>> studentDeveloperCourses = studentDeveloperCourseRepository.findByStudentId(studentId);
        if (!studentDeveloperCourses.isPresent()) {
            throw new IllegalArgumentException
                    ("Student with the following id = " + studentId + " is not found.");
        }
        return studentDeveloperCourseMapper.transformToListOfDTO(studentDeveloperCourses.get());
    }

    @Override
    public List<StudentDeveloperCourseDTO> findAllByDeveloperCourseId(Integer developerCourseId) {
        Optional<List<StudentDeveloperCourse>> studentDeveloperCourse = studentDeveloperCourseRepository.findByDeveloperCourseId(developerCourseId);
        if (!studentDeveloperCourse.isPresent()) {
            throw new IllegalArgumentException
                    ("Course with the following id = " + developerCourseId + " is not found.");
        }
        return studentDeveloperCourseMapper.transformToListOfDTO(studentDeveloperCourse.get());
    }

    private StudentDeveloperCourse getStudentDeveloperCourseById(Integer id) {
        return studentDeveloperCourseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("Student course with the following id = " + id + " is not found."));
    }

    private void checkIsAvailable(Integer studentId, Integer courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<DeveloperCourse> developerCourse = developerCourseRepository.findById(courseId);
        if (!student.isPresent()) {
            throw new IllegalArgumentException
                    ("Student with the following id = " + studentId + " is not found.");
        }
        if (!developerCourse.isPresent()) {
            throw new IllegalArgumentException
                    ("Course with the following id = " + courseId + " is not found.");
        }
        if (studentDeveloperCourseRepository.findByDeveloperCourseIdAndStudentId(courseId, studentId).isPresent()) {
            throw new IllegalArgumentException
                    ("Student course combination is already present.");
        }
    }
}
