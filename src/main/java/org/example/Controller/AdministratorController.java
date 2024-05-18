
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.net.MalformedURLException;
import java.net.ProtocolException;

/**
 * Controller class for managing administrator functionalities in a JavaFX application.
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

    @FXML
    private JFXButton infoAPI;

    @FXML
    private Label welcomeText;

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
     * Handles the action when the item type management button is clicked.
     */
    @FXML
    void itemTypeManage(ActionEvent event) {
        loadWindow("Item Type Management", "/view/newemployee.fxml");
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
     * Opens a window to display developer information.
     */
    @FXML
    public void infoFetch(){
        loadWindow("Developer Information","/view/developerdata.fxml");
    }

    /*public void fetchData(){
        try{
            URL url = new URL("https://api.myjson.online/v1/records/783b26d2-1359-4615-a478-7409dcc4d252");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();
            String json = response.toString();
            jsonParse(json);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void jsonParse(String response) throws ParseException {

        JSONParser parser = new JSONParser();
        Object object = parser.parse(response);

        JSONObject mainjsonObject = (JSONObject) object;

        String id = (String) mainjsonObject.get("roll");
        System.out.println("ID: " + id);




    }*/
    /*public void fetchData() {
        try {
            URL url = new URL("https://api.myjson.online/v1/records/783b26d2-1359-4615-a478-7409dcc4d252");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            String json = response.toString();
            jsonParse(json);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void jsonParse(String response) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(response);
        JSONObject mainjsonObject = (JSONObject) object;
        String developerName;
        developerName = (String) mainjsonObject.get("name");
        System.out.println(developerName);
    }*/
    /*public void fetchData() {
        try {
            URL url = new URL("https://api.myjson.online/v1/records/783b26d2-1359-4615-a478-7409dcc4d252");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            String json = response.toString();
            jsonParse(json);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void jsonParse(String response) throws ParseException {

        JSONParser parser = new JSONParser();
        Object object = parser.parse(response);

        JSONObject mainjsonObject = (JSONObject) object;
        JSONObject dataObject = (JSONObject) mainjsonObject.get("data");

        String name = (String) dataObject.get("name");
        String roll = (String) dataObject.get("roll");
        String batch = (String) dataObject.get("batch");
        String email = (String) dataObject.get("email");
        String dept = (String) dataObject.get("department");
        String uni = (String) dataObject.get("university");

        System.out.println(name + roll + batch + email+dept+uni);

        String text = welcomeText.getText();
        welcomeText.setText(text+"\n"+name+"\n"+roll+"\n"+batch+"\n"+email+"\n"+dept+"\n"+uni);

    }*/
    /**
     * Initializes the controller.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
