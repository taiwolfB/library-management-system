package org.team4.libraryManagement.model;

import com.jfoenix.controls.JFXTabPane;
import com.sun.javafx.image.impl.General;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team4.libraryManagement.Presentation.Dialogs.StudentFormController;
import org.team4.libraryManagement.dao.*;


import static org.junit.jupiter.api.Assertions.*;


class StudentTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void createStudentSuccessful()
    {
        StudentFormController studentFormController = new StudentFormController();
        studentFormController.createStudent("testFirstName1","testLastName1","testtest1@gmail.com");
       Student student =  studentFormController.getCreatedStudent();
       assertEquals("testFirstName1",student.getFirstName());
       assertEquals("testLastName1",student.getLastName());
       assertEquals("testtest1@gmail.com",student.getEmail());
    }



}