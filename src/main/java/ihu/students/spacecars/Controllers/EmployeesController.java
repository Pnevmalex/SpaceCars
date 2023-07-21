package ihu.students.spacecars.Controllers;

import ihu.students.spacecars.DatabaseConnection;
import ihu.students.spacecars.Main;
import ihu.students.spacecars.SearchModels.CarsSearchModel;
import ihu.students.spacecars.SearchModels.EmployeesSearchModel;
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

public class EmployeesController implements Initializable {
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
    private TableView<EmployeesSearchModel> employeesTableView;

    @FXML
    private TableColumn<EmployeesSearchModel, String> nameColumn;

    @FXML
    private TableColumn<EmployeesSearchModel, Integer> ageColumn;

    @FXML
    private TableColumn<EmployeesSearchModel, String> phoneColumn;

    @FXML
    private TableColumn<EmployeesSearchModel, String> categoryColumn;

    ObservableList<EmployeesSearchModel> employeesSearchModelObservableList = FXCollections.observableArrayList();

    EmployeesSearchModel employee = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String employeesViewQuery = "SELECT FULLNAME, AGE, PHONE, CATEGORY FROM EMPLOYEES";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(employeesViewQuery);

            while (queryOutput.next()) {
                String queryEmployeesName = queryOutput.getString("FULLNAME");
                Integer queryEmployeesAge = queryOutput.getInt("AGE");
                String queryEmployeesPhone = queryOutput.getString("PHONE");
                String queryEmployeesCategory = queryOutput.getString("CATEGORY");


                employeesSearchModelObservableList.add(new EmployeesSearchModel(queryEmployeesName, queryEmployeesAge, queryEmployeesPhone, queryEmployeesCategory));
            }

            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

            employeesTableView.setItems(employeesSearchModelObservableList);

            FilteredList<EmployeesSearchModel> filteredData = new FilteredList<>(employeesSearchModelObservableList, b -> true);

            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(employeesSearchModel -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (employeesSearchModel.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (employeesSearchModel.getAge().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (employeesSearchModel.getPhone().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (employeesSearchModel.getCategory().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;


                });
            });

            SortedList<EmployeesSearchModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(employeesTableView.comparatorProperty());

            employeesTableView.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(EmployeesController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }

    @FXML
    private void refresh() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();
        employeesSearchModelObservableList.clear();

        String employeesViewQuery = "SELECT FULLNAME, AGE, PHONE, CATEGORY FROM EMPLOYEES";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(employeesViewQuery);

            while (queryOutput.next()) {
                String queryEmployeesName = queryOutput.getString("FULLNAME");
                Integer queryEmployeesAge = queryOutput.getInt("AGE");
                String queryEmployeesPhone = queryOutput.getString("PHONE");
                String queryEmployeesCategory = queryOutput.getString("CATEGORY");


                employeesSearchModelObservableList.add(new EmployeesSearchModel(queryEmployeesName, queryEmployeesAge, queryEmployeesPhone, queryEmployeesCategory));
            }

            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

            employeesTableView.setItems(employeesSearchModelObservableList);

            FilteredList<EmployeesSearchModel> filteredData = new FilteredList<>(employeesSearchModelObservableList, b -> true);

            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(employeesSearchModel -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (employeesSearchModel.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (employeesSearchModel.getAge().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (employeesSearchModel.getPhone().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (employeesSearchModel.getCategory().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;


                });
            });

            SortedList<EmployeesSearchModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(employeesTableView.comparatorProperty());

            employeesTableView.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(EmployeesController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    @FXML
    private void addEmp(){
        try {
            Parent parent = FXMLLoader.load(Main.class.getResource("addemployees.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void editEmp() {
        employee = employeesTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("addemployees.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddEmployees addEmployees = loader.getController();
        addEmployees.update = true;
        addEmployees.setTextField(employee.getName(), employee.getAge(), employee.getPhone(), employee.getCategory());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }


    @FXML
    private void deleteEmp() {
        employee = employeesTableView.getSelectionModel().getSelectedItem();
        try {
            DatabaseConnection.statement.executeQuery("SELECT delete_emp('" + employee.getName() + "', '" + employee.getPhone() + "');");
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
        //petakse to login
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

