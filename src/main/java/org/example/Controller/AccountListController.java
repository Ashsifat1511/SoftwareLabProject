
package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Others.DBConnection;
import org.example.Others.Account;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class controls the functionality of the AccountList.fxml file.
 * Designed by Swapnil
 */
public class AccountListController implements Initializable {
    @FXML
    private JFXTextField txtAccID;
    @FXML
    private JFXTextField txtCusID;
    @FXML
    private TableView<Account> tbl;
    @FXML
    private TableColumn<Account, Integer> accID;
    @FXML
    private TableColumn<Account, String> accName;
    @FXML
    private TableColumn<Account, Integer> cusID;
    @FXML
    private TableColumn<Account, String> issued;
    @FXML
    private TableColumn<Account, String> payType;
    @FXML
    private JFXButton btnAddNew;
    @FXML
    private FontAwesomeIconView btnAddIcon;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private FontAwesomeIconView btnAddIcon1;
    @FXML
    private JFXTextField txtAccName;
    @FXML
    private JFXTextField txtPayMethod;
    @FXML
    private JFXTextField txtEmpName;
    /**
     * Handles the action when an account is deleted.
     */
    @FXML
    void deleteAcc(ActionEvent event) {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM accounts WHERE acccountID = "+Integer.valueOf(txtAccID.getText()));
            ps.executeUpdate();

            txtPayMethod.getScene().getWindow().hide();
            new PromptDialogController("Operation Successful", "Record deleted");

            con.close();
        } catch (SQLException e) {
            new PromptDialogController("Operation Failed", "Invalid Arguments!");
        }
    }
    /**
     * Handles the action when an account is updated.
     */
    @FXML
    void updateAcc(ActionEvent event) {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE accounts SET acccountID = ?, accountName = ?, Customers_customerID = ?, User_username = ?, payMethod = ? WHERE acccountID = "+Integer.valueOf(txtAccID.getText()));
            ps.setInt(1, Integer.valueOf(txtAccID.getText()));
            ps.setString(2, txtAccName.getText());
            ps.setInt(3, Integer.valueOf(txtCusID.getText()));
            ps.setString(4, txtEmpName.getText());
            ps.setString(5, txtPayMethod.getText());

            ps.executeUpdate();

            txtPayMethod.getScene().getWindow().hide();
            new PromptDialogController("Operation Successful", "Record updated");
            con.close();
        } catch (SQLException e) {
            new PromptDialogController("Operation Failed", "Invalid Arguments!");
        }
    }
    /**
     * Initializes the controller and sets up the UI components.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tbl.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2)
            {
                loadContents();
            }
        });

        Connection connection = DBConnection.getConnection();
        ObservableList<Account> list = FXCollections.observableArrayList();

        accID.setCellValueFactory(new PropertyValueFactory<>("accID"));
        accName.setCellValueFactory(new PropertyValueFactory<>("accName"));
        cusID.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        payType.setCellValueFactory(new PropertyValueFactory<>("paymethod"));
        issued.setCellValueFactory(new PropertyValueFactory<>("user"));

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Account(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
            }

            tbl.setItems(list);

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Loads contents into the input fields when a row in the table is double-clicked.
     */
    private void loadContents() {
        Account accountFieldEditorData = tbl.getSelectionModel().getSelectedItem();

        txtAccID.setText(Integer.valueOf(accountFieldEditorData.getAccID()).toString());
        txtAccName.setText(accountFieldEditorData.getAccName());
        txtCusID.setText(Integer.valueOf(accountFieldEditorData.getCusID()).toString());
        txtEmpName.setText(accountFieldEditorData.getUser());
        txtPayMethod.setText(accountFieldEditorData.getPaymethod());
    }
}
