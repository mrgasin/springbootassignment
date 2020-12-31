package com.alasdoo.developercourseassignment.controllers;

import com.alasdoo.developercourseassignment.dtos.TeacherDTO;
import com.alasdoo.developercourseassignment.services.contracts.TeacherService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/getTeacher/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDTO selectTeacher(@PathVariable("id") Integer id) {
        return teacherService.findOne(id);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeacherDTO> getAllTeachers() {
        return null;
    }

    @PostMapping(value = "/addTeacher", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDTO saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.save(teacherDTO);
    }

    @GetMapping(value = "/get/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDTO findByEmail(@PathVariable("email") String email) {
        return teacherService.findByTeacherEmail(email);
    }

    @GetMapping(value = "/get/{name}/{surname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDTO findByNameAndSurname(@PathVariable("name") String name, @PathVariable("surname") String surname) {
        return teacherService.findByTeacherNameAndTeacherSurname(name, surname);
    }

}
