
package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Controller class for managing the base layout and navigation in a JavaFX application.
 *  Designed by Sifat
 */
public class BaseController implements Initializable{
    @FXML
    private AnchorPane paneRight;
    @FXML
    private AnchorPane paneLeft;
    @FXML
    private JFXButton btnDashboard;
    @FXML
    private Label lblAccessLevel;
    @FXML
    private Label lblUsername;
    @FXML
    private JFXButton btnCustomers;
    @FXML
    private JFXButton btnInventoryItem;
    @FXML
    private JFXButton btnAccounts;
    @FXML
    private JFXButton btnDueUpdate;
    @FXML
    private JFXButton btnAdmin;
    @FXML
    private JFXButton btn;
    @FXML
    private Label lblClock;
    @FXML
    private JFXButton btnRentals;
    @FXML
    private JFXButton btnSells;
    private static String username = "";
    private static String accessLevel = "";
    private AnchorPane newRightPane = null;
    private JFXButton temp = null;
    private JFXButton recover = null;
    private static boolean anchorFlag = false;

    private HashMap<String, String> FXML_URL = new HashMap<>();

    /**
     * Automatically resizes the right pane to match the dimensions of the anchor pane.
     */
    private void autoResizePane() {
        newRightPane.setPrefWidth(paneRight.getWidth());
        newRightPane.setPrefHeight(paneRight.getHeight());
    }


    /**
     * Controls the content of the right pane based on the specified FXML file URL.
     * @param URL The URL of the FXML file.
     * @throws IOException If loading the FXML file fails.
     */
    @FXML
    private void ctrlRightPane(String URL) throws IOException {
        try {
            paneRight.getChildren().clear(); //Removing previous nodes
            newRightPane = FXMLLoader.load(getClass().getResource(URL));

            newRightPane.setPrefHeight(paneRight.getHeight());
            newRightPane.setPrefWidth(paneRight.getWidth());

            paneRight.getChildren().add(newRightPane);

            //Listener to monitor any window size change
            paneRight.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
                // Some components of the scene will be resized automatically
                autoResizePane();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Handles the action when a navigation button is clicked.
     * @param event The action event.
     */
    @FXML
    void btnNavigators(ActionEvent event) {
        borderSelector(event); //Marking selected navigator button

        JFXButton btn = (JFXButton)event.getSource();

        // Getting navigation button label
        String btnText = btn.getText();

        // Checking which button is clicked from the map
        // and navigating to respective menu
        try {
            ctrlRightPane(FXML_URL.get(btnText));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Initializes the FXML_URL map with button texts and corresponding FXML file paths.
     */
    private void loadFXMLMap() {
        // Adding URLS in the FXML_URL ArrayList field
        // to avoid code redundancy in ctrlRightPane() method
        FXML_URL.put("Dashboard", "/view/login.fxml");
        FXML_URL.put("Items", "/view/inventory.fxml");
        FXML_URL.put("Customers", "/view/customer.fxml");
        FXML_URL.put("Dashboard", "/view/dashboard.fxml");
        FXML_URL.put("Sells", "/view/sells.fxml");
        FXML_URL.put("Rentals", "/view/rentals.fxml");
        FXML_URL.put("Accounts", "/view/accounts.fxml");
        FXML_URL.put("Administrative", "/view/administrator.fxml");
        FXML_URL.put("Update Due", "/view/dueupdate.fxml");
    }

    /**
     * Marks the selected navigator button and loads the corresponding menu.
     * @param event The action event.
     */
    private void borderSelector(ActionEvent event) {
        JFXButton btn = (JFXButton)event.getSource();

        if(temp == null) {
            temp = btn; //Saving current button properties
        } else {
            temp.setStyle(""); //Resetting previous selected button properties
            temp = btn; //Saving current button properties
        }

        btn.setStyle("-fx-background-color: #455A64");
    }
    /**
     * Initializes the controller and sets up the initial state of the UI.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFXMLMap();
        username = LogInController.loggerUsername;
        accessLevel = LogInController.loggerAccessLevel;
        lblUsername.setText(username.toUpperCase());
        lblAccessLevel.setText(accessLevel);

        //Controling access by checking access level of user
        if(accessLevel.equals("Employee")) {
            btnAdmin.setDisable(true);

        }

        //Setting Clock within a new Thread
        Runnable clock = new Runnable() {
            @Override
            public void run() {
                runClock();
            }
        };

        Thread newClock = new Thread(clock); //Creating new thread
        newClock.setDaemon(true); //Thread will automatically close on applications closing
        newClock.start(); //Starting Thread

        //Setting DashboardController on RightPane
        try {
            ctrlRightPane("/view/dashboard.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Runs a clock in a separate thread to update the time label.
     */
    private void runClock() {
        while (true) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Getting the system time in a string
                    String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
                    // Setting the time in a label
                    lblClock.setText(time);
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Logs out the user and returns to the login screen.
     */
    @FXML
    private void logOut() {
        Stage current = (Stage)lblUsername.getScene().getWindow();
        current.close();

        try {
            // Setting login window
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            root.getStylesheets().add("/css/login.css");
            Scene scene = new Scene(root);
            Stage logInPrompt = new Stage();
            logInPrompt.setScene(scene);
            logInPrompt.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

