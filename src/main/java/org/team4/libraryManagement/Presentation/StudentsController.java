package org.team4.libraryManagement.Presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import org.team4.libraryManagement.Presentation.Components.StudentPane;
import org.team4.libraryManagement.service.DialogService;
import org.team4.libraryManagement.Presentation.Dialogs.WarningMessage;
import org.team4.libraryManagement.dao.GeneralDAO;
import org.team4.libraryManagement.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsController {

    @FXML JFXButton blaclistStudentButton;
    @FXML JFXButton unblaclistStudentButton;
    @FXML JFXButton updateStudentButton;
    @FXML JFXButton deleteStudentButton;

    @FXML VBox studentContainer;
    @FXML JFXTextField searchBar;
    @FXML JFXComboBox<Label> comboBox;
    StudentPane selectedStudent;

    @FXML void initialize(){
        InitializeComboBox();
        selectedStudent = null;
        //TODO REMOVE and get them from database (BUT ONLY WHEN SEARCHING) -> finished -> to discuss/review
        List<Student> studentList = new GeneralDAO<>(Student.class).selectAll();
        updateStudentList((ArrayList<Student>) studentList);
    }

    private void updateStudentList(ArrayList<Student> students) {
        studentContainer.getChildren().clear();
        studentContainer.getChildren().addAll(
                students.stream().map( s -> StudentPane.build(s, () -> selectStudent(s))).toList()
        );
    }

    public void selectStudent(Student b) {
        if(selectedStudent != null)
            selectedStudent.unselect();
        selectedStudent = (StudentPane) studentContainer.getChildren().stream().filter(  node -> ((StudentPane) node).getStudent().equals(b)).findFirst().get();
        selectedStudent.select();
        setButtons(false);
    }
    private void setButtons(boolean b) {
        updateStudentButton.setDisable(b);
        if(b == false){
            if(selectedStudent.getStudent().isBlacklisted()){
                blaclistStudentButton.setDisable(true);
                unblaclistStudentButton.setDisable(false);
            }
            else {
                blaclistStudentButton.setDisable(false);
                unblaclistStudentButton.setDisable(true);
            }
        }
        else {
            blaclistStudentButton.setDisable(true);
            unblaclistStudentButton.setDisable(true);
        }
        deleteStudentButton.setDisable(b);
    }

    public void createNewStudent(ActionEvent actionEvent) {
        DialogService.get().openDialog("studentForm");
    }

    public void blacklistStudent(ActionEvent actionEvent) {
        DialogService.get().openDialog("warning", new WarningMessage(
                "BLACKLIST",
                "Are you sure you want to blacklist " + selectedStudent.getStudent().getFirstName() + " from borrowing books?",
                this::performBlacklisting
        ));
    }

    private void performBlacklisting() {
        selectedStudent.getStudent().setBlacklisted(true);
        new GeneralDAO<>(Student.class).updateStudent(selectedStudent.getStudent());
        //TODO show blacklist label
    }


    public void unblacklistStudent(ActionEvent actionEvent) {
        selectedStudent.getStudent().setBlacklisted(false);
        new GeneralDAO<>(Student.class).updateStudent(selectedStudent.getStudent());
        //TODO delete blacklist label
    }

    public void updateStudent(ActionEvent actionEvent) {
        DialogService.get().openDialog("studentForm", selectedStudent.getStudent());
    }

    public void deleteStudent(ActionEvent actionEvent) {
        DialogService.get().openDialog("warning", new WarningMessage(
                "DELETE",
                "Are you sure you want to delete " + selectedStudent.getStudent().getFirstName() + " from the database?",
                this::performDeletion
        ));
    }
    private void performDeletion() {
        //TODO implement deletion of selected book -> Finished -> Review
        new GeneralDAO<>(Student.class).delete(selectedStudent.getStudent().getUuid());
        //TODO show the studentt has been deleted, discussion
    }

    public void searchStudent(ActionEvent actionEvent) {
        //TODO get list of students -> finished -> to review
        List<Student> students = new GeneralDAO<>(Student.class).selectByParameter(comboBox.getValue().getText(),searchBar.getText());
        updateStudentList((ArrayList<Student>) students);
        //selectedStudent.unselect();
        setButtons(true);
        selectedStudent = null;
    }

    private void InitializeComboBox() {
        comboBox.getItems().add(new Label("email"));
        comboBox.getItems().add(new Label("firstName"));
        comboBox.getItems().add(new Label("lastName"));
        comboBox.getItems().add(new Label("Student Id"));
        comboBox.setConverter(new StringConverter<Label>() {
            @Override
            public String toString(Label object) {
                return object==null? "" : object.getText();
            }

            @Override
            public Label fromString(String string) {
                return new Label(string);
            }
        });
        comboBox.getSelectionModel().select(0);
    }

}
