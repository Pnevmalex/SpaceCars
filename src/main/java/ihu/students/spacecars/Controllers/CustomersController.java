package ihu.students.spacecars.Controllers;

import ihu.students.spacecars.DatabaseConnection;
import ihu.students.spacecars.Main;
import ihu.students.spacecars.SearchModels.CustomersSearchModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomersController implements Initializable {
    @FXML
    private Button carsBtn;



    @FXML
    private Button customersBtn;

    @FXML
    private Button employeesBtn;

    @FXML
    private Button aboutBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private TextField keywordTextField;

    @FXML
    private TableView<CustomersSearchModel> customersTableView;

    @FXML
    private TableColumn<CustomersSearchModel, String> customersNameColumn;

    @FXML
    private TableColumn<CustomersSearchModel, String> customersPhoneColumn;

    @FXML
    private TableColumn<CustomersSearchModel, Integer> customersCarIDColumn;

    @FXML
    private TableColumn<CustomersSearchModel, String> customersMakeColumn;

    @FXML
    private TableColumn<CustomersSearchModel, String> customersModelColumn;

    ObservableList<CustomersSearchModel> customersSearchModelObservableList = FXCollections.observableArrayList();

    CustomersSearchModel customer = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String customersViewQuery = "SELECT CUSTOMERS.FULLNAME, CUSTOMERS.PHONE, CUSTOMERS.CUSTOMERCARID, CARS.MAKE, CARS.MODEL FROM CUSTOMERS JOIN CARS ON CUSTOMERS.CUSTOMERCARID=CARS.CARID";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(customersViewQuery);

            while (queryOutput.next()) {
                String queryCustomersName = queryOutput.getString("FULLNAME");
                String queryCustomersPhone = queryOutput.getString("PHONE");
                Integer queryCustomersCarID = queryOutput.getInt("CUSTOMERCARID");
                String queryCustomerMake = queryOutput.getString("MAKE");
                String queryCustomerModel = queryOutput.getString("MODEL");

                customersSearchModelObservableList.add(new CustomersSearchModel(queryCustomersName, queryCustomersPhone, queryCustomersCarID, queryCustomerMake, queryCustomerModel));
            }

            customersNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
            customersPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            customersCarIDColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));
            customersMakeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
            customersModelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

            customersTableView.setItems(customersSearchModelObservableList);

            FilteredList<CustomersSearchModel> filteredData = new FilteredList<>(customersSearchModelObservableList, b -> true);

            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(customersSearchModel -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (customersSearchModel.getFullname().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (customersSearchModel.getPhone().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (customersSearchModel.getCarID().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (customersSearchModel.getMake().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (customersSearchModel.getModel().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;


                });
            });

            SortedList<CustomersSearchModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(customersTableView.comparatorProperty());

            customersTableView.setItems(sortedData);


        } catch (SQLException e) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    @FXML
    private void refresh() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();
        customersSearchModelObservableList.clear();

        String customersViewQuery = "SELECT CUSTOMERS.FULLNAME, CUSTOMERS.PHONE, CUSTOMERS.CUSTOMERCARID, CARS.MAKE, CARS.MODEL FROM CUSTOMERS JOIN CARS ON CUSTOMERS.CUSTOMERCARID=CARS.CARID";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(customersViewQuery);

            while (queryOutput.next()) {
                String queryCustomersName = queryOutput.getString("FULLNAME");
                String queryCustomersPhone = queryOutput.getString("PHONE");
                Integer queryCustomersCarID = queryOutput.getInt("CUSTOMERCARID");
                String queryCustomerMake = queryOutput.getString("MAKE");
                String queryCustomerModel = queryOutput.getString("MODEL");

                customersSearchModelObservableList.add(new CustomersSearchModel(queryCustomersName, queryCustomersPhone, queryCustomersCarID, queryCustomerMake, queryCustomerModel));
            }

            customersNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
            customersPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            customersCarIDColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));
            customersMakeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
            customersModelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

            customersTableView.setItems(customersSearchModelObservableList);

            FilteredList<CustomersSearchModel> filteredData = new FilteredList<>(customersSearchModelObservableList, b -> true);

            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(customersSearchModel -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (customersSearchModel.getFullname().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (customersSearchModel.getPhone().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (customersSearchModel.getCarID().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (customersSearchModel.getMake().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (customersSearchModel.getModel().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;


                });
            });

            SortedList<CustomersSearchModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(customersTableView.comparatorProperty());

            customersTableView.setItems(sortedData);


        } catch (SQLException e) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    @FXML
    private void edit() {
        customer = customersTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("updatecustomers.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        UpdateCustomers updateCustomers = loader.getController();
        updateCustomers.setTextField(customer.getFullname(), customer.getPhone(), customer.getCarID());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void delete() {
        customer = customersTableView.getSelectionModel().getSelectedItem();
        try {
            DatabaseConnection.statement.executeQuery("SELECT delete_customer('" + customer.getFullname() + "', " + customer.getCarID() + ");");
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //menu
    @FXML
    private void handleClicks(ActionEvent event) {
        CarsController.MakeMenu(event, carsBtn, customersBtn, employeesBtn, aboutBtn);
    }

    //logout
    public void logoutBtnOnAction(ActionEvent e) {

        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("login.fxml"));
        } catch (IOException x) {
            throw new RuntimeException(x);
        }
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(new Scene(root, 720, 500));
    }
}

