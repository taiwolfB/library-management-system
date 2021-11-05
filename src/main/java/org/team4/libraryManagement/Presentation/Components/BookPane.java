package org.team4.libraryManagement.Presentation.Components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.team4.libraryManagement.model.Book;

import java.util.function.Consumer;

public class BookPane extends Pane {
    Book book;

    public Book getBook() {
        return book;
    }

    public BookPane(Book b){
        super();
        book = b;
    }

    public void select(){
        getStyleClass().add("m-selected-card");
    }

    public void unselect(){
        getStyleClass().remove("m-selected-card");
    }

    public static BookPane build(Book book, Runnable onClick) {

        BookPane base = new BookPane(book);
        base.onMouseClickedProperty().set( (e) -> onClick.run());
        base.getStyleClass().add("m-card");
        base.prefWidth(Double.POSITIVE_INFINITY);
        VBox box = new VBox();
        box.setSpacing(10);
        box.setPadding(new Insets(20, 30, 20, 30));
        box.getChildren().addAll(
                buildLabel(book.getTitle(), "m-heading"),
                buildLabel(book.getAuthor(), "m-subheading"),
                buildLabel(book.getIsbn(), "m-subheading"),
                buildLabel(book.getGenre(), "m-subheading")
        );
        base.getChildren().add(box);

        return base;
    }

    static private Label buildLabel(String text, String clas ){
        Label l = new Label(text);
        l.getStyleClass().add(clas);
        return l;
    }
}
