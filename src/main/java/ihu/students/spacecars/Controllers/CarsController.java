package ihu.students.spacecars.Controllers;

import ihu.students.spacecars.DatabaseConnection;
import ihu.students.spacecars.Main;
import ihu.students.spacecars.SearchModels.CarsSearchModel;
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

public class CarsController implements Initializable {


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
    private TableView<CarsSearchModel> carsTableView;

    @FXML
    private TableColumn<CarsSearchModel, Integer> carsIDColumn;

    @FXML
    private TableColumn<CarsSearchModel, String> carsMakeColumn;

    @FXML
    private TableColumn<CarsSearchModel, String> carsModelColumn;

    @FXML
    private TableColumn<CarsSearchModel, Integer> carsYearColumn;

    @FXML
    private TableColumn<CarsSearchModel, Integer> carsPriceColumn;

    ObservableList<CarsSearchModel> carsSearchModelObservableList = FXCollections.observableArrayList();

    CarsSearchModel car = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();

        String carsViewQuery = "SELECT CARID, MAKE, MODEL, YEAR, PRICE FROM CARS";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(carsViewQuery);

            while (queryOutput.next()) {
                Integer queryCarsID = queryOutput.getInt("CARID");
                String queryCarsMake = queryOutput.getString("MAKE");
                String queryCarsModel = queryOutput.getString("MODEL");
                Integer queryCarsYear = queryOutput.getInt("YEAR");
                Integer queryCarsPrice = queryOutput.getInt("PRICE");

                carsSearchModelObservableList.add(new CarsSearchModel(queryCarsID, queryCarsMake, queryCarsModel, queryCarsYear, queryCarsPrice));
            }

            carsIDColumn.setCellValueFactory(new PropertyValueFactory<>("carsID"));
            carsMakeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
            carsModelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
            carsYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            carsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

            carsTableView.setItems(carsSearchModelObservableList);

            FilteredList<CarsSearchModel> filteredData = new FilteredList<>(carsSearchModelObservableList, b -> true);

            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(carsSearchModel -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (carsSearchModel.getMake().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (carsSearchModel.getModel().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (carsSearchModel.getCarsID().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (carsSearchModel.getYear().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (carsSearchModel.getPrice().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;


                });
            });

            SortedList<CarsSearchModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(carsTableView.comparatorProperty());

            carsTableView.setItems(sortedData);


        } catch (SQLException e) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }


    }


    @FXML
    private void refresh() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDBConnection();
        carsSearchModelObservableList.clear();

        String carsViewQuery = "SELECT CARID, MAKE, MODEL, YEAR, PRICE FROM CARS";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(carsViewQuery);

            while (queryOutput.next()) {
                Integer queryCarsID = queryOutput.getInt("CARID");
                String queryCarsMake = queryOutput.getString("MAKE");
                String queryCarsModel = queryOutput.getString("MODEL");
                Integer queryCarsYear = queryOutput.getInt("YEAR");
                Integer queryCarsPrice = queryOutput.getInt("PRICE");

                carsSearchModelObservableList.add(new CarsSearchModel(queryCarsID, queryCarsMake, queryCarsModel, queryCarsYear, queryCarsPrice));
            }

            carsIDColumn.setCellValueFactory(new PropertyValueFactory<>("carsID"));
            carsMakeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
            carsModelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
            carsYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            carsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

            carsTableView.setItems(carsSearchModelObservableList);

            FilteredList<CarsSearchModel> filteredData = new FilteredList<>(carsSearchModelObservableList, b -> true);

            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(carsSearchModel -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (carsSearchModel.getMake().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (carsSearchModel.getModel().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (carsSearchModel.getCarsID().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (carsSearchModel.getYear().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (carsSearchModel.getPrice().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;


                });
            });

            SortedList<CarsSearchModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(carsTableView.comparatorProperty());

            carsTableView.setItems(sortedData);


        } catch (SQLException e) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }

    @FXML
    private void addBtn() {
        try {
            Parent parent = FXMLLoader.load(Main.class.getResource("addcars.fxml"));
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
    private void order() {
        car = carsTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("addcustomers.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddCustomers addCustomer = loader.getController();
        addCustomer.setTextFieldTwo(car.getCarsID(), car.getPrice());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void edit() {
        car = carsTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("addcars.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CarsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddCarsController addCars = loader.getController();
        addCars.update = true;
        addCars.setTextField(car.getCarsID(), car.getMake(), car.getModel(), car.getYear(), car.getPrice());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void delete() {
        car = carsTableView.getSelectionModel().getSelectedItem();
        try {
            DatabaseConnection.statement.executeQuery("SELECT delete_car(" + car.getCarsID() + ");");
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //menu
    @FXML
    private void handleClicks(ActionEvent event) {
        MakeMenu(event, carsBtn, customersBtn, employeesBtn, aboutBtn);
    }

    static void MakeMenu(ActionEvent event, Button carsBtn, Button customersBtn, Button employeesBtn, Button aboutBtn) {
        if (event.getSource() == carsBtn) {
            Parent root = null;
            try {
                root = FXMLLoader.load(Main.class.getResource("cars.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage window = (Stage) carsBtn.getScene().getWindow();
            window.setScene(new Scene(root, 1250, 750));
        } else if (event.getSource() == customersBtn) {
            Parent root = null;
            try {
                root = FXMLLoader.load(Main.class.getResource("customers.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage window = (Stage) customersBtn.getScene().getWindow();
            window.setScene(new Scene(root, 1250, 750));
        } else if (event.getSource() == employeesBtn) {
            Parent root = null;
            try {
                root = FXMLLoader.load(Main.class.getResource("employees.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage window = (Stage) employeesBtn.getScene().getWindow();
            window.setScene(new Scene(root, 1250, 750));
        } else if (event.getSource() == aboutBtn) {
            Parent root = null;
            try {
                root = FXMLLoader.load(Main.class.getResource("about.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage window = (Stage) aboutBtn.getScene().getWindow();
            window.setScene(new Scene(root, 1250, 750));
        }
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
