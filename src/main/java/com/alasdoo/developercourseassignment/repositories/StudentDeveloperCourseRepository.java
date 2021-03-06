package com.alasdoo.developercourseassignment.repositories;

import com.alasdoo.developercourseassignment.entities.StudentDeveloperCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentDeveloperCourseRepository extends JpaRepository<StudentDeveloperCourse, Integer> {

    Optional<List<StudentDeveloperCourse>> findByStudentId(Integer studentId);

    Optional<List<StudentDeveloperCourse>> findByDeveloperCourseId(Integer developerCourseId);

    Optional<StudentDeveloperCourse> findByDeveloperCourseIdAndStudentId(Integer developerCourseId, Integer studentId);

    void deleteAllByDeveloperCourseId(Integer courseId);

    void deleteAllByStudentId(Integer studentId);
}
