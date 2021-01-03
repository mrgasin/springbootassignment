package com.alasdoo.developercourseassignment.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeacherDTO implements Serializable {

    private Integer id;
    private String teacherName;
    private String teacherSurname;
    private String teacherEmail;
}
