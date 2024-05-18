
package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.xdevapi.JsonParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.Others.DBConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.net.MalformedURLException;
import java.net.ProtocolException;

/**
 * Controller class for managing administrator functionalities in a JavaFX application.
 * Desinged by Sifat
 */
public class AdministratorController implements Initializable {
    @FXML
    private JFXButton btnLastThirty, btnTrans;
    @FXML
    private JFXButton btnUpdateEmp;
    @FXML
    private JFXButton btnTotalRents;
    @FXML
    private JFXButton btnTotalSell;
    @FXML
    private JFXButton btnAddNew;
    @FXML
    private JFXButton btnRemoveEmployee;
    @FXML
    private JFXButton btEmpList;
    @FXML
    private JFXButton btnTopTen;
    @FXML
    private JFXButton btnMostDue;
    @FXML
    private JFXButton btnAccDelete;



    /**
     * Loads a new window with specified title and URL.
     * @param title The title of the window.
     * @param URL The URL of the FXML file for the window.
     */
    void loadWindow(String title, String URL) {
        try {
            Parent acc = FXMLLoader.load(getClass().getResource(URL));
            Scene s = new Scene(acc);
            Stage stg = new Stage();
            stg.setTitle(title);
            stg.setScene(s);
            stg.setResizable(false);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Handles the action when the account update button is clicked.
     */
    @FXML
    void accUpdate(ActionEvent event) {
        loadWindow("Account Management", "/view/itemtypemanager.fxml");
    }

    /**
     * Handles the action when the add new employee button is clicked.
     */
    @FXML
    void addNewEmployee(ActionEvent event) {
        loadWindow("Add Employee", "/view/newemployee.fxml");
    }
    /**
     * Handles the action when the delete all records button is clicked.
     */
    @FXML
    void deleteAll(ActionEvent event) {
        Connection con = DBConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement("DELETE from accounts");
            PreparedStatement ps2 = con.prepareStatement("DELETE from financialtronrental");
            PreparedStatement ps3 = con.prepareStatement("DELETE from financialtronpurchase");
            PreparedStatement px = con.prepareStatement("DELETE FROM trtypecode");
            PreparedStatement ps6 = con.prepareStatement("DELETE from purchases");
            PreparedStatement ps7 = con.prepareStatement("DELETE from rentals");
            PreparedStatement ps4 = con.prepareStatement("DELETE from item");
            PreparedStatement py = con.prepareStatement("DELETE from itemtypes");
            PreparedStatement ps5 = con.prepareStatement("DELETE from customers");

            // TODO check px, py
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setGraphic(new ImageView(this.getClass().getResource("/icons/x-button.png").toString()));

            alert.setHeaderText("Do you really want to delete everything?\n" +
                    "This action will delete every records except employee credentials.\n" +
                    "Once it is done it can not be undone!");
            alert.setContentText("Press OK to confirm, Cancel to go back");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK) {
                ps.executeUpdate();
                ps2.executeUpdate();
                ps3.executeUpdate();
                //px.executeUpdate();
                ps6.executeUpdate();
                ps7.executeUpdate();
                ps4.executeUpdate();
                //py.executeUpdate();
                ps5.executeUpdate();

                new PromptDialogController("Operation Successful!", "The database is reset to initial state.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Handles the action when the employee list button is clicked.
     */
    @FXML
    void empList(ActionEvent event) {
        loadWindow("Employee Management", "/view/employeelist.fxml");
    }

    /**
     * Handles the action when the show transactions button is clicked.
     */
    @FXML
    void showTransactions(ActionEvent event) {
        loadWindow("Transaction List", "/view/translist.fxml");
    }
    /**
     * Handles the action when the total rents button is clicked.
     */
    @FXML
    void totalRents(ActionEvent event) {
        SellListController.todayFlag = false;
        loadWindow("Rental List", "/view/rentallist.fxml");
    }
    /**
     * Handles the action when the total sell button is clicked.
     */
    @FXML
    void totalSell(ActionEvent event) {
        SellListController.todayFlag = false;
        loadWindow("Sell List", "/view/selllist.fxml");
    }


    /**
     * Initializes the controller.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
