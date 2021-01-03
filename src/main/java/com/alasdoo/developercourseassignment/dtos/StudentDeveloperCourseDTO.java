package com.alasdoo.developercourseassignment.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDeveloperCourseDTO implements Serializable {

    private Integer id;
    private Integer studentId;
    private Integer developerCourseId;
    private Integer classesBought;
}
