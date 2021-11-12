package org.team4.libraryManagement.Presentation.Dialogs;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.team4.libraryManagement.dao.GeneralDAO;
import org.team4.libraryManagement.model.Book;

import java.sql.Date;
import java.time.LocalDateTime;

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
         new GeneralDAO<>(Book.class).updateBook(selectedBook);
        root.close();
    }
}
