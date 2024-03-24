package org.example;

import org.example.Controller.PromptDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * The Main class for starting the application.
 * Designed by Sifat
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("/css/login.css").toExternalForm(); // Getting stylesheet
            scene.getStylesheets().add(css); // Adding stylesheet
            primaryStage.setTitle("Log In Prompt");
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("/icons/Accounts_main.png"));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            new PromptDialogController("Error!", "Error Occured. Failed to initialize system.");
        }

    }

    /**
     * The main method to launch the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
