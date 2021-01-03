package com.alasdoo.developercourseassignment.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "teacher_developer_course")
public class TeacherDeveloperCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "developer_course_id", nullable = false, length = 250)
    private Integer developerCourseId;
    @Column(name = "teacher_id", nullable = false, length = 250)
    private Integer teacherId;
}
