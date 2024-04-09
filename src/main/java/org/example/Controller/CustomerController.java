
package org.example.Controller;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.Others.Customer;
import org.controlsfx.control.textfield.TextFields;
import org.example.Others.DBConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class for managing customer-related operations in a JavaFX application.
 * Designed by Tithi
 */

public class CustomerController implements Initializable {
    @FXML
    private AnchorPane cusTomerPane;
    @FXML
    private AnchorPane customerPane;
    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField email;
    @FXML
    private Label memberSince;
    @FXML
    private JFXToggleButton btnEditMode;
    @FXML
    private JFXButton btnPrevEntry;
    @FXML
    private JFXButton btnNextEntry;
    @FXML
    private Label customerID, customerDue, lblSearchResults, lblMode;
    @FXML
    private Label lblPageIndex;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private Circle imgCustomerPhoto;
    @FXML
    private JFXRadioButton radioMale;
    @FXML
    private ToggleGroup gender;
    @FXML
    private JFXRadioButton radioFemale;
    @FXML
    private AnchorPane customerListPane;
    @FXML
    private JFXButton btnLViewAllCustomers, btnGoBack;
    @FXML
    private FontAwesomeIconView btnSeachIcon;
    @FXML
    private JFXButton btnAddNew, btnSave;
    @FXML
    private JFXButton btnPurchases;
    @FXML
    private JFXButton btnRentals, btnDelete;
    @FXML
    private FontAwesomeIconView btnAddIcon;
    @FXML
    private TableView<Customer> tbl;
    @FXML
    private TableColumn<Customer, Integer> columnID;
    @FXML
    private TableColumn<Customer, String> columnFirstName;
    @FXML
    private TableColumn<Customer, String> columnLastName;
    @FXML
    private TableColumn<Customer, String> columnGender;
    @FXML
    private TableColumn<Customer, String> columnAddress;
    @FXML
    private TableColumn<Customer, String> columnPhone;
    @FXML
    private TableColumn<Customer, String> columnEmail;

    private static int recordIndex = 0;
    private static int recordSize = 0;
    public static ObservableList<Customer> customersList = FXCollections.observableArrayList(); //This field will auto set from InitializerController Class
    public static ObservableList<Customer> tempList = FXCollections.observableArrayList(); //Will hold the main list while searching
    public static ArrayList<String> customerNames = new ArrayList<>();

    private Customer onView = null;
    private static boolean searchDone = false;

    private static boolean addFlag = false;
    private static String imgPath = null;

