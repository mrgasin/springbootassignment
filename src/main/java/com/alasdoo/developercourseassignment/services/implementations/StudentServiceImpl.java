package com.alasdoo.developercourseassignment.services.implementations;

import com.alasdoo.developercourseassignment.dtos.StudentDTO;
import com.alasdoo.developercourseassignment.entities.Student;
import com.alasdoo.developercourseassignment.mappers.StudentMapper;
import com.alasdoo.developercourseassignment.repositories.StudentDeveloperCourseRepository;
import com.alasdoo.developercourseassignment.repositories.StudentRepository;
import com.alasdoo.developercourseassignment.services.contracts.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final StudentDeveloperCourseRepository studentDeveloperCourseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, StudentDeveloperCourseRepository studentDeveloperCourseRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.studentDeveloperCourseRepository = studentDeveloperCourseRepository;
    }

    @Override
    public StudentDTO findOne(Integer id) {
        Student student = findById(id);
        return studentMapper.transformToDTO(student);
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(studentMapper::transformToDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        Student student = studentMapper.transformToEntity(studentDTO);
        return studentMapper.transformToDTO(studentRepository.save(student));
    }

    @Override
    @Transactional
    public void remove(Integer id) throws IllegalArgumentException {
        findById(id);
        studentDeveloperCourseRepository.deleteAllByStudentId(id);
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO update(Integer id, StudentDTO studentDTO) {
        Student oldStudent = findById(id);
        Student s = studentMapper.transformToEntity(studentDTO);
        s.setId(oldStudent.getId());
        studentRepository.save(s);
        return studentMapper.transformToDTO(s);
    }

    @Override
    public StudentDTO findByAccountName(String accountName) {
        Optional<Student> student = studentRepository.findByAccountName(accountName);
        if (!student.isPresent()) {
            throw new IllegalArgumentException
                    ("Student with the following account name = " + accountName + " is not found.");
        }
        return studentMapper.transformToDTO(student.get());
    }

    private Student findById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("Student with the following id = " + id + " is not found."));
    }
}
