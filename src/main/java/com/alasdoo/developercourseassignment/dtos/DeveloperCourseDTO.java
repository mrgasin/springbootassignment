package com.alasdoo.developercourseassignment.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeveloperCourseDTO implements Serializable {

    private Integer id;
    private String developerCourseName;
    private Integer costPerClass;
    private Integer classesPerWeek;
}
