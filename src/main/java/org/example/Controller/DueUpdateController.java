package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.example.Others.DBConnection;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Controller class for updating due amounts for purchases and rentals.
 */
public class DueUpdateController implements Initializable {
    @FXML
    private AnchorPane rightPane;
    @FXML
    private JFXTextField txtCustomerId;
    @FXML
    private JFXTextField txtItemId;
    @FXML
    private Label lblCategory;
    @FXML
    private JFXDatePicker txtSellDate;
    @FXML
    private JFXTextField txtPaid;
    @FXML
    private JFXButton btnProcced;
    @FXML
    private FontAwesomeIconView btnIcon;
    @FXML
    private JFXTextField txtDue;
    @FXML
    private JFXTextField txtNewPay;
    @FXML
    private JFXTextField txtSellId;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private FontAwesomeIconView btnSearchIcon;
    @FXML
    private JFXTextField txtCustomerId1;
    @FXML
    private JFXTextField txtItemRental;
    @FXML
    private JFXDatePicker txtRentalDate;
    @FXML
    private JFXTextField txtPaidRental;
    @FXML
    private JFXButton btnProceedRental;
    @FXML
    private FontAwesomeIconView btnIcon1;
    @FXML
    private JFXTextField txtRentalDue;
    @FXML
    private JFXTextField txtNewPayRental;
    @FXML
    private JFXTextField txtRentalID;
    @FXML
    private JFXButton btnSearchRental;
    @FXML
    private FontAwesomeIconView btnSearchIcon1;
    /**
     * Updates the due amount for a rental transaction.
     *
     * @param event The action event triggering the update.
     */
    @FXML
    void updateRentalDue(ActionEvent event) {
        Double newAmount = Double.valueOf(txtNewPayRental.getText());
        Double newDue = Double.valueOf(txtRentalDue.getText()) - newAmount;
        Double totalPaid = Double.valueOf(txtPaidRental.getText()) + newAmount;
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE rentals SET paid = "+totalPaid+", amountDue = "+newDue+"WHERE rentalID = "+Integer.valueOf(txtRentalID.getText()));
            ps.executeUpdate();

            new PromptDialogController("Operation Successful!", "The due is updated successfully and recorded into database!");

            //Clearing fields
            txtRentalID.setText("");
            txtCustomerId1.setText("");
            txtRentalDue.setText("");
            txtRentalDate.setValue(null);
            txtPaidRental.setText("");
            txtItemRental.setText("");
            txtNewPayRental.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Searches for a rental transaction to update its due amount.
     *
     * @param event The action event triggering the search.
     */
    @FXML
    void btnSearchActionRental(ActionEvent event) {
        Integer sId = Integer.valueOf(txtRentalID.getText());

        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rentals WHERE rentalID ="+sId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                txtRentalID.setText(Integer.valueOf(rs.getInt("rentalID")).toString());
                txtCustomerId1.setText(rs.getString("Customers_customerID"));
                txtItemRental.setText(Integer.valueOf(rs.getInt("Item_itemID")).toString());
                txtRentalDate.setValue(Date.valueOf(rs.getString("rentalDate")).toLocalDate());
                txtPaidRental.setText(Double.valueOf(rs.getDouble("paid")).toString());
                txtRentalDue.setText(Double.valueOf(rs.getDouble("amountDue")).toString());
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    /**
     * Searches for a purchase transaction to update its due amount.
     *
     * @param event The action event triggering the search.
     */
    @FXML
    void btnSearchAction(ActionEvent event) {
        Integer sId = Integer.valueOf(txtSellId.getText());

        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM purchases WHERE purchaseID ="+sId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                txtSellId.setText(Integer.valueOf(rs.getInt("purchaseID")).toString());
                txtCustomerId.setText(rs.getString("Customers_customerID"));
                txtItemId.setText(Integer.valueOf(rs.getInt("Item_itemID")).toString());
                txtSellDate.setValue(Date.valueOf(rs.getString("purchaseDate")).toLocalDate());
                txtPaid.setText(Double.valueOf(rs.getDouble("payAmount")).toString());
                txtDue.setText(Double.valueOf(rs.getDouble("amountDue")).toString());

            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Updates the due amount for a purchase transaction.
     *
     * @param event The action event triggering the update.
     */
    @FXML
    void updateSellDue(ActionEvent event) {

        Double newAmount = Double.valueOf(txtNewPay.getText());
        Double newDue = Double.valueOf(txtDue.getText()) - newAmount;
        Double totalPaid = Double.valueOf(txtPaid.getText()) + newAmount;
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE purchases SET payAmount = "+totalPaid+", amountDue = "+newDue+"WHERE purchaseID = "+Integer.valueOf(txtSellId.getText()));
            ps.executeUpdate();

            new PromptDialogController("Operation Successful!", "The due is updated successfully and recorded into database!");

            //Clearing fields
            txtSellId.setText("");
            txtCustomerId.setText("");
            txtDue.setText("");
            txtSellDate.setValue(null);
            txtPaid.setText("");
            txtItemId.setText("");
            txtNewPay.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
