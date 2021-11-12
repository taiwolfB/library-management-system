package org.team4.libraryManagement.Presentation.Components;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.team4.libraryManagement.controller.EmailService;
import org.team4.libraryManagement.model.Book;
import org.team4.libraryManagement.model.Student;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OverdueBookPane extends Pane {
    Book book;
    private static final Logger LOGGER = Logger.getLogger(OverdueBookPane.class.getName());
    public Book getBook() {
        return book;
    }

    public OverdueBookPane(Book b){
        super();
        book = b;
    }
    public static OverdueBookPane build(Book book, Student student ) {

        OverdueBookPane base = new OverdueBookPane(book);
        base.onMouseClickedProperty().set( (e) -> base.sendEmail(student,book.getTitle()));
        base.getStyleClass().add("m-card");
        base.setPadding(new Insets(20, 0, 20, 30));
        base.prefWidth(Double.POSITIVE_INFINITY);
        VBox box = new VBox();
        box.setSpacing(5);
        box.setPadding(new Insets(20, 30, 20, 30));
        box.getChildren().addAll(
                buildLabel(book.getTitle(), "m-heading"),
                buildLabel("ISBN: " + book.getIsbn(), "m-subheading"),
                buildLabel("BORROWED BY: " + student.getFirstName() + " " + student.getLastName(), "m-subheading"),
                buildLabel("BORROWER ID: " + student.getUuid().toString(), "m-subheading")
        );
        HBox box2 = new HBox();
        box2.setAlignment(Pos.CENTER_LEFT);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        JFXButton btn = new JFXButton();
        btn.setText("SEND EMAIL");
        btn.setOnAction((e) -> {
            btn.setDisable(true);
            base.sendEmail(student,book.getTitle());
        });
        btn.getStyleClass().add("m-button");
        box2.getChildren().addAll(
                box,
                spacer,
                btn
        );
        base.getChildren().add(box2);

        return base;
    }

    static private Label buildLabel(String text, String clas ){
        Label l = new Label(text);
        l.getStyleClass().add(clas);
        return l;
    }

    void sendEmail(Student student, String overDueBook)  {
        //TODO implement email sending (with sendgrid probabbly)
        String emailContent = "Dear " + student.getFirstName() + " \n" +
                "The book following book: " + overDueBook + " is over due!";
        EmailService emailService = new EmailService();
        try {
            emailService.sendEmail("libraryEmail@company.com",student.getEmail(),
                    "Book " + overDueBook + " overdue",emailContent);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error mail service: " + e.getMessage());
        }
    }
}
