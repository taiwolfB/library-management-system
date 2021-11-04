package org.team4.libraryManagement.Presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import org.team4.libraryManagement.model.Book;

public class BooksController {

    @FXML
    JFXComboBox<Label> comboBox;
    @FXML
    JFXTextField searchBar;


    @FXML void initialize(){
        InitializeComboBox();
    }

    public void createNewBook(ActionEvent actionEvent) {

    }

    public void deleteBook(ActionEvent actionEvent) {

    }

    public void showOverdueBooks(ActionEvent actionEvent) {

    }

    public void lendBook(ActionEvent actionEvent) {
    }

    public void returnBook(ActionEvent actionEvent) {

    }

    public void updateBook(ActionEvent actionEvent) {

    }

    public void searchBook(ActionEvent actionEvent) {

    }

    private void InitializeComboBox() {
        comboBox.getItems().add(new Label("Title"));
        comboBox.getItems().add(new Label("Author"));
        comboBox.getItems().add(new Label("ISBN"));
        comboBox.getItems().add(new Label("Genre"));
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
