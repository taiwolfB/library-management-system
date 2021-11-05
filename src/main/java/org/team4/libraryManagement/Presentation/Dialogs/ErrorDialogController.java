package org.team4.libraryManagement.Presentation.Dialogs;

import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ErrorDialogController extends DialogController {

    @FXML Label body;
    @FXML JFXDialog root;

    @FXML void initialize() {
        DialogService.get().subscribe("error", root, this);
    }

    @Override
    public void initDialog(Object param) {
        if(param == null){
            body.setText("Something went wrong");
        }
        else{
            body.setText((String) param);
        }
    }

    public void closeDialog(ActionEvent actionEvent) {
        root.close();
    }
}
