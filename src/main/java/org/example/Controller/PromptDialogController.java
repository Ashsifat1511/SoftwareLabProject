package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for displaying prompt dialogs.
 * Designed by Sifat
 */
public class PromptDialogController {

    /**
     * Constructs a new prompt dialog with the given header and error message.
     * @param header The header text of the prompt dialog.
     * @param error The error message to be displayed.
     */
    public PromptDialogController(String header, String error) {

        Stage stg = new Stage();
        stg.setAlwaysOnTop(true);

        //Modality is so that this window must be interacted before others
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/view/dialog.fxml"));
            Scene s = new Scene(root);

            //Getting useful nodes from FXML to set error report
            Label lblHeader = (Label) root.lookup("#lblHeader");
            JFXTextArea txtError = (JFXTextArea) root.lookup("#txtError");
            JFXButton btnClose = (JFXButton) root.lookup("#btnClose");

            lblHeader.setText(header);
            txtError.setText(error);

            //Setting close button event
            btnClose.setOnAction(event -> {
                stg.hide();
            });

            stg.setScene(s);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

