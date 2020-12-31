package com.alasdoo.developercourseassignment.controllers;

import com.alasdoo.developercourseassignment.dtos.StudentDTO;
import com.alasdoo.developercourseassignment.services.contracts.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/getStudent/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO selectStudent(@PathVariable("id") Integer id) {
        return studentService.findOne(id);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDTO> getAllStudents() {
        return studentService.findAll();
    }

    @PostMapping(value = "/addStudent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.save(studentDTO);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO updateStudent(@PathVariable("id") Integer id, @RequestBody StudentDTO studentDTO) {
        return studentService.update(id, studentDTO);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStudent(@PathVariable("id") Integer id) {
        studentService.remove(id);
    }

    @GetMapping(value = "/get/{accountName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO findByAccountName(@PathVariable("accountName") String accountName) {
        return studentService.findByAccountName(accountName);
    }

    @GetMapping(value = "/get/{accountName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO findByAccountName(@PathVariable("accountName") String accountName, @PathVariable("password") String password) {
        return studentService.findByAccountNameAndPassword(accountName, password);
    }
}
