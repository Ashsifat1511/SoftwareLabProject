package org.example.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Others.DBConnection;
import org.example.Others.Rent;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
/**
 * Controller class for managing rental list UI.
 * Designed by Tithi
 */

public class RentalListController implements Initializable {
    @FXML
    private Label lblSellCount;
    @FXML
    private Label lblDue;
    @FXML
    private Label lblHeader;
    @FXML
    private Label lblAmount;
    @FXML
    private Label today;
    @FXML
    private TableView<Rent> tblRecent;
    @FXML
    private TableColumn<Rent, Integer> rentID;
    @FXML
    private TableColumn<Rent, Integer> cusID;
    @FXML
    private TableColumn<Rent, Integer> itemID;
    @FXML
    private TableColumn<Rent, String> rentalDate;
    @FXML
    private TableColumn<Rent, String> returnDate;
    @FXML
    private TableColumn<Rent, String> empName;
    @FXML
    private TableColumn<Rent, Double> paid;
    @FXML
    private TableColumn<Rent, Double> due;

    public static boolean todayFlag = false;
    PreparedStatement getRentalList;
    /**
     * Initializes the controller.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        today.setText(LocalDate.now().toString());

        Connection con = DBConnection.getConnection();

        if(todayFlag) {
            lblHeader.setText("Today's Rentals Report");
            try {
                getRentalList = con.prepareStatement("SELECT * FROM rentals WHERE  rentalDate = '"+ Date.valueOf(LocalDate.now()) +"'");
                showReport();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            todayFlag = false;
        } else {
            try {
                getRentalList = con.prepareStatement("SELECT * FROM rentals");
                showReport();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Retrieves and displays the rental report.
     */
    private void showReport() {
        try {
            ResultSet rentList = getRentalList.executeQuery();

            ObservableList<Rent> rentsListByUser = FXCollections.observableArrayList();

            //Setting up table columns
            rentID.setCellValueFactory(new PropertyValueFactory<>("rentID"));
            itemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
            cusID.setCellValueFactory(new PropertyValueFactory<>("cusID"));
            rentalDate.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
            returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
            paid.setCellValueFactory(new PropertyValueFactory<>("payAmount"));
            due.setCellValueFactory(new PropertyValueFactory<>("amountDue"));
            empName.setCellValueFactory(new PropertyValueFactory<>("user"));

            Integer ctr = 0;
            Double due = 0.0;
            Double total = 0.0;

            while (rentList.next()) {
                rentsListByUser.add(new Rent(rentList.getInt("rentalID"),
                        rentList.getInt("Item_itemID"),
                        rentList.getInt("Customers_customerID"),
                        rentList.getString("rentalDate"),
                        rentList.getString("returnDate"),
                        rentList.getDouble("paid"),
                        rentList.getDouble("amountDue"),
                        rentList.getString("User_username")));
                ctr++;
                due += rentList.getDouble("amountDue");
                total += rentList.getDouble("paid");
            }

            lblDue.setText(due.toString() + " $");
            lblAmount.setText(total.toString() + " $");
            lblSellCount.setText(ctr.toString());

            tblRecent.setItems(rentsListByUser);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

