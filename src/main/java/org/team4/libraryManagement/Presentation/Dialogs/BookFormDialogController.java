package org.team4.libraryManagement.Presentation.Dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.team4.libraryManagement.service.DialogService;
import org.team4.libraryManagement.dao.GeneralDAO;
import org.team4.libraryManagement.model.Book;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BookFormDialogController extends DialogController {

    protected static final Logger LOGGER = Logger.getLogger(BookFormDialogController.class.getName());
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
        List<String> parametersToValidate = new ArrayList<>();
        parametersToValidate.add(titleTextField.getText());
        parametersToValidate.add(authorTextField.getText());
        parametersToValidate.add(genreTextField.getText());
        parametersToValidate.add(ISBNTextField.getText());
        System.out.println(ISBNTextField.getText());
        if (new GeneralDAO<>(Book.class).getBookValidator().validate(parametersToValidate)){
            target.setTitle(titleTextField.getText());
            target.setAuthor(authorTextField.getText());
            target.setGenre(genreTextField.getText());
            target.setIsbn(ISBNTextField.getText());
            new GeneralDAO<>(Book.class).updateBook(target);
            LOGGER.log(Level.FINE, "Book with uuid " + target.getUuid() + " updated");
        }
        //TODO show updated Book on UI
    }

    private void createBook() {
        List<String> parametersToValidate = new ArrayList<>();
        parametersToValidate.add(titleTextField.getText());
        parametersToValidate.add(authorTextField.getText());
        parametersToValidate.add(genreTextField.getText());
        parametersToValidate.add(ISBNTextField.getText());
        System.out.println(ISBNTextField.getText());
        if (new GeneralDAO<>(Book.class).getBookValidator().validate(parametersToValidate)){
            target = new Book();
            target.setTitle(titleTextField.getText());
            target.setAuthor(authorTextField.getText());
            target.setGenre(genreTextField.getText());
            target.setIsbn(ISBNTextField.getText());

            String uuid = generateRandomNumericString();
            List<Book> books = new GeneralDAO<>(Book.class).selectAll();
            HashSet<String> set = new HashSet<>();
            books.forEach(b -> set.add(b.getUuid()));
            while(true)
            {
                if(set.contains(uuid))
                    uuid = generateRandomNumericString();
                else
                    break;
            }
            target.setUuid(uuid);

            new GeneralDAO<>(Book.class).insertBook(target);
        }
    }
}