    /**
     * Initializes the controller class.
     * This method is called after the FXML file has been loaded.
     * It sets up the initial state of the UI.
     *
     * @param location  The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(LogInController.loggerAccessLevel.equals("Admin"))
            btnDelete.setDisable(false);

        TextFields.bindAutoCompletion(txtSearch, customerNames); //Auto complete field is set now

        //Setting next entry if any on next button action
        btnNextEntry.setOnAction(event -> {
            onView = customersList.get(++recordIndex);
            recordNavigator();
            lblPageIndex.setText("Showing " + (recordIndex + 1) + " of " + recordSize + " results.");
            if (recordIndex == recordSize - 1)
                btnNextEntry.setDisable(true);
            btnPrevEntry.setDisable(false);

        });

        //Setting previous entry if any on previous button action
        btnPrevEntry.setOnAction(event -> {
            onView = customersList.get(--recordIndex);
            recordNavigator();
            lblPageIndex.setText("Showing " + (recordIndex + 1) + " of " + recordSize + " results.");
            btnNextEntry.setDisable(false);
            if (recordIndex == 0)
                btnPrevEntry.setDisable(true);
        });
        setView();
    }
    /**
     * Sets up the initial view for the customer records.
     * It initializes various UI components and loads the first customer record.
     */
    private void setView() {
        imgPath = null;
        customerListPane.setVisible(false); //Initially customer list view is set as not visible

        recordIndex = 0; //Resetting record index
        recordSize = customersList.size();

        //Tooltip will be activated on Customer's photo if hovered
        Tooltip tooltip = new Tooltip("Double Click to Change Avatar in 'Edit Mode'");
        Tooltip.install(imgCustomerPhoto, tooltip);

        imgCustomerPhoto.setOnMouseClicked(event -> {
            if (btnEditMode.isSelected() && event.getClickCount() == 2) {
                FileChooser fc = new FileChooser();
                fc.setTitle("Choose Photo");

                File imgFile = fc.showOpenDialog(btnEditMode.getScene().getWindow());

                if(imgFile != null) { //This block will be only executed if there is any file chosen
                    imgPath = imgFile.toURI().toString();

                    if(imgPath.contains(".jpg") || imgPath.contains(".png") || imgPath.contains(".gif") ||imgPath.contains(".jpeg")) {
                        ImagePattern gg = new ImagePattern(new Image(imgPath));
                        imgCustomerPhoto.setFill(gg);
                    } else {
                        new PromptDialogController("File Format Error!", "Please select a valid image file. You can select JPG, JPEG, PNG, GIF");
                    }
                }

            }
        });

        btnNextEntry.setDisable(true); //For purpose ;)

        if (recordSize > 0) {
            onView = customersList.get(recordIndex); //Setting value for current record

            //Setting customer default fields
            recordNavigator();

            //Setting page indexer value
            lblPageIndex.setText("Showing " + (recordIndex + 1) + " of " + recordSize + " results.");

            if (recordSize > 1) {
                btnNextEntry.setDisable(false); //Next entry will be enabled if there is more than one entry
            }
        }

        btnPrevEntry.setDisable(true); //Disabling prevButton Initially
    }


    /**
     * Toggles edit mode for the customer details.
     * When edit mode is enabled, text fields become editable.
     *
     * @param event The ActionEvent that triggered this method.
     */
    @FXML
    void btnEditModeToggle(ActionEvent event) {
        if (btnEditMode.isSelected()) {
            phone.setEditable(true);
            txtFName.setEditable(true);
            txtLName.setEditable(true);
            address.setEditable(true);
            email.setEditable(true);
        } else {
            btnEditMode.setStyle("");
            phone.setEditable(false);
            txtFName.setEditable(false);
            txtLName.setEditable(false);
            address.setEditable(false);
            email.setEditable(false);
        }
    }


