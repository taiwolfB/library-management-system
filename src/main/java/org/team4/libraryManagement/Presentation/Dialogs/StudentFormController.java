package org.team4.libraryManagement.Presentation.Dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.team4.libraryManagement.service.DialogService;
import org.team4.libraryManagement.dao.GeneralDAO;
import org.team4.libraryManagement.model.Student;
import org.team4.libraryManagement.validator.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentFormController extends DialogController {

    protected static final Logger LOGGER = Logger.getLogger(StudentFormController.class.getName());
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
            createStudent(firstNameTextField.getText(),lastNameTextField.getText(),emailTextField.getText());
        else
            updateStudent();
        root.close();
    }
    public void closeDialog(ActionEvent actionEvent) {
        root.close();
    }

    private void updateStudent() {

        List<String> parametersToValidate = new ArrayList<>();
        parametersToValidate.add(emailTextField.getText());
        parametersToValidate.add(firstNameTextField.getText());
        parametersToValidate.add(lastNameTextField.getText());
        if (new GeneralDAO<>(Student.class).getStudentValidator().validate(parametersToValidate)){
            target.setFirstName(firstNameTextField.getText());
            target.setLastName(lastNameTextField.getText());
            target.setEmail(emailTextField.getText());
            new GeneralDAO<>(Student.class).updateStudent(target);
            LOGGER.log(Level.FINE, "Student with uuid " + target.getUuid() + " updated");
        }
        //TODO show updated student on UI
    }

    public Student getCreatedStudent(String param)
    {
        return (Student) new GeneralDAO<>(Student.class).selectByParameter("firstName",param);
    }
    public void createStudent(String firstName, String lastName, String email) {
        //TODO process fields and call function to handle database communication for create
        List<String> parametersToValidate = new ArrayList<>();
        parametersToValidate.add(email);
        parametersToValidate.add(firstName);
        parametersToValidate.add(lastName);
        if (new GeneralDAO<>(Student.class).getStudentValidator().validate(parametersToValidate)){
            target = new Student();
            target.setFirstName(firstName);
            target.setLastName(lastName);
            target.setEmail(email);
            target.setBlacklisted(false);
            String uuid = generateRandomNumericString();
            List<Student> students = new GeneralDAO<>(Student.class).selectAll();
            HashSet<String> set = new HashSet<>();
            students.forEach(s -> set.add(s.getUuid()));
            while(true)
            {
                if(set.contains(uuid))
                    uuid = generateRandomNumericString();
                else
                   break;
            }
            target.setUuid(uuid);

            new GeneralDAO<>(Student.class).insertStudent(target);
        }
    }
}
