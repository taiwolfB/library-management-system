package org.team4.libraryManagement.Presentation.Dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.image.impl.General;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.team4.libraryManagement.dao.GeneralDAO;
import org.team4.libraryManagement.model.Book;
import org.team4.libraryManagement.model.Student;
import org.team4.libraryManagement.validator.StudentValidator;

import java.util.ArrayList;
import java.util.List;

public class StudentFormController extends DialogController {

    @FXML JFXDialog root;
    @FXML Label heading;
    @FXML JFXTextField firstNameTextField;
    @FXML JFXTextField lastNameTextField;
    @FXML JFXTextField emailTextField;
    @FXML JFXButton saveButton;
    Student target = null;

    @FXML void initialize(){
        DialogService.get().subscribe("studentForm", root, this);
    }

    @Override
    public void initDialog(Object param) {

        target = null;
        if(param == null){
            heading.setText("New Student");
            saveButton.setText("CREATE");
            firstNameTextField.clear();
            lastNameTextField.clear();
            emailTextField.clear();
        }
        else {
            heading.setText("Update Student");
            saveButton.setText("UPDATE");
            target = (Student) param;
            firstNameTextField.setText(target.getFirstName());
            lastNameTextField.setText(target.getLastName());
            emailTextField.setText(target.getEmail());
        }
    }

    public void saveDialog(ActionEvent actionEvent) {
        if(target == null)
            createStudent();
        else
            updateStudent();
        root.close();
    }
    public void closeDialog(ActionEvent actionEvent) {
        root.close();
    }

    private void updateStudent() {
        System.out.println("updated");
        //TODO process fields and call function to handle database communication for update -> finished -> TO review
        List<String> parametersToValidate = new ArrayList<>();
        parametersToValidate.add(emailTextField.getText());
        parametersToValidate.add(firstNameTextField.getText());
        parametersToValidate.add(lastNameTextField.getText());
        if (new GeneralDAO<>(Student.class).getStudentValidator().validate(parametersToValidate)){
            //TODO you need to call updateStudent() from GeneralDAO but you need the uuid of the student(idk where to get it from)
        }
    }

    private void createStudent() {
        //TODO process fields and call function to handle database communication for create
    }
}
