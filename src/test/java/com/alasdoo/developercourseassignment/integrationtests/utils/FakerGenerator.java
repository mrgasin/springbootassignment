package com.alasdoo.developercourseassignment.integrationtests.utils;

import com.alasdoo.developercourseassignment.entities.DeveloperCourse;
import com.alasdoo.developercourseassignment.entities.Student;
import com.alasdoo.developercourseassignment.entities.Teacher;
import com.alasdoo.developercourseassignment.integrationtests.utils.models.Person;
import com.github.javafaker.Faker;

public class FakerGenerator {

    private FakerGenerator() {
    }

    private static final Faker faker = new Faker();

    private static Person newPerson() {
        Person person = new Person();
        person.setName(faker.name().firstName());
        person.setSurname(faker.name().lastName());
        person.setEmail(faker.internet().emailAddress());
        return person;
    }

    public static Student newStudent() {
        Person person = newPerson();
        String bankAccountName = person.getName().toLowerCase() + "_" + person.getSurname().toLowerCase();

        Student student = new Student();
        student.setName(person.getName());
        student.setSurname(person.getSurname());
        student.setEmail(person.getEmail());
        student.setBankCardNumber(faker.number().numberBetween(100, 10000));
        student.setAccountName(bankAccountName);
        return student;
    }

    public static Teacher newTeacher() {
        Person person = newPerson();
        Teacher teacher = new Teacher();
        teacher.setTeacherName(person.getName());
        teacher.setTeacherSurname(person.getSurname());
        teacher.setTeacherEmail(person.getEmail());
        return teacher;
    }

    public static DeveloperCourse newCourse() {
        DeveloperCourse course = new DeveloperCourse();
        course.setCostPerClass(faker.number().numberBetween(25, 100));
        course.setClassesPerWeek(faker.number().numberBetween(1, 10));
        course.setDeveloperCourseName(faker.book().title());
        return course;
    }
}
