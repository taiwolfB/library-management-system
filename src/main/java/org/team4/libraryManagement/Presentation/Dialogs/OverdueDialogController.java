package org.team4.libraryManagement.Presentation.Dialogs;

import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import oracle.ucp.common.waitfreepool.Tuple;
import org.team4.libraryManagement.Presentation.Components.OverdueBookPane;
import org.team4.libraryManagement.model.Book;
import org.team4.libraryManagement.model.Student;

import java.util.ArrayList;
import java.util.UUID;

public class OverdueDialogController extends DialogController {


    @FXML VBox bookContainer;
    @FXML JFXDialog root;

    @FXML void initialize() {
        DialogService.get().subscribe("overdue", root, this);
    }

    @Override
    public void initDialog(Object param) {
        //TODO remove:
        ArrayList<Tuple<Book, Student>> info = new ArrayList<>();
//        info.add(new Tuple<>(new Book("AHh", "hahah", "hehe", "hoho", null, null, null),
//                             new Student(new UUID(10,10), "hhe", "hoho", false, "hihi")));
//        info.add(new Tuple<>(new Book("AHh", "hahah", "hehe", "hoho", null, null, null),
//                new Student(new UUID(10,10), "hhe", "hoho", false, "hihi")));
//        info.add(new Tuple<>(new Book("AHh", "hahah", "hehe", "hoho", null, null, null),
//                new Student(new UUID(10,10), "hhe", "hoho", false, "hihi")));
//        info.add(new Tuple<>(new Book("AHh", "hahah", "hehe", "hoho", null, null, null),
//                new Student(new UUID(10,10), "hhe", "hoho", false, "hihi")));
        bookContainer.getChildren().addAll(
                info.stream().map((t) -> OverdueBookPane.build(t.get1(), t.get2())).toList()
        );
    }

    public void closeDialog(ActionEvent actionEvent) {
        root.close();
    }
}
