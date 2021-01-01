package com.alasdoo.developercourseassignment.controllers;

import com.alasdoo.developercourseassignment.dtos.TeacherDeveloperCourseDTO;
import com.alasdoo.developercourseassignment.services.contracts.TeacherDeveloperCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacherdevelopercourse")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherDeveloperCourseController {
    
    private final TeacherDeveloperCourseService teacherDeveloperCourseService;

    public TeacherDeveloperCourseController(TeacherDeveloperCourseService teacherDeveloperCourseService) {
        this.teacherDeveloperCourseService = teacherDeveloperCourseService;
    }

    @GetMapping(value = "/getTeacherCourse/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDeveloperCourseDTO selectTeacherDeveloperCourse(@PathVariable("id") Integer id) {
        return teacherDeveloperCourseService.findOne(id);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeacherDeveloperCourseDTO> getAllTeacherDeveloperCourses() {
        return teacherDeveloperCourseService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addTeacherCourse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDeveloperCourseDTO saveTeacherDeveloperCourse(@RequestBody TeacherDeveloperCourseDTO teacherDeveloperCourseDTO) {
        return teacherDeveloperCourseService.save(teacherDeveloperCourseDTO);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDeveloperCourseDTO updateTeacherDeveloperCourse(@PathVariable("id") Integer id, @RequestBody TeacherDeveloperCourseDTO teacherDeveloperCourseDTO) {
        return teacherDeveloperCourseService.update(id, teacherDeveloperCourseDTO);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTeacherDeveloperCourse(@PathVariable("id") Integer id) {
        teacherDeveloperCourseService.remove(id);
    }

    @GetMapping(value = "/get/teacher/{teacherId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDeveloperCourseDTO findByTeacherId(@PathVariable("teacherId") Integer teacherId) {
        return teacherDeveloperCourseService.findByTeacherId(teacherId);
    }

}
