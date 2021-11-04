package org.team4.libraryManagement.validator;

import org.team4.libraryManagement.model.Student;

public class StudentValidator implements Validator<Student>{
    @Override
    public boolean validate(Student student) {
        return false;
    }
}
