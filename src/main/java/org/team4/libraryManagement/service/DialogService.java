package org.team4.libraryManagement.service;

import com.jfoenix.controls.JFXDialog;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.team4.libraryManagement.Presentation.Dialogs.DialogController;

import java.util.HashMap;

public class DialogService {

    final private static DialogService service = new DialogService();

    private DialogService() { }

    public static DialogService get(){
        return service;
    }



    StackPane root;

    public void init(Parent root) {
        this.root = (StackPane) root;
    }

    final private HashMap<String, JFXDialog> dialogs = new HashMap<>();
    final private HashMap<String, DialogController> dialogControllers = new HashMap<>();

    public void subscribe(String id, JFXDialog dialog, DialogController controller) {
        dialogs.put(id, dialog);
        dialogControllers.put(id, controller);
    }

    public void openDialog(String id, Object param){
        dialogControllers.get(id).initDialog(param);
        dialogs.get(id).show(root);
    }

    public void openDialog(String id){
        dialogControllers.get(id).initDialog(null);
        dialogs.get(id).show(root);
    }
}
