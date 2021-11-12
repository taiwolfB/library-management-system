package org.team4.libraryManagement.Presentation.Dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.team4.libraryManagement.service.DialogService;

public class WarningDialogController extends DialogController{

    @FXML JFXDialog root;
    @FXML Label body;
    @FXML Label heading;
    @FXML JFXButton saveButton;
    WarningMessage msg = null;

    @FXML
    void initialize(){
        DialogService.get().subscribe("warning", root, this);
    }

    @Override
    public void initDialog(Object param) {
        if(param == null){
            msg = null;
            body.setText("Are you sure?");
            saveButton.setText("YES");
        }
        else {
            msg = (WarningMessage) param;
            body.setText(msg.message);
            saveButton.setText(msg.action);
        }
    }

    public void closeDialog(ActionEvent actionEvent) {
        root.close();
    }

    public void saveDialog(ActionEvent actionEvent) {
        msg.then.run();
        root.close();
    }
}
