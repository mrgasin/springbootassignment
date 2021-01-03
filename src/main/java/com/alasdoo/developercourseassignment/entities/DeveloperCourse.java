package com.alasdoo.developercourseassignment.entities;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "developer_course")
public class DeveloperCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "developer_course_name", nullable = false, length = 250)
    private String developerCourseName;
    @Column(name = "cost_per_class", nullable = false)
    private Integer costPerClass;
    @Column(name = "classes_per_week", nullable = false)
    private Integer classesPerWeek;
}
