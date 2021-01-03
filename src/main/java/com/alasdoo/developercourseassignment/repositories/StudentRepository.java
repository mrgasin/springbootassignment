package com.alasdoo.developercourseassignment.repositories;

import com.alasdoo.developercourseassignment.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByAccountName(String accountName);
}
