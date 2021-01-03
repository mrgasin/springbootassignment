package com.alasdoo.developercourseassignment.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDTO implements Serializable {

    private Integer id;
    private String name;
    private String surname;
    private String accountName;
    private String email;
    private String password;
    private Integer bankCardNumber;
}
