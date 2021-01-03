package com.alasdoo.developercourseassignment.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false, length = 250)
    private String name;
    @Column(name = "surname", nullable = false, length = 250)
    private String surname;
    @Column(name = "account_name", nullable = false, length = 250, unique = true)
    private String accountName;
    @Column(name = "password", nullable = false, length = 250)
    private String password;
    @Column(name = "email", nullable = false, length = 250, unique = true)
    private String email;
    @Column(name = "bank_card_number", nullable = false, length = 16)
    private Integer bankCardNumber;
}
