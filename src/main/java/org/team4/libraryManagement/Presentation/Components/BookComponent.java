package org.team4.libraryManagement.Presentation.Components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.team4.libraryManagement.model.Book;

public class BookComponent {
    static Pane build(Book book) {
        Pane base = new Pane();
        base.styleProperty().set("m-card");
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

    static private Label  buildLabel(String text, String clas ){
        Label l = new Label(text);
        l.styleProperty().set(clas);
        return l;
    }
}
