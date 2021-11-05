package org.team4.libraryManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.team4.libraryManagement.Presentation.Dialogs.DialogService;

/**
 * The main class of the application where the scene is created and configured.
 */
public class Main extends Application {
    /**
     * The root method where the scene is created
     * @param primaryStage the stage.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
//        try {
            //load and parse the FXML file containing the application frontend.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Presentation/main.fxml"));
            Parent root = loader.load();

            DialogService.get().init(root);
            //Load the stylesheets from styles.
            root.getStylesheets().add(getClass().getResource("Styles/main.css").toString());
            //create the scene with the set width and height
            primaryStage.setScene(new Scene(root, 1200, 800));

            primaryStage.show();
//        }
//        catch (Exception e) {
//            //If something goes wrong, we will show an alert.
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            System.out.println(e);
//            a.setContentText(e.toString());
//            a.show();
//        }
    }

    /**
     * The static function which acts as the main entry to our program.
     * @param args the command line arguments of our application
     */
    public static void main(String[] args) {
        launch(args);
    }
}

