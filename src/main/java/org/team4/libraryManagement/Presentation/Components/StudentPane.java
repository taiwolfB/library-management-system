package org.team4.libraryManagement.Presentation.Components;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.team4.libraryManagement.model.Book;
import org.team4.libraryManagement.model.Student;

public class StudentPane extends Pane {
    Student student;

    public Student getStudent() {
        return student;
    }

    public StudentPane(Student s){
        super();
        student = s;
    }

    public void select(){
        getStyleClass().add("m-selected-card");
    }

    public void unselect(){
        getStyleClass().remove("m-selected-card");
    }

    public static StudentPane build(Student student, Runnable onClick) {

        StudentPane base = new StudentPane(student);
        base.onMouseClickedProperty().set( (e) -> onClick.run());
        base.getStyleClass().add("m-card");
        base.prefWidth(Double.POSITIVE_INFINITY);
        VBox box = new VBox();
        box.setSpacing(10);
        box.setPadding(new Insets(20, 30, 20, 30));
        box.getChildren().addAll(
                buildLabel(student.getFirstName() + student.getLastName(), "m-heading"),
                buildLabel(student.getEmail(), "m-subheading"),
                buildLabel(student.getId().toString(), "m-subheading")
        );
        HBox box2 = new HBox();
        box2.setAlignment(Pos.CENTER_LEFT);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setPrefWidth(30);
        Label blackListed = new Label("blacklisted");
        blackListed.setStyle("-fx-font-size: 18; -fx-text-fill: red");
        box2.getChildren().add(box);
        if(student.isBlacklisted())
            box2.getChildren().addAll(spacer, blackListed);
        base.getChildren().add(box2);

        return base;
    }

    static private Label buildLabel(String text, String clas ){
        Label l = new Label(text);
        l.getStyleClass().add(clas);
        return l;
    }
}
