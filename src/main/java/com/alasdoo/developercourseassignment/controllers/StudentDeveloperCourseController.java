package com.alasdoo.developercourseassignment.controllers;

import com.alasdoo.developercourseassignment.dtos.StudentDeveloperCourseDTO;
import com.alasdoo.developercourseassignment.services.contracts.StudentDeveloperCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentdevelopercourse")
@CrossOrigin
public class StudentDeveloperCourseController {

    private final StudentDeveloperCourseService studentDeveloperCourseService;

    public StudentDeveloperCourseController(StudentDeveloperCourseService studentDeveloperCourseService) {
        this.studentDeveloperCourseService = studentDeveloperCourseService;
    }

    @GetMapping(value = "/getStudentCourse/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDeveloperCourseDTO selectStudentDeveloperCourse(@PathVariable("id") Integer id) {
        return studentDeveloperCourseService.findOne(id);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDeveloperCourseDTO> getAllStudentDeveloperCourses() {
        return studentDeveloperCourseService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addStudentDeveloperCourse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDeveloperCourseDTO saveStudentDeveloperCourse(@RequestBody StudentDeveloperCourseDTO studentDeveloperCourseDTO) {
        return studentDeveloperCourseService.save(studentDeveloperCourseDTO);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDeveloperCourseDTO updateStudentDeveloperCourse(@PathVariable("id") Integer id, @RequestBody StudentDeveloperCourseDTO studentDeveloperCourseDTO) {
        return studentDeveloperCourseService.update(id, studentDeveloperCourseDTO);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStudentDeveloperCourse(@PathVariable("id") Integer id) {
        studentDeveloperCourseService.remove(id);
    }

    @GetMapping(value = "/get/student/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDeveloperCourseDTO> findAllByStudentId(@PathVariable("studentId") Integer studentId) {
        return studentDeveloperCourseService.findAllByStudentId(studentId);
    }

    @GetMapping(value = "/get/course/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDeveloperCourseDTO> findAllByCourseId(@PathVariable("courseId") Integer courseId) {
        return studentDeveloperCourseService.findAllByDeveloperCourseId(courseId);
    }
}
