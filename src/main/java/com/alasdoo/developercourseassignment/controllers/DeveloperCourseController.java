package com.alasdoo.developercourseassignment.controllers;

import com.alasdoo.developercourseassignment.dtos.DeveloperCourseDTO;
import com.alasdoo.developercourseassignment.services.contracts.DeveloperCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developercourse")
@CrossOrigin
public class DeveloperCourseController {
    
    private final DeveloperCourseService developerCourseService;

    public DeveloperCourseController(DeveloperCourseService developerCourseService) {
        this.developerCourseService = developerCourseService;
    }

    @GetMapping(value = "/getCourse/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeveloperCourseDTO selectDeveloperCourse(@PathVariable("id") Integer id) {
        return developerCourseService.findOne(id);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeveloperCourseDTO> getAllDeveloperCourses() {
        return developerCourseService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addDeveloperCourse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeveloperCourseDTO saveDeveloperCourse(@RequestBody DeveloperCourseDTO developerCourseDTO) {
        return developerCourseService.save(developerCourseDTO);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeveloperCourseDTO updateDeveloperCourse(@PathVariable("id") Integer id, @RequestBody DeveloperCourseDTO developerCourseDTO) {
        return developerCourseService.update(id, developerCourseDTO);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteDeveloperCourse(@PathVariable("id") Integer id) {
        developerCourseService.remove(id);
    }

    @GetMapping(value = "/get/{courseName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeveloperCourseDTO> findByDeveloperCourseName(@PathVariable("courseName") String courseName) {
        return developerCourseService.findByDeveloperCourseName(courseName);
    }

    @GetMapping(value = "/getByStudentId/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeveloperCourseDTO> getDeveloperCourseByStudentId(@PathVariable("studentId") Integer studentId) {
        return developerCourseService.findDeveloperCoursesByStudentId(studentId);
    }

    @GetMapping(value = "/getByTeacherId/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeveloperCourseDTO> getDeveloperCourseByTeacherId(@PathVariable("studentId") Integer teacherId) {
        return developerCourseService.findDeveloperCoursesByTeacherId(teacherId);
    }

}
