package org.team4.libraryManagement.Presentation.Dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.team4.libraryManagement.model.Book;

public class BookFormDialogController extends DialogController {

    @FXML JFXButton saveButton;
    @FXML Label heading;
    @FXML JFXTextField titleTextField;
    @FXML JFXTextField authorTextField;
    @FXML JFXTextField genreTextField;
    @FXML JFXTextField ISBNTextField;
    @FXML JFXDialog root;

    Book target = null;

    @FXML void initialize(){
        DialogService.get().subscribe("bookForm", root, this);
    }

    @Override
    public void initDialog(Object param) {
        target = null;
        if(param == null){
            heading.setText("New Book");
            saveButton.setText("CREATE");
            titleTextField.clear();
            authorTextField.clear();
            genreTextField.clear();
            ISBNTextField.clear();

        }
        else {
            heading.setText("Update Book");
            saveButton.setText("UDPATE");
            target = (Book) param;
            titleTextField.setText(target.getTitle());
            authorTextField.setText(target.getAuthor());
            genreTextField.setText(target.getGenre());
            ISBNTextField.setText(target.getIsbn());
        }
    }

    public void closeDialog(ActionEvent actionEvent) {
        root.close();
    }

    public void saveDialog(ActionEvent actionEvent) {
        if(target == null)
            createBook();
        else
            updateBook();
        root.close();
    }

    private void updateBook() {
        System.out.println("updated");
        //TODO process fields and call function to handle database communication for update
    }

    private void createBook() {
        //TODO process fields and call function to handle database communication for create
    }
}