    /**
     * Calculates the total due amount for a customer.
     * This includes both purchase and rental dues.
     *
     * @param id The ID of the customer.
     * @return The total due amount for the customer.
     */
    private Double setDue(Integer id) {
        Connection connection = DBConnection.getConnection();
        Double purchaseDue = 0.0;
        Double rentalDue = 0.0;
        try {
            // Getting total purchase due of customer
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT SUM(amountDue) FROM purchases WHERE Customers_customerID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                purchaseDue += rs.getDouble(1);
            }

            // Getting total rental due of customer
            statement = connection.prepareStatement(
                    "SELECT SUM(amountDue) FROM rentals WHERE Customers_customerID = ?");
            statement.setInt(1, id);

            rs = statement.executeQuery();

            while (rs.next()) {
                rentalDue += rs.getDouble(1);
            }

            return purchaseDue + rentalDue;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Navigates to the next customer record.
     * It updates the UI to display the next customer details.
     */
    private void recordNavigator() {
        // Resetting fields from the data of onView field
        customerID.setText(String.valueOf(onView.getId()));
        txtFName.setText(onView.getFirstName());
        txtLName.setText(onView.getLastName());
        address.setText(onView.getAddress());
        email.setText(onView.getEmail());
        phone.setText(onView.getPhone());
        memberSince.setText(onView.getDate().toString());
        customerDue.setText(Double.valueOf(setDue(onView.getId())).toString() + " $");

        if (onView.getGender().equals("Male"))
            radioMale.setSelected(true);
        else
            radioFemale.setSelected(true);

        if (onView.getPhoto() == null) {
            ImagePattern img = new ImagePattern(new Image("/icons/user.png"));
            imgCustomerPhoto.setFill(img);
        } else {
            try {
                imgPath = onView.getPhoto();
                File tmpPath = new File(imgPath.replace("file:", ""));

                if(tmpPath.exists()) {
                    ImagePattern img = new ImagePattern(new Image(imgPath));
                    imgCustomerPhoto.setFill(img);
                } else {
                    imgPath = null; // Setting imgPath to null cause no valid image found
                    ImagePattern img = new ImagePattern(new Image("/icons/user.png"));
                    imgCustomerPhoto.setFill(img);
                }
            } catch (Exception e) {
                ImagePattern img = new ImagePattern(new Image("/icons/user.png"));
                imgCustomerPhoto.setFill(img); //Setting image
            }
        }
    }
    /**
     * Displays the purchases made by the selected customer.
     *
     * @param event The ActionEvent that triggered this method.
     */
    @FXML
    void showPurchases(ActionEvent event) {
        try {
            CustomerPurchaseListController.customerID = Integer.valueOf(customerID.getText());
            Parent pur = FXMLLoader.load(getClass().getResource("/view/customerpurchase.fxml"));
            Scene s = new Scene(pur);
            Stage stg = new Stage();
            stg.setResizable(false);
            stg.setScene(s);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Displays the rentals taken by the selected customer.
     *
     * @param event The ActionEvent that triggered this method.
     */
    @FXML
    void showrentals(ActionEvent event) {
        try {
            CustomersRentalListController.customerID = Integer.valueOf(customerID.getText());
            Parent rent = FXMLLoader.load(getClass().getResource("/view/customerrentals.fxml"));
            Scene s = new Scene(rent);
            Stage stg = new Stage();
            stg.setResizable(false);
            stg.setScene(s);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Displays a list of all customers in the database.
     * It switches the view to display the customer list.
     *
     * @param event The ActionEvent that triggered this method.
     */
    @FXML
    private void listAllCustomers(ActionEvent event) {
        //cusTomerPane.setVisible(false); //Hiding default customer view
        customerListPane.setVisible(true); //Showing total list
        customerPane.setVisible(false);

        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tbl.setItems(customersList);

        btnGoBack.setOnAction(e -> {
            customerListPane.setVisible(false);
            customerPane.setVisible(true);
        });

    }
    /**
     * Deletes the selected customer record from the database.
     *
     * @param event The ActionEvent that triggered this method.
     */
    @FXML
    void btnDelAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setGraphic(new ImageView(this.getClass().getResource("/icons/x-button.png").toString()));

        alert.setHeaderText("Do you really want to delete this entry?");
        alert.setContentText("Press OK to confirm, Cancel to go back");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            Connection connection = DBConnection.getConnection();
            try {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM  customers WHERE customerID = "+Integer.valueOf(customerID.getText()));
                ps.executeUpdate();
                reloadRecords();

                new PromptDialogController("Operation Successful.", "Item is deleted from the database. Restart or refresh to see effective result.");
            } catch (SQLException e) {
                if(e.getErrorCode() == 1451) {
                    new PromptDialogController("Constraint Error", "Customer has accounts & may be transactions. You need to delete them first in Admin Panel if you want to delete this entry");
                }

            }
        }
    }
    /**
     * Reloads the customer records from the database.
     * It updates the UI to reflect the changes.
     */
    private void reloadRecords() {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement getCustomerList = connection.prepareStatement("SELECT * FROM customers");
            ResultSet customerResultSet = getCustomerList.executeQuery();

            customersList.clear();

            while(customerResultSet.next()) {
                customersList.add(new Customer(
                        customerResultSet.getInt(1),
                        customerResultSet.getString(2),
                        customerResultSet.getString(3),
                        customerResultSet.getString(4),
                        customerResultSet.getString(5),
                        customerResultSet.getString(6),
                        customerResultSet.getString(7),
                        customerResultSet.getString(8),
                        customerResultSet.getDate(9)));;
            }

            setView();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for a customer with the given ID in the database.
     *
     * @param id The ID of the customer to search for.
     * @return An ObservableList containing the search results.
     */
    private ObservableList<Customer> searchWithID(Integer id) {
        Connection con = DBConnection.getConnection();

        String idSQL = "SELECT * FROM customers WHERE customerID = ?";

        ObservableList<Customer> searchResult = FXCollections.observableArrayList(); //list to hold search result

        try {
            PreparedStatement preparedStatement = con.prepareStatement(idSQL);
            preparedStatement.setInt(1, id);

            ResultSet customerResultSet = preparedStatement.executeQuery();

            //Getting values from customers result set
            while (customerResultSet.next()) {
                searchResult.add(new Customer(
                        customerResultSet.getInt("customerID"),
                        customerResultSet.getString("firstName"),
                        customerResultSet.getString("lastName"),
                        customerResultSet.getString("address"),
                        customerResultSet.getString("phone"),
                        customerResultSet.getString("email"),
                        customerResultSet.getString("photo"),
                        customerResultSet.getString("gender"),
                        customerResultSet.getDate("memberSince")));
            }

            con.close();

        } catch (SQLException e) {
            new PromptDialogController("SQL Error!",
                    "Error occured while executing Query.\nSQL Error Code: " + e.getErrorCode());
        }

        return searchResult;
    }

    /**
     * Searches for customers with the given name in the database.
     *
     * @param name The name of the customer to search for.
     * @return An ObservableList containing the search results.
     */
    private ObservableList<Customer> searchWithName(String name) {
        Connection con = DBConnection.getConnection();
        String nameSQL = "SELECT * FROM customers " +
                "WHERE firstName COLLATE UTF8_GENERAL_CI like ? " +
                "OR " +
                "lastName COLLATE UTF8_GENERAL_CI like ?";
        ObservableList<Customer> searchResult = FXCollections.observableArrayList(); //list to hold search result

        try {
            PreparedStatement preparedStatement = con.prepareStatement(nameSQL);
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");

            ResultSet customerResultSet = preparedStatement.executeQuery();

            //Getting values from customers result set
            while (customerResultSet.next()) {
                searchResult.add(new Customer(
                        customerResultSet.getInt("customerID"),
                        customerResultSet.getString("firstName"),
                        customerResultSet.getString("lastName"),
                        customerResultSet.getString("address"),
                        customerResultSet.getString("phone"),
                        customerResultSet.getString("email"),
                        customerResultSet.getString("photo"),
                        customerResultSet.getString("gender"),
                        customerResultSet.getDate("memberSince")));

            }
        } catch (SQLException e) {
            new PromptDialogController("SQL Error!",
                    "Error occured while executing Query.\nSQL Error Code: " + e.getErrorCode());
        }
        return searchResult;
    }
    /**
     * Handles the search action when the search button is clicked.
     * It performs a search based on the entered ID or name.
     *
     * @param event The ActionEvent that triggered this method.
     */
    @FXML
    void btnSearchAction(ActionEvent event) {
        if (searchDone) {
            searchDone = false;
            lblSearchResults.setVisible(false);
            customersList = tempList; //Reassigning customers List
            recordSize = customersList.size();
            btnSearch.setTooltip(new Tooltip("Search with customers name or id"));
            btnSeachIcon.setGlyphName("SEARCH");
            setView();
        } else {
            ObservableList<Customer> searchResult = FXCollections.observableArrayList();
            try {
                Integer id = Integer.valueOf(txtSearch.getText());
                searchResult = searchWithID(id);
            } catch (NumberFormatException e) {
                String name = txtSearch.getText();
                searchResult = searchWithName(name);
            } finally {
                if (searchResult.size() <= 0) {
                    lblSearchResults.setText("No Results Found!");
                    lblSearchResults.setVisible(true);
                } else {
                    tempList = FXCollections.observableArrayList(customersList);
                    searchDone = true;
                    btnSeachIcon.setGlyphName("CLOSE");
                    btnSearch.setTooltip(new Tooltip("Reset Full List"));
                    customersList = searchResult; //Assigning search result to customerList
                    recordSize = searchResult.size();
                    lblSearchResults.setText(recordSize + " results found!");
                    lblSearchResults.setVisible(true);
                    // Resetting the view on screen with search results
                    setView();
                }
            }
        }
    }
    /**
     * Toggles between add mode and navigation mode for customer entries.
     * In add mode, users can add new customer entries.
     *
     * @param event The ActionEvent that triggered this method.
     */
    @FXML
    void btnAddMode(ActionEvent event) {
        if(addFlag) {
            addFlag = false; //Resetting addFlag value.
            btnAddIcon.setGlyphName("PLUS");

            //Enabling other buttons
            btnPrevEntry.setDisable(false);
            btnNextEntry.setDisable(false);
            btnSearch.setDisable(false);
            btnRentals.setDisable(false);
            btnPurchases.setDisable(false);
            btnLViewAllCustomers.setDisable(false);
            btnEditMode.setSelected(false);
            btnDelete.setDisable(false);

            String defColor = "#263238";

            //Changing Focus Color
            txtFName.setUnFocusColor(Color.web(defColor));
            txtLName.setUnFocusColor(Color.web(defColor));
            address.setUnFocusColor(Color.web(defColor));
            phone.setUnFocusColor(Color.web(defColor));
            email.setUnFocusColor(Color.web(defColor));

            //Setting Label
            lblMode.setText("Navigation Mode");

            reloadRecords();

            btnEditModeToggle(new ActionEvent());

        } else {
            Connection con = DBConnection.getConnection();
            try {
                PreparedStatement ps = con.prepareStatement("SELECT max(customerID) FROM customers");
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                    customerID.setText(Integer.valueOf(rs.getInt(1) + 1).toString());
                }

                addFlag = true; //Setting flag true to enable exit mode
                btnAddIcon.setGlyphName("UNDO"); //Changing glyph

                //Setting Label
                lblMode.setText("Entry Mode");

                //Disabling other buttons
                btnPrevEntry.setDisable(true);
                btnNextEntry.setDisable(true);
                btnRentals.setDisable(true);
                btnPurchases.setDisable(true);
                btnLViewAllCustomers.setDisable(true);
                btnSearch.setDisable(true);
                btnDelete.setDisable(true);
                btnEditMode.setSelected(true);

                //Cleaning fields
                txtFName.setText("");
                txtLName.setText("");
                address.setText("");
                phone.setText("");
                email.setText("");
                memberSince.setText(LocalDate.now().toString());
                ImagePattern img = new ImagePattern(new Image("/icons/user.png"));
                imgCustomerPhoto.setFill(img);
                imgPath = null;

                btnEditModeToggle(new ActionEvent()); //Changing mode into entry mode.. all fields will be available to edit

            } catch (SQLException e) {
                new PromptDialogController("SQL Error!", "Error occured while executing Query.\nSQL Error Code: " + e.getErrorCode());
            }
        }
    }
    /**
     * Checks if all required fields are filled when adding or updating a record.
     *
     * @return True if all fields are filled, false otherwise.
     */
    private boolean checkFields() {
        boolean entryFlag = true;
        if (txtFName.getText().equals("")) {
            txtFName.setUnFocusColor(Color.web("red"));
            entryFlag = false;
        }

        if(txtLName.getText().equals("")) {
            txtLName.setUnFocusColor(Color.web("red"));
            entryFlag = false;
        }

        if(address.getText().equals("")) {
            address.setUnFocusColor(Color.web("red"));
            entryFlag = false;
        }

        if(phone.getText().equals("")) {
            phone.setUnFocusColor(Color.web("red"));
            entryFlag = false;
        }

        if(email.getText().equals("")) {
            email.setUnFocusColor(Color.web("red"));
            entryFlag = false;;
        }

        return entryFlag;
    }
    /**
     * Adds a new customer record to the database.
     * It retrieves data from the input fields and inserts them into the database.
     */
    private void addRecordToDatabase() {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO customers VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, Integer.valueOf(customerID.getText()));
            ps.setString(2, txtFName.getText());
            ps.setString(3, txtLName.getText());
            ps.setString(4, address.getText());
            ps.setString(5, phone.getText());
            ps.setString(6, email.getText());
            ps.setString(7, imgPath);
            if(radioMale.isSelected()) {
                ps.setString(8, "Male");
            } else if(radioFemale.isSelected()) {
                ps.setString(8, "Female");
            }

            ps.setDate(9, Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

            new PromptDialogController("Operation Successful!", "New Customer Added!");

        } catch (SQLException e) {
            new PromptDialogController("SQL Error!", "Error occured while executing Query.\nSQL Error Code: " + e.getErrorCode());
        }
    }
    /**
     * Updates an existing customer record in the database.
     * It retrieves data from the input fields and updates the corresponding record.
     */
    private void updateRecord() {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE customers SET customerID = ?, firstName = ?, lastName = ?, address = ?," +
                    "phone = ?, email = ?, photo = ?, gender = ?, memberSince = ? WHERE customerID =" + Integer.valueOf(customerID.getText()));

            //Setting fields
            ps.setInt(1, Integer.valueOf(customerID.getText()));
            ps.setString(2, txtFName.getText());
            ps.setString(3, txtLName.getText());
            ps.setString(4, address.getText());
            ps.setString(5, phone.getText());
            ps.setString(6, email.getText());
            //ps.setString(7, "null");
            ps.setString(7, imgPath);
            if (radioMale.isSelected()) {
                ps.setString(8, "Male");
            } else if (radioFemale.isSelected()) {
                ps.setString(8, "Female");
            }
            ps.setDate(9, Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

            new PromptDialogController("Operation Successful!", "The record is updated!");
            reloadRecords();

        } catch (SQLException e) {
            e.printStackTrace();
            new PromptDialogController("SQL Error!", "Error occured while executing Query.\nSQL Error Code: " + e.getErrorCode());
        }
    }
    /**
     * Handles the save action when the save button is clicked.
     * It either adds a new record or updates an existing one based on the mode.
     *
     * @param event The ActionEvent that triggered this method.
     */
    @FXML
    void btnSaveAction(ActionEvent event) {
        if (addFlag) {
            boolean fieldsNotEmpty = checkFields();
            if(fieldsNotEmpty) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Entry");
                alert.setGraphic(new ImageView(this.getClass().getResource("/icons/question (2).png").toString()));

                alert.setHeaderText("Do you want to add this entry?");
                alert.setContentText("Press OK to confirm, Cancel to go back");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    addRecordToDatabase();
                }
            } else {
                JFXSnackbar snackbar = new JFXSnackbar(customerPane);
                snackbar.show("One or more fields are empty!", 3000);
                //final JFXSnackbar.SnackbarEvent snackbarEvent = new JFXSnackbar.SnackbarEvent(new Label("One or more fields are empty!"), Duration.millis(3000), null);
                //snackbar.enqueue(snackbarEvent);
            }
        } else {
            boolean fieldsNotEmpty = checkFields();
            if(fieldsNotEmpty) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Edit");
                alert.setGraphic(new ImageView(this.getClass().getResource("/icons/question (2).png").toString()));

                alert.setHeaderText("Do you really want to update this entry?");
                alert.setContentText("Press OK to confirm, Cancel to go back");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    updateRecord();
                }
            }

        }
    }
}