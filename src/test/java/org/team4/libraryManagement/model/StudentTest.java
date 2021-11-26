package org.team4.libraryManagement.model;

import com.jfoenix.controls.JFXTabPane;
import com.sun.javafx.image.impl.General;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team4.libraryManagement.Presentation.Dialogs.StudentFormController;
import org.team4.libraryManagement.validator.*;
import org.team4.libraryManagement.dao.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class StudentTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void createStudentFailed()
    {
        StudentFormController studentFormController = new StudentFormController();
        studentFormController.createStudent("testFirstName1&*","testLastName1","testtest1@gmail.com");
       Student student =  studentFormController.getCreatedStudent("testFirstName1&*");
       assertEquals("testFirstName1&*",student.getFirstName());
       assertEquals("testLastName1",student.getLastName());
       assertEquals("testtest1@gmail.com",student.getEmail());
    }

    @Test
    void createStudentSuccessful()
    {
        StudentFormController studentFormController = new StudentFormController();
        studentFormController.createStudent("testFirstNameSuccessful","testLastNameSuccessful","testtest1@gmail.com");
        Student student =  studentFormController.getCreatedStudent("testFirstNameSuccessful");
        assertEquals("testFirstNameSuccessful",student.getFirstName());
        assertEquals("testLastNameSuccessful",student.getLastName());
        assertEquals("testtest1@gmail.com",student.getEmail());
    }

    @Test
    void validateStudentFailed()
    {
        StudentValidator studentValidator = new StudentValidator();
        List<String> params = new ArrayList<>();
        params.add("timengelQmail.com");
        params.add("Tim1234");
        params.add("Engel%");
        assertEquals(new GeneralDAO<>(Student.class).getStudentValidator().validate(params),false);
    }

    @Test
    void validateStudentSuccessful()
    {
        StudentValidator studentValidator = new StudentValidator();
        List<String> params = new ArrayList<>();
        params.add("timengel@mail.com");
        params.add("Tim");
        params.add("Engel");
        assertEquals(new GeneralDAO<>(Student.class).getStudentValidator().validate(params),true);
    }




}