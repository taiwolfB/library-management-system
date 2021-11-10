package org.team4.libraryManagement.Presentation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import oracle.ucp.common.waitfreepool.Tuple;
import org.team4.libraryManagement.Presentation.Components.BookPane;
import org.team4.libraryManagement.Presentation.Dialogs.DialogService;
import org.team4.libraryManagement.Presentation.Dialogs.WarningMessage;
import org.team4.libraryManagement.dao.GeneralDAO;
import org.team4.libraryManagement.model.Book;
import org.team4.libraryManagement.model.Student;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BooksController {

    @FXML JFXButton updateBookButton;
    @FXML JFXButton returnBookButton;
    @FXML JFXButton lendBookButton;
    @FXML JFXButton deleteBookButton;

    @FXML VBox bookContainer;

    @FXML JFXComboBox<Label> comboBox;

    @FXML JFXTextField searchBar;

    BookPane selectedBook;

    @FXML void initialize(){
        InitializeComboBox();
        ArrayList<Book> bookList = new ArrayList<>();
        selectedBook = null;

        //TODO REMOVE
        updateBookList(new ArrayList<>());
    }

    public void createNewBook(ActionEvent actionEvent) {
        DialogService.get().openDialog("bookForm", null);
    }

    public void deleteBook(ActionEvent actionEvent) {
        DialogService.get().openDialog("warning", new WarningMessage(
                "DELETE",
                "Are you sure you want to delete " + selectedBook.getBook().getTitle() + " from the database?",
                this::performDeletion
        ));
    }

    private void performDeletion() {
        //TODO implement deletion of selected book -> finished -> TO review
        new GeneralDAO<>(Book.class).delete(selectedBook.getBook().getUuid());
    }

    private boolean isOverdue(Date date)
    {
        Date currentDate = new Date();
        long diff = currentDate.getTime() - date.getTime();
        TimeUnit time = TimeUnit.DAYS;
        long difference = time.convert(diff, TimeUnit.MILLISECONDS);
        return difference >= 14L;

    }
    //TODO -> finished -> TO review
    public void showOverdueBooks(ActionEvent actionEvent) {

        List<Book> bookList = new GeneralDAO<>(Book.class).selectAll();
        ArrayList<Tuple<Book,Student>> overdueTuple = new ArrayList<>();
        bookList.stream().filter(b -> isOverdue(b.getBorrwedDate()));
        bookList.forEach(book -> overdueTuple.add(new Tuple<>(book,new GeneralDAO<>(Student.class).selectById(book.getBorrowedBy()))));

        DialogService.get().openDialog("overdue",overdueTuple);

    }

    public void lendBook(ActionEvent actionEvent) {
        DialogService.get().openDialog("lend", selectedBook.getBook());
    }

    public void returnBook(ActionEvent actionEvent) {
        Book book = selectedBook.getBook();
        book.setBorrwedDate(null);
        book.setBorrowedBy(null);
        new GeneralDAO<>(Book.class).updateBook(book);
    }

    public void updateBook(ActionEvent actionEvent) {
        DialogService.get().openDialog("bookForm", selectedBook.getBook());
    }

    public void searchBook(ActionEvent actionEvent) {
        //TODO get list of books -> finished -> TO review
        List<Book> bookList = new GeneralDAO<>(Book.class).selectByParameter(comboBox.getValue().getText(), searchBar.getText());

        bookContainer.getChildren().clear();
        bookContainer.getChildren().addAll(
                bookList.stream().map( b -> BookPane.build(b, () -> selectBook(b))).toList()
        );
        selectedBook.unselect();
        selectedBook = null;
        setButtons(true);
    }

    public void selectBook(Book b) {
        if(selectedBook != null)
            selectedBook.unselect();
        selectedBook = (BookPane) bookContainer.getChildren().stream().filter(  node -> ((BookPane) node).getBook().equals(b)).findFirst().get();
        selectedBook.select();
        setButtons(false);
    }

    private void setButtons(boolean b) {
        updateBookButton.setDisable(b);
        returnBookButton.setDisable(b);
        lendBookButton.setDisable(b);
        deleteBookButton.setDisable(b);
    }

    //TODO patch up later
    private void updateBookList(ArrayList<Book> books){

//        books.add(new Book("haha", "auth", "ha", "ha", null, null, null));
//        books.add( new Book("bebe", "autho", "be", "be", null, null, null));
//        books.add( new Book("bebe", "autho", "be", "be", null, null, null));
//        books.add( new Book("bebe", "autho", "be", "be", null, null, null));
//        books.add( new Book("bebe", "autho", "be", "be", null, null, null));
//        books.add( new Book("bebe", "autho", "be", "be", null, null, null));
//        books.add( new Book("bebe", "autho", "be", "be", null, null, null));
        bookContainer.getChildren().clear();
        bookContainer.getChildren().addAll(
                books.stream().map( b -> BookPane.build(b, () -> selectBook(b))).toList()
        );
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
