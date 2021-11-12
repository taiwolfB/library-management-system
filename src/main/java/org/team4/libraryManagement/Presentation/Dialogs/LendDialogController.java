package org.team4.libraryManagement.Presentation.Dialogs;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.team4.libraryManagement.model.Student;
import org.team4.libraryManagement.service.DialogService;
import org.team4.libraryManagement.dao.GeneralDAO;
import org.team4.libraryManagement.model.Book;

import java.sql.Date;

public class LendDialogController extends DialogController{

    @FXML JFXTextField idTextField;
    @FXML JFXDialog root;

    Book selectedBook;

    @FXML void initialize(){
        DialogService.get().subscribe("lend", root, this);
    }

    @Override
    public void initDialog(Object param) {
        selectedBook = (Book) param;
    }


    public void closeDialog(ActionEvent actionEvent) {
        root.close();
    }

    public void saveDialog(ActionEvent actionEvent) {
        //TODO: implement lend
        selectedBook.setBorrowedBy(idTextField.getText());
        Date date = new Date(System.currentTimeMillis());
        selectedBook.setBorrowedDate(date);
        Student student = new GeneralDAO<>(Student.class).selectById(idTextField.getText());
        if(student.isBlacklisted())
            DialogService.get().openDialog("error","The student is blacklisted!");
        else
            new GeneralDAO<>(Book.class).updateBook(selectedBook);
        root.close();
    }
}
